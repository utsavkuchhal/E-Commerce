package com.example.e_commerse.Models;

public class BasketModel {

    String productId;
    int quantity;

    public BasketModel(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public BasketModel() {
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
