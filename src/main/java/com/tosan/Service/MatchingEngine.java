package com.tosan.Service;

import com.tosan.data.OrderBook;
import com.tosan.model.BuyOrder;
import com.tosan.model.Order;
import com.tosan.model.ResultOrder;
import com.tosan.model.SellOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MatchingEngine {
    private final OrderBook orderBook;

    public MatchingEngine() {
        this.orderBook = new OrderBook();
    }

    public ResultOrder processNewOrder(String textOrderRegister) {
        Order order = prepareOrder(textOrderRegister);
        orderBook.addOrder(order);
        return matchOrders();
    }

    private Order prepareOrder(String line) {
        Order order;
        String[] tokens = line.split("#");
        String orderType = tokens[0];
        BigDecimal price = new BigDecimal(tokens[1]);
        Integer quantity = Integer.valueOf(tokens[2]);
        if (orderType.equals("buyOrder")) {
            order = new BuyOrder(price, quantity);
        } else {
            order = new SellOrder(price, quantity);
        }
        return order;
    }

    private ResultOrder matchOrders() {
        ResultOrder resultOrder = new ResultOrder();
        List<ResultOrder> resultOrderList = new ArrayList<>();
        int countSuccessfulOrder = 0;

        while (!orderBook.getBuyOrders().isEmpty() && !orderBook.getSellOrders().isEmpty()) {
            BuyOrder highestBuy = orderBook.getHighestBuyOrder();
            SellOrder lowestSell = orderBook.getLowestSellOrder();

            if (highestBuy.getPrice().compareTo(lowestSell.getPrice()) >= 0) {
                countSuccessfulOrder ++;

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
                ResultOrder result = prepareResultOrder(highestBuy.getId(), lowestSell.getId(), tradeQuantity, tradePrice, countSuccessfulOrder);
                resultOrderList.add(result);
            } else {
                break;
            }
        }
        resultOrder.setCountSuccessfulOrder(countSuccessfulOrder);
        resultOrder.setResultOrders(resultOrderList);
        return resultOrder;
    }

    private ResultOrder prepareResultOrder(String buyOrderID, String sellOrderID, int tradeQuantity, BigDecimal tradePrice, int countSuccessfulOrder) {
        ResultOrder resultOrder = new ResultOrder();
        resultOrder.setBuyOrderID(buyOrderID);
        resultOrder.setSellOrderID(sellOrderID);
        resultOrder.setTradeQuantity(tradeQuantity);
        resultOrder.setTradePrice(tradePrice);
        resultOrder.setCountSuccessfulOrder(countSuccessfulOrder);
        return resultOrder;
    }
}
