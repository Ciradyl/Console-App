/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.view.admin.order;

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
public class UpdateOrderStatus {

    private OrderService orderService;
    private Scanner scanner;

    // Constructors
    public UpdateOrderStatus(OrderService orderService, Scanner scanner) {
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void display() {
        Header.updateOrderStatusHeader();
        // Check if there are any products available
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("");
            System.out.println(MessageConstants.INVALID_ORDER_REF_INPUT_ERROR);
            System.out.println("");
            ConsoleUtils.pressEnterToContinue();
            return; // Exit the screen if no products are found
        }

        String orderReference;
        while (true) {
            System.out.print("Order Reference : ");
            String orderRef = scanner.nextLine();
            // Check if the input is null or empty
            if (orderRef == null || orderRef.isEmpty()) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_INPUT_ERROR);
                System.out.println("");
                continue; // Prompt user to enter Product ID again
            }

            orderReference = orderRef;

            // Check if product exists
            Order order = orderService.getOrderByReference(orderReference);
            if (order == null) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_ORDER_REF_INPUT_ERROR);
                System.out.println("");
                ConsoleUtils.pressEnterToContinue();
                continue; // Prompt user to enter Product ID again
            }

            break; // Exit loop if valid number and existing product ID is entered
        }
        
        // Loop for letting the user confirm or cancel the product.
        // as well as input validation.
        while (true) {
            System.out.println("");
            System.out.print("Are you sure you want to mark this order as delivered (Y/N): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                System.out.println("");
                System.out.println(MessageConstants.SUCCESS_ORDER_UPDATED_MESSAGE);
                System.out.println("");
                orderService.updateOrderStatusToDelivered(orderReference);
                break;
            } else if (confirm.equalsIgnoreCase("N")) {
                System.out.println("");
                System.out.println(MessageConstants.ACTION_CANCELLED_MESSAGE);
                System.out.println("");
                return;
            } else {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_CHAR_INPUT_ERROR);
                System.out.println("");
            }
            ConsoleUtils.pressEnterToContinue();
        }

    }
}
