/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.view.admin.order;

import com.kerwin.shop.model.Order;
import com.kerwin.shop.service.OrderService;
import com.kerwin.shop.ui.Header;
import com.kerwin.shop.ui.Menu;
import com.kerwin.shop.utils.ConsoleUtils;
import com.kerwin.shop.utils.MessageConstants;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lione
 */
public class ManageOrders {

    private OrderService orderService;
    private Scanner scanner;

    // Constructors
    public ManageOrders(OrderService orderService, Scanner scanner) {
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            List<Order> orders = orderService.getAllOrders();
            Header.manageOrdersHeader();
            // shows No Order if empty
            if (orders.isEmpty()) {
                System.out.println(MessageConstants.NO_ORDER_MESSAGE);
                System.out.println("........................");
                ConsoleUtils.pressEnterToContinue();
                return;
            } else {
                // Table area
                System.out.println("Date\t\t\t\tReference\t\tProduct Name\tPrice\tQty\tTotal\tStatus");
                for (Order order : orders) {
                    System.out.printf("%s\t%s\t%s\t%s\t%d\t%s\t%s\n",
                            order.getDate(), 
                            order.getReference(), 
                            order.getProduct().getName(),
                            order.getProduct().getPrice(), 
                            order.getQuantity(), 
                            order.getTotal(), 
                            order.getStatus());
                }
                Menu.manageOrdersMenu();
            }

            System.out.println("");

            System.out.print("What do you want to do : ");
            String input = scanner.next();
            scanner.nextLine();

            if (input.equals("1")) {
                System.out.println("");
                System.out.println(MessageConstants.ADMIN_ORDER_UPDATE_SELECTED_MESSAGE);
                System.out.println("");
                new UpdateOrderStatus(orderService, scanner).display();
            } else if (input.equals("0")) {
                System.out.println("");
                System.out.println(MessageConstants.ADMIN_AUTH_SELECTED_MESSAGE);
                System.out.println("");
                break;
            } else {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_INPUT_ERROR);
                System.out.println("");
            }

        }
    }
}
