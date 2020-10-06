package com.rajuuu.milkdiary.Model;

public class AllProductsModel {

    private String productid;
    private String productname;
    private String amount;
    private String lastupdate;


    public AllProductsModel(String productid, String productname, String amount,String lastupdate) {
        this.productid = productid;
        this.productname = productname;
        this.amount = amount;
        this.lastupdate = lastupdate;

    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }
}
