/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.view.admin.product;

import com.kerwin.shop.model.Product;
import com.kerwin.shop.service.ProductService;
import com.kerwin.shop.utils.ConsoleUtils;
import com.kerwin.shop.utils.MessageConstants;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author lione
 */
public class UpdateProductDetails {

    private ProductService productService;
    private Scanner scanner;

    public UpdateProductDetails(ProductService productService, Scanner scanner) {
        this.productService = productService;
        this.scanner = scanner;
    }

    public void display(Product product) {
        System.out.println("Updating Product: " + product.getName());

        // Update Product Name
        String newName;
        while (true) {
            System.out.print("New Product Name (leave blank to keep current): ");
            newName = scanner.nextLine();

            if (newName != null && !newName.trim().isEmpty()) {
                // Check if product name already exists
                if (productService.getProductByName(newName) != null) {
                    System.out.println("");
                    System.out.println(MessageConstants.INVALID_PRODUCT_NAME_ERROR);
                    System.out.println("");
                    continue; // Prompt user to enter a different product name
                }
                product.setName(newName);
            }
            break;
        }

        // Update Product Price
        BigDecimal newPrice;
        while (true) {
            System.out.print("New Product Price (leave blank to keep current): ");
            String priceInput = scanner.nextLine();

            if (priceInput == null || priceInput.trim().isEmpty()) {
                break; // Keep current price
            }

            try {
                newPrice = new BigDecimal(priceInput);
                product.setPrice(newPrice);
                break;
            } catch (NumberFormatException e) {
                System.out.println("");
                System.out.println(MessageConstants.INVALID_NUMBER_INPUT_ERROR);
                System.out.println("");
            }
        }

        // Save the updated product
        productService.updateProduct(product);

        System.out.println("");
        System.out.println(MessageConstants.SUCCESS_PRODUCT_UPDATED_MESSAGE);
        System.out.println("");

        ConsoleUtils.pressEnterToContinue();
    }

}
