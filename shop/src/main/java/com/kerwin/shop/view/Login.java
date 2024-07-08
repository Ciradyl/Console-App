package com.kerwin.shop.view;

import com.kerwin.shop.view.admin.AdminHome;
import com.kerwin.shop.view.customer.CustomerHome;
import com.kerwin.shop.model.User;
import com.kerwin.shop.service.OrderService;
import com.kerwin.shop.service.ProductService;
import com.kerwin.shop.service.UserService;
import com.kerwin.shop.ui.Header;
import com.kerwin.shop.utils.ConsoleUtils;
import com.kerwin.shop.utils.MessageConstants;
import java.util.Scanner;

/**
 *
 * @author lione
 */
public class Login {

    private UserService userService;
    private ProductService productService;
    private OrderService orderService;
    private Scanner scanner;

    // constructors
    public Login(UserService userService, ProductService productService, OrderService orderService, Scanner scanner) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void display() {
        Header.loginHeader();

        System.out.print("Username : ");
        String username = scanner.next();
        scanner.nextLine();

        System.out.print("Password : ");
        String password = scanner.next();
        scanner.nextLine();

        // grabs the authenticate impl to check roles
        User user = userService.authenticate(username, password); 
        if (user != null) {
            // if admin
            if (user.isAdmin()) {
                System.out.println("");
                System.out.println(MessageConstants.ADMIN_AUTH_SELECTED_MESSAGE);
                System.out.println("");
                new AdminHome(productService, orderService, scanner).display();
            } else { // else customer
                System.out.println("");
                System.out.println(MessageConstants.CUSTOMER_AUTH_SELECTED_MESSAGE);
                System.out.println("");
                new CustomerHome(productService, orderService, scanner).display();
            }
        } else {
            System.out.println("");
            System.out.println("ERROR : Invalid credentials.");
            System.out.println("");
            ConsoleUtils.pressEnterToContinue();
        }
    }
}
