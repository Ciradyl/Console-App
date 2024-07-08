/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kerwin.shop.service;

import com.kerwin.shop.model.User;

/**
 *
 * @author lione
 */
public interface UserService {

    User authenticate(String username, String password);
}
