/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.view.customer.shop;

import com.kerwin.shop.model.Product;
import com.kerwin.shop.service.OrderService;
import com.kerwin.shop.service.ProductService;
import com.kerwin.shop.ui.Header;
import com.kerwin.shop.ui.Menu;
import com.kerwin.shop.utils.ConsoleUtils;
import com.kerwin.shop.utils.MessageConstants;
import com.kerwin.shop.view.customer.order.PlaceOrder;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lione
 */
public class MyShop {

    private ProductService productService;
    private OrderService orderService;
    private Scanner scanner;

    public MyShop(ProductService productService, OrderService orderService, Scanner scanner) {
        this.productService = productService;
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void display() {
        Header.shopHeader();

        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println(MessageConstants.NO_PRODUCT_MESSAGE);
            System.out.println("........................");
            ConsoleUtils.pressEnterToContinue();
            return; // Exit the screen if no products are found
        } else {
            System.out.println("ID\tName\t\tPrice");
            for (Product product : products) {
                System.out.printf("%d\t%s\t%s%n", product.getId(), product.getName(), product.getPrice());
            }
        }

        Menu.shopMenu();
        System.out.println("");

        int productId;
        Product selectedProduct = null;

        while (true) {
            System.out.print("What do you want to Order: ");
            String input = scanner.nextLine();

            if (input == null || input.isEmpty()) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_INPUT_ERROR);
                System.out.println("");
                continue; // Prompt user to enter Product ID again
            }

            if (input.equals("0")) {
                System.out.println("");
                System.out.println(MessageConstants.CUSTOMER_AUTH_SELECTED_MESSAGE);
                System.out.println("");
                return; // Return to Customer Home Screen
            }

            try {
                productId = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_NUMBER_INPUT_ERROR);
                System.out.println("");
                continue; // Prompt user to enter Product ID again
            }

            selectedProduct = productService.getProductById(productId);
            if (selectedProduct == null) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_ORDER_PRODUCT_ID_INPUT_ERROR);
                System.out.println("");
                continue; // Prompt user to enter Product ID again
            }

            break; // Exit loop if valid number and existing product ID is entered
        }

        while (true) {
            System.out.println("");
            System.out.print("Are you sure you want to buy this product (Y/N): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                System.out.println("");
                System.out.println(MessageConstants.CUSTOMER_PLACE_ORDER_MESSAGE);
                System.out.println("");
                new PlaceOrder(productService, orderService, scanner).display(selectedProduct);
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
