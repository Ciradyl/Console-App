/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kerwin.shop.service;

import com.kerwin.shop.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lione
 */
public class ProductServiceImpl implements ProductService {

    private List<Product> products = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
    }

    @Override
    public void removeProduct(int productId) {
        products.removeIf(product -> product.getId() == productId);
    }

    @Override
    public void updateProduct(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId() == updatedProduct.getId()) {
                products.set(i, updatedProduct);
                return;
            }
        }
    }

    @Override
    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null; // Product not found
    }

    @Override
    public Product getProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products); // Return a copy of the product list
    }
}
