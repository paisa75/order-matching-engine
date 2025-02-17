package com.tosan;

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
        Scanner scanner = new Scanner(System.in);
        ConsoleUI consoleUI = new ConsoleUI();
        MatchingEngine matchingEngine = new MatchingEngine();
        while (true) {
        String textOrderRegister = consoleUI.getOrder(scanner);
            if (textOrderRegister.trim().isEmpty()) {
                break;
            }
            OrderResult orderResult = matchingEngine.processNewOrder(textOrderRegister);
            consoleUI.showResult(orderResult);
        }
        scanner.close();
    }
}
