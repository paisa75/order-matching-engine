package com.tosan;

import com.tosan.Service.MatchingEngine;
import com.tosan.model.ResultOrder;
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
            ResultOrder resultOrder = matchingEngine.processNewOrder(textOrderRegister);
            consoleUI.showResult(resultOrder);
        }
        scanner.close();
    }
}
