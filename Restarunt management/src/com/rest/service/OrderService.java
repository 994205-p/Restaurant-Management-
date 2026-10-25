package com.rest.service;

import com.rest.models.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final List<Order> activeOrders = new ArrayList<>();

    public void createOrder(Order order) {
        activeOrders.add(order);
    }

    // Helps PaymentCli find how much a specific table owes
    public Order getOrderByTableId(int tableId) {
        for (Order o : activeOrders) {
            if (o.getTableId() == tableId) {
                return o;
            }
        }
        return null;
    }

    public void removeOrder(int tableId) {
        activeOrders.removeIf(o -> o.getTableId() == tableId);
    }
}
