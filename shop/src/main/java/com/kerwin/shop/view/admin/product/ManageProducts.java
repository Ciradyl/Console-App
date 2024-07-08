/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.view.admin.product;

import com.kerwin.shop.view.admin.product.AddProduct;
import com.kerwin.shop.model.Product;
import com.kerwin.shop.service.ProductService;
import com.kerwin.shop.ui.Header;
import com.kerwin.shop.ui.Menu;
import com.kerwin.shop.utils.MessageConstants;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lione
 */
public class ManageProducts {

    private ProductService productService;
    private Scanner scanner;

    public ManageProducts(ProductService productService, Scanner scanner) {
        this.productService = productService;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            List<Product> products = productService.getProducts();
            Header.manageProductsHeader();

            if (products.isEmpty()) {
                System.out.println(MessageConstants.NO_PRODUCT_MESSAGE);
            } else {
                System.out.println("ID\tName\t\t\tPrice");
                for (Product product : products) {
                    System.out.printf("%d\t%s\t%s%n", product.getId(), product.getName(), product.getPrice());
                }
            }

            Menu.manageProductMenu();

            System.out.println("");

            System.out.print("What do you want to do : ");
            String input = scanner.next();
            scanner.nextLine();

            if (input.equals("1")) {
                System.out.println("");
                System.out.println(MessageConstants.ADMIN_PRODUCT_ADD_SELECTED_MESSAGE);
                System.out.println("");
                new AddProduct(productService, scanner).display();
            } else if (input.equals("2")) {
                System.out.println("");
                System.out.println(MessageConstants.ADMIN_PRODUCT_UPDATE_SELECTED_MESSAGE);
                System.out.println("");
                new UpdateProduct(productService, scanner).display();
            } else if (input.equals("3")) {
                System.out.println("");
                System.out.println(MessageConstants.ADMIN_PRODUCT_REMOVE_SELECTED_MESSAGE);
                System.out.println("");
                new RemoveProduct(productService, scanner).display();
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
