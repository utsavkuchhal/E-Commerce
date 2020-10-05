package com.example.e_commerse.Models;

import com.example.e_commerse.Basket;

import java.util.ArrayList;
import java.util.Date;

public class OrderModel {
    String buyerId;
    String orderId;
    ArrayList<BasketModel> basket;
    boolean shipped;
    boolean delivered;
    boolean cancelled;
    String orderTime;
    int totalAmount;

    public OrderModel(String buyerId, String orderId, ArrayList<BasketModel> orderItems, boolean shipped, boolean delivered, boolean cancelled, String orderTime, int totalAmount) {
        this.buyerId = buyerId;
        this.orderId = orderId;
        this.basket = orderItems;
        this.shipped = shipped;
        this.delivered = delivered;
        this.cancelled = cancelled;
        this.orderTime = orderTime;
        this.totalAmount = totalAmount;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ArrayList<BasketModel> getOrderItems() {
        return basket;
    }

    public void setOrderItems(ArrayList<BasketModel> orderItems) {
        this.basket = orderItems;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderModel() {
    }
}
