/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.view.customer.order;

import com.kerwin.shop.model.Order;
import com.kerwin.shop.service.OrderService;
import com.kerwin.shop.ui.Header;
import com.kerwin.shop.utils.ConsoleUtils;
import com.kerwin.shop.utils.MessageConstants;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lione
 */
public class MyOrders {

    private OrderService orderService;
    private Scanner scanner;

    public MyOrders(OrderService orderService, Scanner scanner) {
        this.orderService = orderService;
        this.scanner = scanner;
    }
    
    public void display() {
        Header.myOrdersHeader();

        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println(MessageConstants.NO_ORDER_MESSAGE);
            System.out.println("........................");
            ConsoleUtils.pressEnterToContinue();
        } else {
            System.out.println("Date\t\t\t\tReference\tName\tPrice\tQty\tTotal\t\tStatus");
            for (Order order : orders) {
                System.out.printf("%s\t%s\t%s\t%s\t%d\t%s\t%s%n",
                        order.getFormattedDate(),
                        order.getReference(),
                        order.getProduct().getName(),
                        order.getProduct().getPrice(),
                        order.getQuantity(),
                        order.getTotal(),
                        order.getStatus());
            }

            System.out.println("");
            ConsoleUtils.pressEnterToContinue();
        }
    }
}
