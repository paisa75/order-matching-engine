package com.tosan.ome.service;

import com.tosan.ome.repository.entity.BuyOrder;
import com.tosan.ome.repository.entity.CompletedTrade;
import com.tosan.ome.repository.entity.SellOrder;
import com.tosan.ome.repository.repositories.BuyOrderRepository;
import com.tosan.ome.repository.repositories.CompletedOrdersRepository;
import com.tosan.ome.repository.repositories.SellOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MatchingEngine {

    private final SellOrderRepository sellOrderRepository;
    private final BuyOrderRepository buyOrderRepository;
    private final CompletedOrdersRepository completedOrdersRepository;

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class
    )
    public List<CompletedTrade> matchOrders() {
        log.info("Matching orders started...");

        OrderBook orderBook = new OrderBook();
        orderBook.setSellOrders(sellOrderRepository.findAllByOrderByPriceAscIdAsc());
        orderBook.setBuyOrders(buyOrderRepository.findAllByOrderByPriceDescIdAsc());

        List<CompletedTrade> completedTrades = new ArrayList<>();

        while (!orderBook.getBuyOrders().isEmpty() && !orderBook.getSellOrders().isEmpty()) {
            BuyOrder highestBuy = orderBook.getHighestBuyOrder();
            SellOrder lowestSell = orderBook.getLowestSellOrder();

            if (highestBuy != null && lowestSell != null && highestBuy.getPrice().compareTo(lowestSell.getPrice()) >= 0) {
                int tradeQuantity = Math.min(highestBuy.getQuantity(), lowestSell.getQuantity());
                BigDecimal tradePrice = lowestSell.getPrice();

                highestBuy.setQuantity(highestBuy.getQuantity() - tradeQuantity);
                lowestSell.setQuantity(lowestSell.getQuantity() - tradeQuantity);

                if (highestBuy.getQuantity() == 0) {
                    highestBuy.setActive(false);
                    buyOrderRepository.save(highestBuy);
                    orderBook.getBuyOrders().remove(highestBuy);
                }
                if (lowestSell.getQuantity() == 0) {
                    lowestSell.setActive(false);
                    sellOrderRepository.save(lowestSell);
                    orderBook.getSellOrders().remove(lowestSell);
                }

                prepareCompletedOrder(highestBuy, lowestSell, tradePrice, tradeQuantity, completedTrades);
            } else {
                break;
            }
        }
        completedOrdersRepository.saveAll(completedTrades);
        log.info("Matching orders ended with {} completed trades.",completedTrades.size());

        return completedTrades;
    }

    private static void prepareCompletedOrder(BuyOrder highestBuy, SellOrder lowestSell, BigDecimal tradePrice, int tradeQuantity, List<CompletedTrade> completedOrders) {
        CompletedTrade completedOrder = new CompletedTrade();
        completedOrder.setBuyOrder(highestBuy);
        completedOrder.setSellOrder(lowestSell);
        completedOrder.setTradePrice(tradePrice);
        completedOrder.setTradeQuantity(tradeQuantity);
        completedOrders.add(completedOrder);
    }
}
