package com.tosan.ui;

import com.tosan.model.Order;
import com.tosan.model.OrderResult;

import java.util.List;

public interface IConsoleUI {
    Order getOrder();

    void showResult(List<OrderResult> orderResultList);
}
