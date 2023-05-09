package com.example.milkmantra.model;

public class Provider_Customer_Report {
    String no,name;



    public  Provider_Customer_Report(){

    }

    public Provider_Customer_Report(String no, String name) {
        this.no = no;
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }
}

