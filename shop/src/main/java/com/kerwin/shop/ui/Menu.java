package com.kerwin.shop.ui;

/**
 *
 * @author lione
 */
public class Menu {

    public static void welcomeMenu() {
        System.out.println("1 - Login");
        System.out.println(".......................");
        System.out.println("0 - Exit");
    }

    public static void adminMenu() {
        System.out.println("1 - Manage Products");
        System.out.println("2 - Manage Orders");
        System.out.println("........................");
        System.out.println("0 - Logout");
    }

    public static void manageProductMenu() {
        System.out.println(".......................");
        System.out.println("1 - Add New Product");
        System.out.println("2 - Update Product");
        System.out.println("3 - Remove Product");
        System.out.println("0 - Back");
    }

    public static void manageOrdersMenu() {
        System.out.println(".......................");
        System.out.println("1 - Mark Order as Delivered");
        System.out.println("0 - Back");
    }

    public static void customerMenu() {
        System.out.println("1 - Shop");
        System.out.println("2 - My Orders");
        System.out.println("........................");
        System.out.println("0 - Logout");
    }

    public static void shopMenu() {
        System.out.println("........................");
        System.out.println("0 - Back");
    }
}
