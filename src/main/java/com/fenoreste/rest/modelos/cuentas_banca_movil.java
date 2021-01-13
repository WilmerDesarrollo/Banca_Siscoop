/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.rest.modelos;

import java.io.Serializable;
import javax.persistence.Cacheable;
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
@Cacheable(false)
@Entity
@Table(name = "cuentas_banca_movil")
public class cuentas_banca_movil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="validationid")
    private Integer validationid;
    @Column
    private Integer originator_referenced_id;
    @Column
    private String accountnumber;
    @Column
    private String displayaccountnumber;
    @Column
    private Integer execution_id;
    @Column
    private boolean status;
    @Column
    private Integer idorigen;
    @Column
    private Integer idgrupo;
    @Column
    private Integer idsocio;
    @Column
    private Integer idorigenp;
    @Column
    private Integer idproducto;
    @Column
    private Integer idauxiliar;
    @Column
    private String razon_cambio;

    public cuentas_banca_movil() {
    }

    public Integer getValidationid() {
        return validationid;
    }

    public void setValidationid(Integer validationid) {
        this.validationid = validationid;
    }

    public Integer getOriginator_referenced_id() {
        return originator_referenced_id;
    }

    public void setOriginator_referenced_id(Integer originator_referenced_id) {
        this.originator_referenced_id = originator_referenced_id;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getDisplayaccountnumber() {
        return displayaccountnumber;
    }

    public void setDisplayaccountnumber(String displayaccountnumber) {
        this.displayaccountnumber = displayaccountnumber;
    }

    public Integer getExecution_id() {
        return execution_id;
    }

    public void setExecution_id(Integer execution_id) {
        this.execution_id = execution_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getIdorigen() {
        return idorigen;
    }

    public void setIdorigen(Integer idorigen) {
        this.idorigen = idorigen;
    }

    public Integer getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Integer getIdsocio() {
        return idsocio;
    }

    public void setIdsocio(Integer idsocio) {
        this.idsocio = idsocio;
    }

    public Integer getIdorigenp() {
        return idorigenp;
    }

    public void setIdorigenp(Integer idorigenp) {
        this.idorigenp = idorigenp;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Integer getIdauxiliar() {
        return idauxiliar;
    }

    public void setIdauxiliar(Integer idauxiliar) {
        this.idauxiliar = idauxiliar;
    }

    public String getRazon_cambio() {
        return razon_cambio;
    }

    public void setRazon_cambio(String razon_cambio) {
        this.razon_cambio = razon_cambio;
    }

    @Override
    public String toString() {
        return "cuentas{" + "validationid=" + validationid + ", originator_referenced_id=" + originator_referenced_id + ", accountnumber=" + accountnumber + ", displayaccountnumber=" + displayaccountnumber + ", execution_id=" + execution_id + ", status=" + status + ", idorigen=" + idorigen + ", idgrupo=" + idgrupo + ", idsocio=" + idsocio + ", idorigenp=" + idorigenp + ", idproducto=" + idproducto + ", idauxiliar=" + idauxiliar + ", razon_cambio=" + razon_cambio + '}';
    }

}
