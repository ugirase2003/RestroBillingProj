package com.example.billingsystem;

public class Record_Model {
    String c_name,c_number,generated_amt;

    public Record_Model() {
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_number() {
        return c_number;
    }

    public void setC_number(String c_number) {
        this.c_number = c_number;
    }

    public String getGenerated_amt() {
        return generated_amt;
    }

    public void setGenerated_amt(String generated_amt) {
        this.generated_amt = generated_amt;
    }

    public Record_Model(String c_name, String c_number, String generated_amt) {
        this.c_name = c_name;
        this.c_number = c_number;
        this.generated_amt = generated_amt;
    }
}
