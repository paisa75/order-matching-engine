package com.tosan.service;

import com.tosan.annotations.InjectObject;
import com.tosan.data.OrderBook;
import com.tosan.model.BuyOrder;
import com.tosan.model.Order;
import com.tosan.model.OrderResult;
import com.tosan.model.SellOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MatchingEngine {
    @InjectObject
    private OrderBook orderBook;

    public List<OrderResult> processNewOrder(Order order) {
        orderBook.addOrder(order);
        return matchOrders();
    }

    private List<OrderResult> matchOrders() {
        List<OrderResult> orderResultList = new ArrayList<>();

        while (!orderBook.getBuyOrders().isEmpty() && !orderBook.getSellOrders().isEmpty()) {
            BuyOrder highestBuy = orderBook.getHighestBuyOrder();
            SellOrder lowestSell = orderBook.getLowestSellOrder();

            if (highestBuy.getPrice().compareTo(lowestSell.getPrice()) >= 0) {
                int tradeQuantity = Math.min(highestBuy.getQuantity(), lowestSell.getQuantity());
                BigDecimal tradePrice = lowestSell.getPrice();

                highestBuy.setQuantity(highestBuy.getQuantity() - tradeQuantity);
                lowestSell.setQuantity(lowestSell.getQuantity() - tradeQuantity);

                if (highestBuy.getQuantity() == 0) {
                    orderBook.getBuyOrders().poll();
                }
                if (lowestSell.getQuantity() == 0) {
                    orderBook.getSellOrders().poll();
                }
                OrderResult result = prepareResultOrder(highestBuy.getId(), lowestSell.getId(), tradeQuantity, tradePrice);
                orderResultList.add(result);
            } else {
                break;
            }
        }
        return orderResultList;
    }

    private OrderResult prepareResultOrder(String buyOrderID, String sellOrderID, int tradeQuantity, BigDecimal tradePrice) {
        OrderResult orderResult = new OrderResult();
        orderResult.setBuyOrderID(buyOrderID);
        orderResult.setSellOrderID(sellOrderID);
        orderResult.setTradeQuantity(tradeQuantity);
        orderResult.setTradePrice(tradePrice);
        return orderResult;
    }
}
