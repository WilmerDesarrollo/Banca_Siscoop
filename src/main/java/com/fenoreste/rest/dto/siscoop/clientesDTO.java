/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.rest.dto.siscoop;

import com.fasterxml.jackson.core.SerializableString;
import java.io.Serializable;

/**
 *
 * @author root
 */
public class clientesDTO  implements  Serializable{
    
  /*****************************Los objetos son los que solitica la documentacion de las apis***********************************/  

    
private String ResponseCode;
private String customerId;
private String name;
private String taxId;
private String customerType;
private String emails;
private String phones;
private String mobile;
private String addresses;
private String countryCode;
private String state;
private String country;
private String city;
private String district;
private String street;
private String number;
private String apartament;
private String property1;
private String property2;

public clientesDTO() {

}

    public clientesDTO(String ResponseCode, String customerId, String name, String taxId, String customerType, String emails, String phones, String mobile, String addresses, String countryCode, String state, String country, String city, String district, String street, String number, String apartament, String property1, String property2) {
        this.ResponseCode = ResponseCode;
        this.customerId = customerId;
        this.name = name;
        this.taxId = taxId;
        this.customerType = customerType;
        this.emails = emails;
        this.phones = phones;
        this.mobile = mobile;
        this.addresses = addresses;
        this.countryCode = countryCode;
        this.state = state;
        this.country = country;
        this.city = city;
        this.district = district;
        this.street = street;
        this.number = number;
        this.apartament = apartament;
        this.property1 = property1;
        this.property2 = property2;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String ResponseCode) {
        this.ResponseCode = ResponseCode;
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

   public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getApartament() {
        return apartament;
    }

    public void setApartament(String apartament) {
        this.apartament = apartament;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    @Override
    public String toString() {
        return "clientesDTO{" + "ResponseCode=" + ResponseCode + ", customerId=" + customerId + ", name=" + name + ", taxId=" + taxId + ", customerType=" + customerType + ", emails=" + emails + ", phones=" + phones + ", mobile=" + mobile + ", addresses=" + addresses + ", countryCode=" + countryCode + ", state=" + state + ", country=" + country + ", city=" + city + ", district=" + district + ", street=" + street + ", number=" + number + ", apartament=" + apartament + ", property1=" + property1 + ", property2=" + property2 + '}';
    }

 





}
