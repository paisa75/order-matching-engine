package com.tosan.ui;


import com.tosan.model.BuyOrder;
import com.tosan.model.Order;
import com.tosan.model.OrderResult;
import com.tosan.model.SellOrder;

import java.math.BigDecimal;
import java.util.Scanner;


public class ConsoleUI {
    public Order getOrder() {
        Scanner scanner = new Scanner(System.in);
        String textOrderRegister = """
                *****************************************************
                *   Pleas enter your order in the format below      *
                *   orderType#price#quantity                        *
                *                                                   *
                *   Help:                                           *
                *   enter the order type 'buyOrder' or 'sellOrder'  *
                *   enter the order amount with numbers only        *                         *
                *   enter your order number with numbers only       *
                *   put a sharp(#) between each item                *
                *****************************************************
                    
                    enter your order : \n
                """;
        System.out.println(textOrderRegister);
        String line = scanner.nextLine();
        return prepareOrder(line);
    }

    public void showResult(OrderResult orderResult) {
        String textResultCount = "";
        String textResult = "";
        if (orderResult.getCountSuccessfulOrder() > 0) {
            textResultCount += "===================================================== " +
                    "\n :)  Successful sell records count is :" + orderResult.getCountSuccessfulOrder() +
                    "\n =====================================================";
            for (OrderResult result : orderResult.getResultOrders()) {
                textResult += "**************** recorde :" + result.getCountSuccessfulOrder() +
                        "\n *********************" +
                        "\n buy order id is:" + result.getBuyOrderID() +
                        "\n sell order id is:" + result.getSellOrderID() +
                        "\n Trade executed is:" + result.getTradeQuantity() +
                        "\n units at:" + result.getTradePrice();
            }
            System.out.println(textResultCount);
            System.out.println(textResult);
        } else {
            System.out.println(UserMessage.ORDER_NOT_PLACED);
        }
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
}
