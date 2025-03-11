package com.tosan.ui;

import com.tosan.entity.Order;
import com.tosan.model.OrderResult;

import java.util.List;

public interface IConsoleUI {
    Order getOrder();

    void showResult(List<OrderResult> orderResultList);
}
