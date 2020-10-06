package com.rajuuu.milkdiary.Model;

public class AllLinesModel {
    private String lineid;
    private String linename;
    private String salesmanname;
    private String mobilenumber;
    private String vehiclenumber;

    public AllLinesModel(String lineid,String linename, String salesmanname,String mobilenumber, String vehiclenumber) {
        this.lineid = lineid;
        this.linename = linename;
        this.salesmanname = salesmanname;
        this.mobilenumber = mobilenumber;
        this.vehiclenumber = vehiclenumber;
    }

    public String getLineid() {
        return lineid;
    }

    public void setLineid(String lineid) {
        this.lineid = lineid;
    }

    public String getLinename() {
        return linename;
    }

    public void setLinename(String linename) {
        this.linename = linename;
    }

    public String getSalesmanname() {
        return salesmanname;
    }

    public void setSalesmanname(String salesmanname) {
        this.salesmanname = salesmanname;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }
}

