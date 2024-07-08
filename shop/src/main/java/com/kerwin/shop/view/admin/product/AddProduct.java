/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.view.admin.product;

import com.kerwin.shop.model.Product;
import com.kerwin.shop.service.ProductService;
import com.kerwin.shop.ui.Header;
import com.kerwin.shop.utils.ConsoleUtils;
import com.kerwin.shop.utils.MessageConstants;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author lione
 */
public class AddProduct {

    private ProductService productService;
    private Scanner scanner;

    // Constructors
    public AddProduct(ProductService productService, Scanner scanner) {
        this.productService = productService;
        this.scanner = scanner;
    }
    
    public void display() {
        Header.addProductHeader();
        // Loop 1: Repeat name input if validation wrong.
        String name;
        while (true) {
            System.out.print("Name : ");
            name = scanner.nextLine();
            // Check if input is null or empty
            if (name == null || name.trim().isEmpty()) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_INPUT_ERROR);
                System.out.println("");
                continue;
            }

            // Check if product name already exists
            if (productService.getProductByName(name) != null) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_PRODUCT_NAME_ERROR);
                System.out.println("");
                continue; // Prompt user to enter a different product name
            }
            break;
        }

        // Loop 2: Repeat price input if validation wrong.
        BigDecimal price;
        while (true) {
            System.out.print("Price : ");
            String priceString = scanner.nextLine();
            try {
                price = new BigDecimal(priceString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_NUMBER_INPUT_ERROR);
                System.out.println("");
            }
        }

        // Loop 3: Repeat Confirmation if invalid character.
        while (true) {
            System.out.println("");
            System.out.print("Are you sure you want to add this product (Y/N): ");
            String confirm = scanner.nextLine();

            System.out.println("");
            if (confirm.equalsIgnoreCase("Y")) {
                Product product = new Product(0, name, price);
                productService.addProduct(product);

                System.out.println("");
                System.out.println(MessageConstants.SUCCESS_PRODUCT_ADDED_MESSAGE);
                System.out.print("");
                break;
            } else if (confirm.equalsIgnoreCase("N")) {
                System.out.print("");
                System.out.println(MessageConstants.ACTION_CANCELLED_MESSAGE);
                System.out.print("");
                break;
            } else {
                System.out.print("");
                System.out.println(MessageConstants.INVALID_CHAR_INPUT_ERROR);
                System.out.print("");

            }
        }

        // Return to Manage Product Screen
        ConsoleUtils.pressEnterToContinue();

    }
}
