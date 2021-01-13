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
public class accountDTO implements Serializable {

    private static final long serialVersionUID = 1L;
     
    private String accountId;
    private String accountNumber;
    private String displayAccountNumber;
    private String accountTye;
    private String currencyCode;
    private String productCode;
    private boolean status;

    public accountDTO() {
    }

    public accountDTO(String accountId, String accountNumber, String displayAccountNumber, String accountTye, String currencyCode, String productCode, boolean status) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.displayAccountNumber = displayAccountNumber;
        this.accountTye = accountTye;
        this.currencyCode = currencyCode;
        this.productCode = productCode;
        this.status = status;
    }
    
    
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDisplayAccountNumber() {
        return displayAccountNumber;
    }

    public void setDisplayAccountNumber(String displayAccountNumber) {
        this.displayAccountNumber = displayAccountNumber;
    }

    public String getAccountTye() {
        return accountTye;
    }

    public void setAccountTye(String accountTye) {
        this.accountTye = accountTye;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "accountDTO{" + "accountId=" + accountId + ", accountNumber=" + accountNumber + ", displayAccountNumber=" + displayAccountNumber + ", accountTye=" + accountTye + ", currencyCode=" + currencyCode + ", productCode=" + productCode + ", status=" + status + '}';
    }
    
    
     
    
    
}
