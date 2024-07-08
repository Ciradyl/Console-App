package com.kerwin.shop;

import com.kerwin.shop.service.OrderService;
import com.kerwin.shop.service.OrderServiceImpl;
import com.kerwin.shop.service.ProductService;
import com.kerwin.shop.service.ProductServiceImpl;
import com.kerwin.shop.service.UserService;
import com.kerwin.shop.service.UserServiceImpl;
import com.kerwin.shop.view.Welcome;
import java.util.Scanner;

/**
 *
 * @author lione
 */
public class Shop {

    public static void main(String[] args) {
        // Initializes the services when application starts
        UserService userService = new UserServiceImpl();
        ProductService productService = new ProductServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        Scanner scanner = new Scanner(System.in);
        
        // Creates and display Welcome Screen; entry point
        Welcome welcomeScreen = new Welcome(userService, productService, orderService, scanner);
        welcomeScreen.display();
    }

}
