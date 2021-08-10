package com.example.realtimedbtest;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Module extends Application {

    public ArrayList<String> list = new ArrayList<>();
    public ArrayAdapter<String> listAdapter;
    public String itemID;
    public String ItemName;

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }


    public void setItemID(Product product) {

    }
}
