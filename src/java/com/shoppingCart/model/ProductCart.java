/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shoppingCart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Author     : Tien Nguyen
 * Created on : Mar 7, 2017, 10:48:35 AM
 *
 */
public class ProductCart {
       public final List cartItems;

    public ProductCart() {
        cartItems = new ArrayList();
    }

    
    public List<Product> getProduct(){
        List temp = new ArrayList();
        try {
            
            String url = "jdbc:derby://localhost:1527/ShopingCart";  
            Connection con = DriverManager.getConnection(url,"sa","sa");
            Statement stt = con.createStatement();
            ResultSet rs = stt.executeQuery("select * from product");
            while(rs.next()){
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4));
                temp.add(p);
            }
        } catch (Exception e) {
        }
        
        return temp;
    }
    
  
 
    public void addItem(int productId, String productName, String productType, float price, int quantity) {
        Product item = null;
        boolean match = false;
        for (int i = 0; i < cartItems.size(); i++) {
            if (((Product) cartItems.get(i)).getId()== productId) {
                item = (Product) cartItems.get(i);
                setAmount(getAmount() + quantity * item.getPrice());
                item.setQuantity(item.getQuantity() + quantity);
                match = true;
                break;
            }
            if (!match) {
                item = new Product();
                item.setId(productId);
                item.setName(productName);
                item.setType(productType);
                item.setPrice(price);
                setAmount(getAmount() + quantity * item.getPrice());
                item.setQuantity(quantity);
                cartItems.add(item);
            }
        }
    }

    public void removeItem(int productId) {
        for (int i = 0; i < cartItems.size(); i++) {
            Product item = (Product) cartItems.get(i);
            if (item.getId()== productId) {
                setAmount(getAmount() - item.getPrice() * item.getQuantity());
                cartItems.remove(i);
                break;
            }
        }
    }

    public List getCartItems() {
        return cartItems;
    }
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
