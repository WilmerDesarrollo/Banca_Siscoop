/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.rest.dto.siscoop;

import java.io.Serializable;

/**
 *
 * @author wilmer
 */
public class customerDTO implements Serializable{
    
    private String customerId;
    private String name;
    private String taxId;
    private String customerType;
    private String email;
    private String phone;
    private String address;

    public customerDTO() {
    }

    public customerDTO(String customerId, String name, String taxId, String customerType, String email, String phone, String address) {
        this.customerId = customerId;
        this.name = name;
        this.taxId = taxId;
        this.customerType = customerType;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "customerDTO{" + "customerId=" + customerId + ", name=" + name + ", taxId=" + taxId + ", customerType=" + customerType + ", email=" + email + ", phone=" + phone + ", address=" + address + '}';
    }
    
       
    
}
