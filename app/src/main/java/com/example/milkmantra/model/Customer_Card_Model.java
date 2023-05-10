package com.example.milkmantra.model;

public class Customer_Card_Model {


    String Date, transaction_customer_morning_cow_volume, transaction_morning_buffelo_volume, transaction_morning_other_volume, transaction_evening_cow_volume, transaction_evening_buffelo_volume, transaction_evening_other_volume,transaction_id,
            provider_id,customer_id,transaction_is_paid,transaction_day,transaction_month,transaction_year,transaction_is_active,transaction_timestamp;

    public Customer_Card_Model(String date, String transaction_customer_morning_cow_volume, String transaction_morning_buffelo_volume, String transaction_morning_other_volume, String transaction_evening_cow_volume, String transaction_evening_buffelo_volume, String transaction_evening_other_volume, String transaction_id, String provider_id, String customer_id, String transaction_is_paid, String transaction_day, String transaction_month, String transaction_year, String transaction_is_active, String transaction_timestamp) {
        Date = date;
        this.transaction_customer_morning_cow_volume = transaction_customer_morning_cow_volume;
        this.transaction_morning_buffelo_volume = transaction_morning_buffelo_volume;
        this.transaction_morning_other_volume = transaction_morning_other_volume;
        this.transaction_evening_cow_volume = transaction_evening_cow_volume;
        this.transaction_evening_buffelo_volume = transaction_evening_buffelo_volume;
        this.transaction_evening_other_volume = transaction_evening_other_volume;
        this.transaction_id = transaction_id;
        this.provider_id = provider_id;
        this.customer_id = customer_id;
        this.transaction_is_paid = transaction_is_paid;
        this.transaction_day = transaction_day;
        this.transaction_month = transaction_month;
        this.transaction_year = transaction_year;
        this.transaction_is_active = transaction_is_active;
        this.transaction_timestamp = transaction_timestamp;
    }

    public Customer_Card_Model() {

    }

    public void setDate(String date) {
        Date = date;
    }

    public void setTransaction_customer_morning_cow_volume(String transaction_customer_morning_cow_volume) {
        this.transaction_customer_morning_cow_volume = transaction_customer_morning_cow_volume;
    }

    public void setTransaction_morning_buffelo_volume(String transaction_morning_buffelo_volume) {
        this.transaction_morning_buffelo_volume = transaction_morning_buffelo_volume;
    }

    public void setTransaction_morning_other_volume(String transaction_morning_other_volume) {
        this.transaction_morning_other_volume = transaction_morning_other_volume;
    }

    public void setTransaction_evening_cow_volume(String transaction_evening_cow_volume) {
        this.transaction_evening_cow_volume = transaction_evening_cow_volume;
    }

    public void setTransaction_evening_buffelo_volume(String transaction_evening_buffelo_volume) {
        this.transaction_evening_buffelo_volume = transaction_evening_buffelo_volume;
    }

    public void setTransaction_evening_other_volume(String transaction_evening_other_volume) {
        this.transaction_evening_other_volume = transaction_evening_other_volume;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setTransaction_is_paid(String transaction_is_paid) {
        this.transaction_is_paid = transaction_is_paid;
    }

    public void setTransaction_day(String transaction_day) {
        this.transaction_day = transaction_day;
    }

    public void setTransaction_month(String transaction_month) {
        this.transaction_month = transaction_month;
    }

    public void setTransaction_year(String transaction_year) {
        this.transaction_year = transaction_year;
    }

    public void setTransaction_is_active(String transaction_is_active) {
        this.transaction_is_active = transaction_is_active;
    }

    public void setTransaction_timestamp(String transaction_timestamp) {
        this.transaction_timestamp = transaction_timestamp;
    }

    public String getDate() {
        return Date;
    }

    public String getTransaction_customer_morning_cow_volume() {
        return transaction_customer_morning_cow_volume;
    }

    public String getTransaction_morning_buffelo_volume() {
        return transaction_morning_buffelo_volume;
    }

    public String getTransaction_morning_other_volume() {
        return transaction_morning_other_volume;
    }

    public String getTransaction_evening_cow_volume() {
        return transaction_evening_cow_volume;
    }

    public String getTransaction_evening_buffelo_volume() {
        return transaction_evening_buffelo_volume;
    }

    public String getTransaction_evening_other_volume() {
        return transaction_evening_other_volume;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getTransaction_is_paid() {
        return transaction_is_paid;
    }

    public String getTransaction_day() {
        return transaction_day;
    }

    public String getTransaction_month() {
        return transaction_month;
    }

    public String getTransaction_year() {
        return transaction_year;
    }

    public String getTransaction_is_active() {
        return transaction_is_active;
    }

    public String getTransaction_timestamp() {
        return transaction_timestamp;
    }
}