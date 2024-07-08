/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kerwin.shop.service;

import com.kerwin.shop.model.Product;
import java.util.List;

/**
 *
 * @author lione
 */
public interface ProductService {

    List<Product> getProducts();

    void addProduct(Product product);

    void removeProduct(int productId);

    void updateProduct(Product product);

    Product getProductById(int productId);

    Product getProductByName(String name);

    public List<Product> getAllProducts();

}
