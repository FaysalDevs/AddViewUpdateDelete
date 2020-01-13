package com.advance.updatedeletefromfirebase;

public class ProductData {

    String pName;
    String pQuantity;

    public ProductData() {
    }

    public ProductData(String pName, String pQuantity) {
        this.pName = pName;
        this.pQuantity = pQuantity;
    }

    public String getpName() {
        return pName;
    }

    public String getpQuantity() {
        return pQuantity;
    }
}
