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
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lione
 */
public class RemoveProduct {

    private ProductService productService;
    private Scanner scanner;

    public RemoveProduct(ProductService productService, Scanner scanner) {
        this.productService = productService;
        this.scanner = scanner;
    }

    public void display() {
        Header.removeProductHeader();

        // Check if there are any products available
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println(MessageConstants.NO_PRODUCT_MESSAGE);
            System.out.println("........................");
            ConsoleUtils.pressEnterToContinue();
            return; // Exit the screen if no products are found
        }

        int productId;
        while (true) {
            System.out.print("Product ID : ");
            String idString = scanner.nextLine();

            if (idString == null || idString.isEmpty()) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_INPUT_ERROR);
                System.out.println("");
                continue; // Prompt user to enter Product ID again
            }

            try {
                productId = Integer.parseInt(idString);
            } catch (NumberFormatException e) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_NUMBER_INPUT_ERROR);
                System.out.println("");
                continue; // Prompt user to enter Product ID again
            }

            // Check if product exists
            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("");
                System.out.println("No product found with the given ID.");
                System.out.println("");
                continue; // Prompt user to enter Product ID again
            }

            break; // Exit loop if valid number and existing product ID is entered
        }

        while (true) {
            System.out.println("");
            System.out.print("Are you sure you want to remove this product (Y/N): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                productService.removeProduct(productId);
                System.out.println("");
                System.out.println(MessageConstants.SUCCESS_PRODUCT_REMOVED_MESSAGE);
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
            }
        }

        ConsoleUtils.pressEnterToContinue();
    }
}
