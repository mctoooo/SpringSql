package com.example.demo;

import java.util.List;

public class Product {
    private String prodName;
    private String prodType;
    private String prodAmt;
    private String prodPrice;
    private String prodDiscount;

    public Product(String[] arr) {
        this.prodName = arr[0];
        this.prodType = arr[1];
        this.prodAmt = arr[2];
        this.prodPrice = arr[3];
        this.prodDiscount = arr[4];

    }


    public String getProdName() {
        return prodName;
    }

    public String getProdType() {
        return prodType;
    }

    public String getProdAmt() {
        return prodAmt;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public String getProdDiscount() {
        return prodDiscount;
    }

}
