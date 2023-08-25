package com.example.billingsystem;

import java.io.Serializable;

public class Menu_Model implements Serializable {
    int quantity_counter=0;

    public int getQuantity_counter() {
        return quantity_counter;
    }

    public void setQuantity_counter(int quantity_counter) {
        this.quantity_counter = quantity_counter;
    }

    String img_url,item_category,item_name,item_price,item_status,doc_id;

    public Menu_Model(String img_url, String item_category, String item_name, String item_price, String item_status) {
        this.img_url = img_url;
        this.item_category = item_category;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_status = item_status;
    }
    public Menu_Model(){}


    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getItem_category() {
        return item_category;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_status() {
        return item_status;
    }

    public void setItem_status(String item_status) {
        this.item_status = item_status;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }
}
