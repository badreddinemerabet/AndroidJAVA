package com.example.realtimedbtest;

import android.content.Intent;

public class Product {

    private String Name;
    private String Lot;
    private String Code;
    private String  Price;
    private String SellPrice;
    private String UserId;
    private String  amount;
    String date;


    //Stock
    public Product(String name, String lot, String sellPrice, String amount) {
        Name = name;
        Lot = lot;
        SellPrice = sellPrice;
        this.amount = amount;
    }

    //History
    public Product(String name, String code, String sellPrice, String amount, String date) {
        Name = name;
        Code = code;
        SellPrice = sellPrice;
        this.amount = amount;
        this.date = date;
    }

    public Product() {
    }

    public Product(String name, String lot, String code, String price, String sellPrice, String userId, String amount, String date) {
        Name = name;
        Lot = lot;
        Code = code;
        Price = price;
        SellPrice = sellPrice;
        UserId = userId;
        this.amount = amount;
        this.date = date;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLot() {
        return Lot;
    }

    public void setLot(String lot) {
        Lot = lot;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(String sellPrice) {
        SellPrice = sellPrice;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String toStringg(){
        return this.Name+"\n"+" code  "+Code+"\n"+" amount "+amount+"\n"+" Sell Price "+SellPrice;
    }


}
