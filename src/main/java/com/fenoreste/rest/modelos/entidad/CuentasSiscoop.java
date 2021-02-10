/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.rest.modelos.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author wilmer
 */
@Entity
@Table(name = "tipos_cuenta_siscoop")
public class CuentasSiscoop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private Integer idproducto;
    @Column(name="tipocuenta")
    private String tipocuenta;

    public CuentasSiscoop() {
    }

    public CuentasSiscoop(Integer idproducto, String tipocuenta) {
        this.idproducto = idproducto;
        this.tipocuenta = tipocuenta;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(String tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    @Override
    public String toString() {
        return "tiposCuentaSiscoop{" + "idproducto=" + idproducto + ", tipocuenta=" + tipocuenta + '}';
    }
    
    
    
    

}
