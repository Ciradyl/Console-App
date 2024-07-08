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
public class UpdateProduct {

    private ProductService productService;
    private Scanner scanner;

    public UpdateProduct(ProductService productService, Scanner scanner) {
        this.productService = productService;
        this.scanner = scanner;
    }

    public void display() {
        Header.updateProductHeader();

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
            System.out.print("Product ID: ");
            String input = scanner.nextLine();

            if (input == null || input.isEmpty()) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_INPUT_ERROR);
                System.out.println("");
                continue; // Prompt user to enter Product ID again
            }

            try {
                productId = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_NUMBER_INPUT_ERROR);
                System.out.println("");
                continue; // Prompt user to enter Product ID again
            }

            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_ORDER_PRODUCT_ID_INPUT_ERROR);
                System.out.println("");
                continue; // Prompt user to enter a valid Product ID
            }

            // Valid product ID, proceed to update product details
            new UpdateProductDetails(productService, scanner).display(product);
            break;
        }
    }
}
