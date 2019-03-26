package com.example.vishal.saltnpepper;

public class itemcart {

    private String name;
    private String cost;
    private String num;

    public itemcart(String head, String desc, String off) {
        this.name = head;
        this.cost = desc;
        this.num = off;
    }

    public String getname() {
        return name;
    }

    public String getcost() {
        return cost;
    }

    public String getnum() {
        return num;
    }

}
