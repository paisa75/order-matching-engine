package com.tosan;

import com.tosan.container.BeanContainer;
import com.tosan.model.Order;
import com.tosan.model.OrderResult;
import com.tosan.service.IMatchingEngine;
import com.tosan.ui.ConsoleUI;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BeanContainer container = new BeanContainer();
        container.scanAndInstantiate("com.tosan");

        ConsoleUI consoleUI = container.getBean(ConsoleUI.class);
        IMatchingEngine matchingEngine = container.getBean(IMatchingEngine.class);

        while (true) {
            Order order = consoleUI.getOrder();
            List<OrderResult> orderResultList = matchingEngine.processNewOrder(order);
            consoleUI.showResult(orderResultList);
        }
    }
}
