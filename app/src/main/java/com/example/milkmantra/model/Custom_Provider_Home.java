package com.example.milkmantra.model;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class Custom_Provider_Home {
    String customerId,ProviderId,CustomerVacationMode,CustomerMorningCowVolume,CustomerMorningBuffelowVolume,
    CustomerMorningOtherVolume,CustomerEveningCowVolume,CustomerEveningBuffelowVolume,CustomerEveningOtherVolume,
    CustomerIsActive,CustomerProviderIsActive,CustomerName,CustomerProviderTimeStamp,CustomerAddress,CustomerPhoneNumber,
    CustomerPincode,CustomerISActive,CustomerUniqueNo;


    public  Custom_Provider_Home(){

    }

    public Custom_Provider_Home(String customerId, String providerId, String customerVacationMode, String customerMorningCowVolume, String customerMorningBuffelowVolume, String customerMorningOtherVolume, String customerEveningCowVolume, String customerEveningBuffelowVolume, String customerEveningOtherVolume, String customerIsActive, String customerProviderIsActive, String customerName, String customerProviderTimeStamp, String customerAddress, String customerPhoneNumber, String customerPincode, String customerISActive, String customerUniqueNo) {
        this.customerId = customerId;
        ProviderId = providerId;
        CustomerVacationMode = customerVacationMode;
        CustomerMorningCowVolume = customerMorningCowVolume;
        CustomerMorningBuffelowVolume = customerMorningBuffelowVolume;
        CustomerMorningOtherVolume = customerMorningOtherVolume;
        CustomerEveningCowVolume = customerEveningCowVolume;
        CustomerEveningBuffelowVolume = customerEveningBuffelowVolume;
        CustomerEveningOtherVolume = customerEveningOtherVolume;
        CustomerIsActive = customerIsActive;
        CustomerProviderIsActive = customerProviderIsActive;
        CustomerName = customerName;
        CustomerProviderTimeStamp = customerProviderTimeStamp;
        CustomerAddress = customerAddress;
        CustomerPhoneNumber = customerPhoneNumber;
        CustomerPincode = customerPincode;
        CustomerISActive = customerISActive;
        CustomerUniqueNo = customerUniqueNo;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setProviderId(String providerId) {
        ProviderId = providerId;
    }

    public void setCustomerVacationMode(String customerVacationMode) {
        CustomerVacationMode = customerVacationMode;
    }

    public void setCustomerMorningCowVolume(String customerMorningCowVolume) {
        CustomerMorningCowVolume = customerMorningCowVolume;
    }

    public void setCustomerMorningBuffelowVolume(String customerMorningBuffelowVolume) {
        CustomerMorningBuffelowVolume = customerMorningBuffelowVolume;
    }

    public void setCustomerMorningOtherVolume(String customerMorningOtherVolume) {
        CustomerMorningOtherVolume = customerMorningOtherVolume;
    }

    public void setCustomerEveningCowVolume(String customerEveningCowVolume) {
        CustomerEveningCowVolume = customerEveningCowVolume;
    }

    public void setCustomerEveningBuffelowVolume(String customerEveningBuffelowVolume) {
        CustomerEveningBuffelowVolume = customerEveningBuffelowVolume;
    }

    public void setCustomerEveningOtherVolume(String customerEveningOtherVolume) {
        CustomerEveningOtherVolume = customerEveningOtherVolume;
    }

    public void setCustomerIsActive(String customerIsActive) {
        CustomerIsActive = customerIsActive;
    }

    public void setCustomerProviderIsActive(String customerProviderIsActive) {
        CustomerProviderIsActive = customerProviderIsActive;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public void setCustomerProviderTimeStamp(String customerProviderTimeStamp) {
        CustomerProviderTimeStamp = customerProviderTimeStamp;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        CustomerPhoneNumber = customerPhoneNumber;
    }

    public void setCustomerPincode(String customerPincode) {
        CustomerPincode = customerPincode;
    }

    public void setCustomerISActive(String customerISActive) {
        CustomerISActive = customerISActive;
    }

    public void setCustomerUniqueNo(String customerUniqueNo) {
        CustomerUniqueNo = customerUniqueNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProviderId() {
        return ProviderId;
    }

    public String getCustomerVacationMode() {
        return CustomerVacationMode;
    }

    public String getCustomerMorningCowVolume() {
        return CustomerMorningCowVolume;
    }

    public String getCustomerMorningBuffelowVolume() {
        return CustomerMorningBuffelowVolume;
    }

    public String getCustomerMorningOtherVolume() {
        return CustomerMorningOtherVolume;
    }

    public String getCustomerEveningCowVolume() {
        return CustomerEveningCowVolume;
    }

    public String getCustomerEveningBuffelowVolume() {
        return CustomerEveningBuffelowVolume;
    }

    public String getCustomerEveningOtherVolume() {
        return CustomerEveningOtherVolume;
    }

    public String getCustomerIsActive() {
        return CustomerIsActive;
    }

    public String getCustomerProviderIsActive() {
        return CustomerProviderIsActive;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getCustomerProviderTimeStamp() {
        return CustomerProviderTimeStamp;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public String getCustomerPhoneNumber() {
        return CustomerPhoneNumber;
    }

    public String getCustomerPincode() {
        return CustomerPincode;
    }

    public String getCustomerISActive() {
        return CustomerISActive;
    }

    public String getCustomerUniqueNo() {
        return CustomerUniqueNo;
    }
}
