package com.rajuuu.milkdiary.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class AllOrders {

    private String orderdate;
    private String orderline;
    private String openbalance;
    private String total;
    private String closebalance;
    private String s;
    private String closebalance1;
    private ArrayList<HashMap<String, String>> remind_list;
    private ArrayList<HashMap<String, String>> remind_list2;


    public AllOrders(String orderdate, String orderline, String openbalance, String total, String closebalance, String s, String closebalance1, ArrayList<HashMap<String, String>> remind_list, ArrayList<HashMap<String, String>> remind_list2) {
        this.orderdate = orderdate;
        this.orderline = orderline;
        this.openbalance = openbalance;
        this.total = total;
        this.closebalance = closebalance;
        this.s = s;
        this.closebalance1 = closebalance1;
        this.remind_list = remind_list;
        this.remind_list2 = remind_list2;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrderline() {
        return orderline;
    }

    public void setOrderline(String orderline) {
        this.orderline = orderline;
    }

    public String getOpenbalance() {
        return openbalance;
    }

    public void setOpenbalance(String openbalance) {
        this.openbalance = openbalance;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getClosebalance() {
        return closebalance;
    }

    public void setClosebalance(String closebalance) {
        this.closebalance = closebalance;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getClosebalance1() {
        return closebalance1;
    }

    public void setClosebalance1(String closebalance1) {
        this.closebalance1 = closebalance1;
    }

    public ArrayList<HashMap<String, String>> getRemind_list() {
        return remind_list;
    }

    public void setRemind_list(ArrayList<HashMap<String, String>> remind_list) {
        this.remind_list = remind_list;
    }

    public ArrayList<HashMap<String, String>> getRemind_list2() {
        return remind_list2;
    }

    public void setRemind_list2(ArrayList<HashMap<String, String>> remind_list2) {
        this.remind_list2 = remind_list2;
    }
}
