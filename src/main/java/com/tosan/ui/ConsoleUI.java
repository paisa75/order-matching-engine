package com.tosan.ui;


import com.tosan.factory.OrderFactory;
import com.tosan.model.BuyOrder;
import com.tosan.model.Order;
import com.tosan.model.OrderResult;
import com.tosan.model.SellOrder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


public class ConsoleUI {
    public Order getOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(UserMessage.ORDER_REGISTRATION);
        String line = scanner.nextLine();
        return prepareOrder(line);
    }

    public void showResult(List<OrderResult> orderResultList) {
        int recordNumber = 0;
        String textResultCount = "";
        String textResult = "";
        if (orderResultList.size() > 0) {
            textResultCount += "===================================================== " +
                    "\n :)  Successful sell records count is :" + orderResultList.size() +
                    "\n =====================================================";
            for (OrderResult result : orderResultList) {
                recordNumber ++;
                textResult += "**************** record : " + recordNumber +
                        "*********************" +
                        "\n buy order id is : " + result.getBuyOrderID() +
                        "\n sell order id is : " + result.getSellOrderID() +
                        "\n Trade quantity is : " + result.getTradeQuantity() +
                        "\n trade price is : " + result.getTradePrice() +"\n";
            }
            System.out.println(textResultCount);
            System.out.println(textResult);
        } else {
            System.out.println(UserMessage.ORDER_NOT_PLACED);
        }
    }

    private Order prepareOrder(String line) {
        String[] tokens = line.split("#");
        String orderType = tokens[0];
        BigDecimal price = new BigDecimal(tokens[1]);
        Integer quantity = Integer.valueOf(tokens[2]);
        return OrderFactory.createOrder(orderType, price, quantity);
    }
}
