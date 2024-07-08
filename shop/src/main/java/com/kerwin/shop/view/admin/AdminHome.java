/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.view.admin;

import com.kerwin.shop.view.admin.product.ManageProducts;
import com.kerwin.shop.view.admin.order.ManageOrders;
import com.kerwin.shop.service.OrderService;
import com.kerwin.shop.service.ProductService;
import com.kerwin.shop.ui.Header;
import com.kerwin.shop.ui.Menu;
import com.kerwin.shop.utils.MessageConstants;
import java.util.Scanner;

/**
 *
 * @author lione
 */
public class AdminHome {

    private ProductService productService;
    private OrderService orderService;
    private Scanner scanner;

    // Constructors
    public AdminHome(ProductService productService, OrderService orderService, Scanner scanner) {
        this.productService = productService;
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            Header.adminHeader();
            Menu.adminMenu();

            System.out.println("");

            System.out.print("What do you want to do : ");
            String input = scanner.next();
            scanner.nextLine();

            if (input.equals("1")) {
                System.out.println("");
                System.out.println(MessageConstants.ADMIN_PRODUCT_SELECTED_MESSAGE);
                System.out.println("");
                new ManageProducts(productService, scanner).display();
            } else if (input.equals("2")) {
                System.out.println("");
                System.out.println(MessageConstants.ADMIN_ORDER_SELECTED_MESSAGE);
                System.out.println("");
                new ManageOrders(orderService, scanner).display();
            } else if (input.equals("0")) {
                System.out.println("");
                System.out.println(MessageConstants.LOGOUT_SELECTED_MESSAGE);
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
