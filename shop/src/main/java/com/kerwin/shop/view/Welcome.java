package com.kerwin.shop.view;

import com.kerwin.shop.service.OrderService;
import com.kerwin.shop.service.ProductService;
import com.kerwin.shop.service.UserService;
import com.kerwin.shop.ui.Header;
import com.kerwin.shop.ui.Menu;
import com.kerwin.shop.utils.MessageConstants;
import java.util.Scanner;

/**
 *
 * @author lione
 */
public class Welcome {

    private UserService userService;
    private ProductService productService;
    private OrderService orderService;
    private Scanner scanner;

    // constructors
    public Welcome(UserService userService, ProductService productService, OrderService orderService, Scanner scanner) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            Header.welcomeHeader();
            Menu.welcomeMenu();

            System.out.println("");

            System.out.print("What do you want to do? : ");
            String input = scanner.next();
            scanner.nextLine();

            if (input.equals("1")) {
                System.out.println("");
                System.out.println(MessageConstants.LOGIN_SELECTED_MESSAGE);
                System.out.println("");
                new Login(userService, productService, orderService, scanner).display();
            } else if (input.equals("0")) {
                System.out.println("");
                System.out.println(MessageConstants.EXIT_MESSAGE);
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
