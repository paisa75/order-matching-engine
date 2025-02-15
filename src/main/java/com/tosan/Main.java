package com.tosan;

import com.tosan.Service.MatchingEngine;
import com.tosan.model.Order;
import com.tosan.ui.ConsoleUI;

import java.io.Console;

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
            matchingEngine.processNewOrder(order);
            consoleUI.showResult();
        }
    }
}
