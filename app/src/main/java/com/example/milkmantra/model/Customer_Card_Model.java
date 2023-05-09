package com.example.milkmantra.model;

public class Customer_Card_Model {


    String Date,CowMorning,BuffelowMorning,OtherMorning,CowEvening,BuffelowEvening,OtherEvening;

    public Customer_Card_Model(){

    }


    public Customer_Card_Model(String date, String cowMorning, String buffelowMorning, String otherMorning, String cowEvening, String buffelowEvening, String otherEvening) {
        Date = date;
        CowMorning = cowMorning;
        BuffelowMorning = buffelowMorning;
        OtherMorning = otherMorning;
        CowEvening = cowEvening;
        BuffelowEvening = buffelowEvening;
        OtherEvening = otherEvening;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setCowMorning(String cowMorning) {
        CowMorning = cowMorning;
    }

    public void setBuffelowMorning(String buffelowMorning) {
        BuffelowMorning = buffelowMorning;
    }

    public void setOtherMorning(String otherMorning) {
        OtherMorning = otherMorning;
    }

    public void setCowEvening(String cowEvening) {
        CowEvening = cowEvening;
    }

    public void setBuffelowEvening(String buffelowEvening) {
        BuffelowEvening = buffelowEvening;
    }

    public void setOtherEvening(String otherEvening) {
        OtherEvening = otherEvening;
    }

    public String getDate() {
        return Date;
    }

    public String getCowMorning() {
        return CowMorning;
    }

    public String getBuffelowMorning() {
        return BuffelowMorning;
    }

    public String getOtherMorning() {
        return OtherMorning;
    }

    public String getCowEvening() {
        return CowEvening;
    }

    public String getBuffelowEvening() {
        return BuffelowEvening;
    }

    public String getOtherEvening() {
        return OtherEvening;
    }
}
