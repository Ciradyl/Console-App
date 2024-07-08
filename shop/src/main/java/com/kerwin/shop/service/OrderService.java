/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.service;

import com.kerwin.shop.model.Order;
import java.util.List;

/**
 *
 * @author lione
 */
public interface OrderService {

    List<Order> getAllOrders();
    
    Order addOrder(Order order);

    Order getOrderByReference(String reference);

    void updateOrderStatusToDelivered(String reference);
}
