/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.rest.modelos.entidad;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Elliot
 */
@Cacheable(true)
@Entity
@Table(name = "paises")
public class Paises {

    @Id
    @Column(name = "idpais")
    private Integer idpais;
    @Column(name="nombre")
    private String nombre;

    public Paises() {
    }

    public Paises(Integer idpais, String nombre) {
        this.idpais = idpais;
        this.nombre = nombre;
    }

    public Integer getIdpais() {
        return idpais;
    }

    public void setIdpais(Integer idpais) {
        this.idpais = idpais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Paises{" + "idpais=" + idpais + ", nombre=" + nombre + '}';
    }
    
    
    

}
