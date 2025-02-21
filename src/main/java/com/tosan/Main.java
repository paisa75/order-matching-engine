package com.tosan;

import com.tosan.factory.ObjectFactoryInjector;
import com.tosan.model.Order;
import com.tosan.service.MatchingEngine;
import com.tosan.model.OrderResult;
import com.tosan.ui.ConsoleUI;

import java.util.List;

public class Main
{
    public static void main( String[] args )
    {
        ConsoleUI consoleUI = ObjectFactoryInjector.createObject(ConsoleUI.class);
        MatchingEngine matchingEngine = ObjectFactoryInjector.createObject(MatchingEngine.class);
        while (true) {
        Order order = consoleUI.getOrder();
            List<OrderResult> orderResultList = matchingEngine.processNewOrder(order);
            consoleUI.showResult(orderResultList);
        }
    }
}
