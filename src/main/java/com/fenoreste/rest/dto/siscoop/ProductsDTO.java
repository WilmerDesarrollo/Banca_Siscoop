/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.rest.dto.siscoop;

/**
 *
 * @author Elliot
 */
public class ProductsDTO {

    private Integer productCode;
    private String accountType;
    private String description;
    private String currencyCode;

    public ProductsDTO() {
    }

    public ProductsDTO(Integer productCode, String accountType, String description, String currencyCode) {
        this.productCode = productCode;
        this.accountType = accountType;
        this.description = description;
        this.currencyCode = currencyCode;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "ProductsDTO{" + "productCode=" + productCode + ", accountType=" + accountType + ", description=" + description + ", currencyCode=" + currencyCode + '}';
    }
    
    

}
