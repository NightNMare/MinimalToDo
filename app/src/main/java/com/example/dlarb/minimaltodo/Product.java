package com.example.dlarb.minimaltodo;

public class Product {

    public Product(String productTitle, String productContent) {
        this.productTitle = productTitle;
        this.productContent = productContent;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    String productTitle;
    String productContent;
}
