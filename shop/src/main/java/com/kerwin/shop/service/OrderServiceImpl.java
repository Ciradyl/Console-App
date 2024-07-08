/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.service;

import com.kerwin.shop.model.Order;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lione
 */
public class OrderServiceImpl implements OrderService {

    private List<Order> orders = new ArrayList<>();

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders); // Return a copy of the orders list
    }

    @Override
    public Order addOrder(Order order) {
        orders.add(order);
        return order;
    }

    @Override
    public Order getOrderByReference(String reference) {
        for (Order order : orders) {
            if (order.getReference().equals(reference)) {
                return order;
            }
        }
        return null; // Order not found
    }

    @Override
    public void updateOrderStatusToDelivered(String reference) {
        for (Order order : orders) {
            if (order.getReference().equals(reference)) {
                order.setStatus("DELIVERED");
                return;
            }
        }
    }

    // Additional methods for managing orders can be added here
}
