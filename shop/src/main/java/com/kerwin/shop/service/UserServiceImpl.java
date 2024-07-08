/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.service;

import com.kerwin.shop.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lione
 */
public class UserServiceImpl implements UserService {

    private List<User> users = new ArrayList<>();

    public UserServiceImpl() {
        users.add(new User("admin", "admin", true));
        users.add(new User("customer", "customer", false));
    }

    @Override
    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
