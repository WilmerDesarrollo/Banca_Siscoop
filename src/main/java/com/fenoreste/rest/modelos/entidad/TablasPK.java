/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.rest.modelos.entidad;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author wilmer
 */
@Cacheable(false)
@Embeddable
public class TablasPK {
    @Column(name = "idtabla")
    private String idtabla;
    @Column(name = "idelemento")
    private String idelemento;

    public TablasPK() {
    }

    public TablasPK(String idtabla, String idelemento) {
        this.idtabla = idtabla;
        this.idelemento = idelemento;
    }

    public String getIdtabla() {
        return idtabla;
    }

    public void setIdtabla(String idtabla) {
        this.idtabla = idtabla;
    }

    public String getIdelemento() {
        return idelemento;
    }

    public void setIdelemento(String idelemento) {
        this.idelemento = idelemento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtabla != null ? idtabla.hashCode() : 0);
        hash += (idelemento != null ? idelemento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablasPK)) {
            return false;
        }
        TablasPK other = (TablasPK) object;
        if ((this.idtabla == null && other.idtabla != null) || (this.idtabla != null && !this.idtabla.equals(other.idtabla))) {
            return false;
        }
        return !((this.idelemento == null && other.idelemento != null) || (this.idelemento != null && !this.idelemento.equals(other.idelemento)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.TablasPK[ idtabla=" + idtabla + ", idelemento=" + idelemento + " ]";
    }

}
