package com.example.e_commerse.Models;

import java.util.ArrayList;
import java.util.Date;

public class ProductModel {
    String productName;
    int price ;
    String imageUrl ;
    String productId ;
    String description;
    String totalAmount;
    Date orderTime;
    ArrayList<BasketModel> basket;

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductModel() {
    }

    public ProductModel(String productName, int price, String imageUrl, String productId, String description) {
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productId = productId;
        this.description = description;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public ArrayList<BasketModel> getBasket() {
        return basket;
    }

    public void setBasket(ArrayList<BasketModel> basket) {
        this.basket = basket;
    }

    public String getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }
}
