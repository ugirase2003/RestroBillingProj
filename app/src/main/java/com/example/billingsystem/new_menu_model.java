package com.example.billingsystem;

public class new_menu_model
{
    String item_name,item_price,item_category;

    public new_menu_model(String item_name, String item_price, String item_category) {
        this.item_name = item_name;
        this.item_price = item_price;
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

    public String getItem_category() {
        return item_category;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }
}
