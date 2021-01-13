/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscoop.dto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 *
 * @author wilmer
 */
public class customerDTO implements Serializable {
    private String customerid;
    private String name;
    private String customertype;

    public customerDTO() {
    
    }

    public customerDTO(String customerid, String name, String customertype) {
        this.customerid = customerid;
        this.name = name;
        this.customertype = customertype;
    }
    
    

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    @Override
    public String toString() {
        return "customerDTO{" + "customerid=" + customerid + ", name=" + name + ", customertype=" + customertype + '}';
    }

}
