package com.tosan;

import com.tosan.model.Order;
import com.tosan.service.MatchingEngine;
import com.tosan.model.OrderResult;
import com.tosan.ui.ConsoleUI;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        ConsoleUI consoleUI = new ConsoleUI();
        MatchingEngine matchingEngine = new MatchingEngine();
        while (true) {
        Order order = consoleUI.getOrder();
            OrderResult orderResult = matchingEngine.processNewOrder(order);
            consoleUI.showResult(orderResult);
        }
    }
}
