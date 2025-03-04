package com.tosan.ui;


import com.tosan.annotations.Component;
import com.tosan.exceptions.OrderException;
import com.tosan.factory.OrderFactory;
import com.tosan.model.Order;
import com.tosan.model.OrderResult;
import com.tosan.validation.OrderValidator;
import com.tosan.annotations.InjectObject;
import com.tosan.model.OrderType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleUI implements IConsoleUI {

    @InjectObject
    private OrderFactory orderFactory;

    @Override
    public Order getOrder() {
        Scanner scanner = new Scanner(System.in);
        OrderValidator orderValidator = new OrderValidator();
        System.out.println(UserMessage.ORDER_REGISTRATION);
        String line = scanner.nextLine();
        try {
            orderValidator.validateInputFormat(line);
        } catch (OrderException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        return prepareOrder(line);
    }

    @Override
    public void showResult(List<OrderResult> orderResultList) {
        int recordNumber = 0;
        String textResultCount = "";
        String textResult = "";
        if (orderResultList.size() > 0) {
            textResultCount += "===================================================== " +
                    "\n :)  Successful sell records count is :" + orderResultList.size() +
                    "\n =====================================================";
            for (OrderResult result : orderResultList) {
                recordNumber++;
                textResult += "**************** record : " + recordNumber +
                        "*********************" +
                        "\n buy order id is : " + result.getBuyOrderID() +
                        "\n sell order id is : " + result.getSellOrderID() +
                        "\n Trade quantity is : " + result.getTradeQuantity() +
                        "\n trade price is : " + result.getTradePrice() + "\n";
            }
            System.out.println(textResultCount);
            System.out.println(textResult);
        } else {
            System.out.println(UserMessage.ORDER_NOT_PLACED);
        }
    }

    public static void prepareTextLog(String className, String methodName, Object[] params, Object result, long executionTime, String timeStamp) {
        System.out.println("[" + timeStamp + "] " + className + "." + methodName + " called.");
        System.out.println("   Parameters: " + (params != null ? java.util.Arrays.toString(params) : "None"));
        System.out.println("   Returned: " + (result != null ? result : "void"));
        System.out.println("   Execution time: " + executionTime + " ms");
        System.out.println("-------------------------------------------------");
    }

    private Order prepareOrder(String line) {
        String[] tokens = line.split("#");
        OrderType orderType = OrderType.fromLabel(tokens[0]);
        BigDecimal price = new BigDecimal(tokens[1]);
        Integer quantity = Integer.valueOf(tokens[2]);
        return orderFactory.createOrder(orderType, price, quantity);
    }
}
