package com.tosan.ui;


import com.tosan.model.OrderResult;

import java.util.Scanner;


public class ConsoleUI {
    public String getOrder(Scanner scanner) {
        String line = "";
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
            line = scanner.nextLine();
        return line;
    }

    public void showResult(OrderResult orderResult) {
        String textResultCount = "";
        String textResult = "";
        if (orderResult.getCountSuccessfulOrder() > 0){
            textResultCount = """
                \n=====================================================
                     :)  Successful sell records count is :"""+ orderResult.getCountSuccessfulOrder()+""" 
                \n=====================================================
                """;
            for (OrderResult result : orderResult.getResultOrders()) {
                textResult += """
                 **************** recorde : """+result.getCountSuccessfulOrder()+"""
                 *********************
                   \n buy order id is:"""+result.getBuyOrderID()+"""  
                   \n sell order id is:"""+result.getSellOrderID()+"""   
                   \n Trade executed is:"""+result.getTradeQuantity()+"""
                   \n units at:"""+result.getTradePrice()+"""             
                """;
            }
            System.out.println(textResultCount);
            System.out.println(textResult);
        } else {
            textResultCount += """
                =====================================================
                              :(  the sell was not made 
                =====================================================
                """;
            System.out.println(textResultCount);
        }
    }
}
