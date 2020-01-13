package com.advance.updatedeletefromfirebase;

public class AddModel {

    private String pName;
    private String pQuantity;

    public AddModel() {
    }

    public AddModel(String pName, String pQuantity) {
        this.pName = pName;
        this.pQuantity = pQuantity;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(String pQuantity) {
        this.pQuantity = pQuantity;
    }
}
