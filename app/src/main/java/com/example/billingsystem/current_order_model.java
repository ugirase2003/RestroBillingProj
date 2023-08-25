package com.example.billingsystem;

import java.util.ArrayList;

public class current_order_model {

    String  name;
    String total;
    String table_no;
    public ArrayList<String>item_list=new ArrayList<>();
    String docid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTable_no() {
        return table_no;
    }

    public void setTable_no(String table_no) {
        this.table_no = table_no;
    }


    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }



    public current_order_model() {
    }

    public current_order_model(String name, String total, String table_no, ArrayList<String> item_list) {
        this.name = name;
        this.total = total;
        this.table_no = table_no;
        this.item_list = item_list;
    }
}
