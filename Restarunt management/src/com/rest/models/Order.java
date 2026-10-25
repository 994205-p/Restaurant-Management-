package com.rest.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int tableId;
    private final List<String> items = new ArrayList<>();
    private double totalAmount = 0.0;

    public Order(int tableId) {
        this.tableId = tableId;
    }

    public int getTableId() {
        return tableId;
    }

    public List<String> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void addItem(String itemName, double price) {
        items.add(itemName);
        totalAmount += price;
    }
}
