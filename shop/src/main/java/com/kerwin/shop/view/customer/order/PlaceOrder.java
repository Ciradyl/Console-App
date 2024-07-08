/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.view.customer.order;

import com.kerwin.shop.model.Order;
import com.kerwin.shop.model.Product;
import com.kerwin.shop.service.OrderService;
import com.kerwin.shop.service.ProductService;
import com.kerwin.shop.ui.Header;
import com.kerwin.shop.utils.ConsoleUtils;
import com.kerwin.shop.utils.MessageConstants;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author lione
 */
public class PlaceOrder {

    private ProductService productService;
    private OrderService orderService;
    private Scanner scanner;

    public PlaceOrder(ProductService productService, OrderService orderService, Scanner scanner) {
        this.productService = productService;
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void display(Product product) {
        Header.placeOrderHeader();
        System.out.println("Name  : " + product.getName());
        System.out.println("Price : Php " + product.getPrice());
        System.out.println("........................");

        int quantity;
        while (true) {
            System.out.print("How many do you want: ");
            String input = scanner.nextLine();

            if (input == null || input.isEmpty()) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_INPUT_ERROR);
                System.out.println("");
                continue; // Prompt user to enter quantity again
            }

            try {
                quantity = Integer.parseInt(input);
                if (quantity <= 0) {
                    System.out.println("");
                    System.out.println(MessageConstants.INVALID_INPUT_ERROR);
                    System.out.println("");
                    continue; // Quantity should be positive
                }
            } catch (NumberFormatException e) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_NUMBER_INPUT_ERROR);
                System.out.println("");
                continue; // Prompt user to enter quantity again
            }

            break; // Exit loop if valid quantity is entered
        }

        BigDecimal totalAmount = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        System.out.println("");
        System.out.println("That would be Php " + totalAmount + ".");
        System.out.print("Proceed with your order (Y/N): ");
        String confirm = scanner.nextLine();

        while (true) {
            if (confirm.equalsIgnoreCase("Y")) {
                String reference = ConsoleUtils.generateOrderReference();
                LocalDateTime orderDate = LocalDateTime.now();
                Order order = new Order(reference, product, quantity, totalAmount, "FOR_DELIVERY", orderDate);
                orderService.addOrder(order);

                System.out.println("");
                System.out.println(MessageConstants.CUSTOMER_PLACE_ORDER_MESSAGE);
                System.out.println("");
                break;
            } else if (confirm.equalsIgnoreCase("N")) {
                System.out.println("");
                System.out.println(MessageConstants.ACTION_CANCELLED_MESSAGE);
                System.out.println("");
                break;
            } else {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_CHAR_INPUT_ERROR);
                System.out.println("");
                System.out.print("Proceed with your order (Y/N): ");
                confirm = scanner.nextLine();
            }
        }

        ConsoleUtils.pressEnterToContinue();
    }
}
