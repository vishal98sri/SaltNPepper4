package com.example.vishal.saltnpepper;

public class itemfood {

    private String name;
    private String cost;
    private String cuisine;
    private String num;

    public itemfood(String head, String desc, String off, String rat) {
        this.name = head;
        this.cost = desc;
        this.cuisine = off;
        this.num = rat;
    }

    public String getname() {
        return name;
    }

    public String getcost() {
        return cost;
    }

    public String getcuisine() {
        return cuisine;
    }

    public String getnum() {
        return num;
    }
}
