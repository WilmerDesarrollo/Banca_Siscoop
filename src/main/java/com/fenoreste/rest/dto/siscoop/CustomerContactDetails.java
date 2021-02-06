/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.rest.dto.siscoop;

/**
 *
 * @author wilmer
 */
public class CustomerContactDetails {
    
    private String customerContactId;
    private String customerContactType;
    private String phoneNumber;
    private String cellphoneNumber;
    private String email;

    public CustomerContactDetails() {
    }

    public CustomerContactDetails(String customerContactId, String customerContactType, String phoneNumber, String cellphoneNumber, String email) {
        this.customerContactId = customerContactId;
        this.customerContactType = customerContactType;
        this.phoneNumber = phoneNumber;
        this.cellphoneNumber = cellphoneNumber;
        this.email = email;
    }

    public String getCustomerContactId() {
        return customerContactId;
    }

    public void setCustomerContactId(String customerContactId) {
        this.customerContactId = customerContactId;
    }

    public String getCustomerContactType() {
        return customerContactType;
    }

    public void setCustomerContactType(String customerContactType) {
        this.customerContactType = customerContactType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerContactDetails{" + "customerContactId=" + customerContactId + ", customerContactType=" + customerContactType + ", phoneNumber=" + phoneNumber + ", cellphoneNumber=" + cellphoneNumber + ", email=" + email + '}';
    }
    
    

}