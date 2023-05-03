package com.example.milkmantra.model;

public class Customer_Add_Provider_model {
   String customer_id,customer_vacation_mode,customer_morning_cow_volume,customer_morning_buffelo_volume,customer_morning_other_volume,
           customer_evening_cow_volume,customer_evening_buffelo_volume,customer_evening_other_volume,
           customer_provider_is_active,provider_name,provider_id,provider_vacation_mode,provider_customer_associated;
    public Customer_Add_Provider_model()
    {

    }
    public Customer_Add_Provider_model(String customer_id, String customer_vacation_mode, String customer_morning_cow_volume, String customer_morning_buffelo_volume, String customer_morning_other_volume, String customer_evening_cow_volume, String customer_evening_buffelo_volume, String customer_evening_other_volume, String customer_provider_is_active, String provider_name, String provider_id, String provider_vacation_mode, String provider_customer_associated) {
        this.customer_id = customer_id;
        this.customer_vacation_mode = customer_vacation_mode;
        this.customer_morning_cow_volume = customer_morning_cow_volume;
        this.customer_morning_buffelo_volume = customer_morning_buffelo_volume;
        this.customer_morning_other_volume = customer_morning_other_volume;
        this.customer_evening_cow_volume = customer_evening_cow_volume;
        this.customer_evening_buffelo_volume = customer_evening_buffelo_volume;
        this.customer_evening_other_volume = customer_evening_other_volume;
        this.customer_provider_is_active = customer_provider_is_active;
        this.provider_name = provider_name;
        this.provider_id = provider_id;
        this.provider_vacation_mode = provider_vacation_mode;
        this.provider_customer_associated = provider_customer_associated;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_vacation_mode() {
        return customer_vacation_mode;
    }

    public String getCustomer_morning_cow_volume() {
        return customer_morning_cow_volume;
    }

    public String getCustomer_morning_buffelo_volume() {
        return customer_morning_buffelo_volume;
    }

    public String getCustomer_morning_other_volume() {
        return customer_morning_other_volume;
    }

    public String getCustomer_evening_cow_volume() {
        return customer_evening_cow_volume;
    }

    public String getCustomer_evening_buffelo_volume() {
        return customer_evening_buffelo_volume;
    }

    public String getCustomer_evening_other_volume() {
        return customer_evening_other_volume;
    }

    public String getCustomer_provider_is_active() {
        return customer_provider_is_active;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public String getProvider_vacation_mode() {
        return provider_vacation_mode;
    }

    public String getProvider_customer_associated() {
        return provider_customer_associated;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setCustomer_vacation_mode(String customer_vacation_mode) {
        this.customer_vacation_mode = customer_vacation_mode;
    }

    public void setCustomer_morning_cow_volume(String customer_morning_cow_volume) {
        this.customer_morning_cow_volume = customer_morning_cow_volume;
    }

    public void setCustomer_morning_buffelo_volume(String customer_morning_buffelo_volume) {
        this.customer_morning_buffelo_volume = customer_morning_buffelo_volume;
    }

    public void setCustomer_morning_other_volume(String customer_morning_other_volume) {
        this.customer_morning_other_volume = customer_morning_other_volume;
    }

    public void setCustomer_evening_cow_volume(String customer_evening_cow_volume) {
        this.customer_evening_cow_volume = customer_evening_cow_volume;
    }

    public void setCustomer_evening_buffelo_volume(String customer_evening_buffelo_volume) {
        this.customer_evening_buffelo_volume = customer_evening_buffelo_volume;
    }

    public void setCustomer_evening_other_volume(String customer_evening_other_volume) {
        this.customer_evening_other_volume = customer_evening_other_volume;
    }

    public void setCustomer_provider_is_active(String customer_provider_is_active) {
        this.customer_provider_is_active = customer_provider_is_active;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public void setProvider_vacation_mode(String provider_vacation_mode) {
        this.provider_vacation_mode = provider_vacation_mode;
    }

    public void setProvider_customer_associated(String provider_customer_associated) {
        this.provider_customer_associated = provider_customer_associated;
    }
}
