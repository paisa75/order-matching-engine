package com.tosan.ome.service;

import com.tosan.ome.repository.entity.BuyOrder;
import com.tosan.ome.repository.entity.CompletedTrade;
import com.tosan.ome.repository.entity.OrderStatus;
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
    private final MarketDataServicePort marketDataServicePort;

    private static void prepareCompletedOrder(BuyOrder highestBuy, SellOrder lowestSell, BigDecimal tradePrice, int tradeQuantity, List<CompletedTrade> completedOrders) {
        CompletedTrade completedOrder = new CompletedTrade();
        completedOrder.setBuyOrder(highestBuy);
        completedOrder.setSellOrder(lowestSell);
        completedOrder.setTradePrice(tradePrice);
        completedOrder.setTradeQuantity(tradeQuantity);
        completedOrders.add(completedOrder);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class
    )
    public List<CompletedTrade> matchOrders() {
        log.info("Matching orders started...");

        OrderBook orderBook = new OrderBook();
        orderBook.setSellOrders(sellOrderRepository.findByActiveTrueAndStatusNotOrderByPriceAscIdAsc(OrderStatus.CANCELLED));
        orderBook.setBuyOrders(buyOrderRepository.findByActiveTrueAndStatusNotOrderByPriceDescIdAsc(OrderStatus.CANCELLED));

        List<CompletedTrade> completedTrades = new ArrayList<>();

        while (!orderBook.getBuyOrders().isEmpty() && !orderBook.getSellOrders().isEmpty()) {
            BuyOrder highestBuy = orderBook.getHighestBuyOrder();
            SellOrder lowestSell = orderBook.getLowestSellOrder();

            if (highestBuy != null && lowestSell != null && highestBuy.getPrice().compareTo(lowestSell.getPrice()) >= 0) {
                int tradeQuantity = Math.min(highestBuy.getQuantity(), lowestSell.getQuantity());
                BigDecimal tradePrice = lowestSell.getPrice();

                highestBuy.setQuantity(highestBuy.getQuantity() - tradeQuantity);
                highestBuy.setStatus(OrderStatus.PARTIALLY_FILLED);
                lowestSell.setQuantity(lowestSell.getQuantity() - tradeQuantity);
                lowestSell.setStatus(OrderStatus.PARTIALLY_FILLED);

                if (highestBuy.getQuantity() == 0) {
                    highestBuy.setActive(false);
                    highestBuy.setStatus(OrderStatus.FILLED);
                    orderBook.getBuyOrders().remove(highestBuy);
                }
                if (lowestSell.getQuantity() == 0) {
                    lowestSell.setActive(false);
                    lowestSell.setStatus(OrderStatus.FILLED);
                    orderBook.getSellOrders().remove(lowestSell);
                }

                prepareCompletedOrder(highestBuy, lowestSell, tradePrice, tradeQuantity, completedTrades);

                buyOrderRepository.save(highestBuy);
                sellOrderRepository.save(lowestSell);

                marketDataServicePort.updateMarketPrice(tradePrice);
            } else {
                break;
            }
        }
        completedOrdersRepository.saveAll(completedTrades);
        log.info("Matching orders ended with {} completed trades.", completedTrades.size());

        return completedTrades;
    }
}
