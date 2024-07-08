/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.utils;

import java.io.IOException;
import java.util.Random;

/**
 *
 * @author lione
 */
public class ConsoleUtils {

    public static void pressEnterToContinue() {
        System.out.println("");
        System.out.println(MessageConstants.PRESS_ENTER_TO_CONTINUE_MESSAGE);
        try {
            System.in.read();
        } catch (IOException e) {
            System.err.println(MessageConstants.INVALID_CONSOLE_READ_ERROR);
        }
    }

    public static String generateOrderReference() {
        Random random = new Random();
        StringBuilder reference = new StringBuilder();
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i = 0; i < 5; i++) {
            char randomChar = alphanumeric.charAt(random.nextInt(alphanumeric.length()));
            reference.append(randomChar);
        }

        return reference.toString();
    }
}
