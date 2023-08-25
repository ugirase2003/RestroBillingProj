package com.example.billingsystem;

import java.io.Serializable;

public class Offer_Model implements Serializable {
    String offer_above_price,offer_desc,offer_disc_price,doc_id;

    public Offer_Model(String offer_disc_price,String offer_above_price, String offer_desc) {
        this.offer_above_price = offer_above_price;
        this.offer_desc = offer_desc;
        this.offer_disc_price = offer_disc_price;
    }

    public String getOffer_above_price() {
        return offer_above_price;
    }

    public void setOffer_above_price(String offer_above_price) {
        this.offer_above_price = offer_above_price;
    }

    public String getOffer_desc() {
        return offer_desc;
    }

    public void setOffer_desc(String offer_desc) {
        this.offer_desc = offer_desc;
    }

    public String getOffer_disc_price() {
        return offer_disc_price;
    }

    public void setOffer_disc_price(String offer_disc_price) {
        this.offer_disc_price = offer_disc_price;
    }

    public Offer_Model() {
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }
}
