package com.tosan.model;

import java.math.BigDecimal;
import java.util.List;

public class ResultOrder {
    private String buyOrderID;
    private String sellOrderID;
    private int tradeQuantity;
    private BigDecimal tradePrice;
    private int countSuccessfulOrder;

    private List<ResultOrder> resultOrders;
    public String getBuyOrderID() {
        return buyOrderID;
    }

    public void setBuyOrderID(String buyOrderID) {
        this.buyOrderID = buyOrderID;
    }

    public String getSellOrderID() {
        return sellOrderID;
    }

    public void setSellOrderID(String sellOrderID) {
        this.sellOrderID = sellOrderID;
    }

    public int getTradeQuantity() {
        return tradeQuantity;
    }

    public void setTradeQuantity(int tradeQuantity) {
        this.tradeQuantity = tradeQuantity;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    public int getCountSuccessfulOrder() {
        return countSuccessfulOrder;
    }

    public void setCountSuccessfulOrder(int countSuccessfulOrder) {
        this.countSuccessfulOrder = countSuccessfulOrder;
    }

    public List<ResultOrder> getResultOrders() {
        return resultOrders;
    }

    public void setResultOrders(List<ResultOrder> resultOrders) {
        this.resultOrders = resultOrders;
    }
}
