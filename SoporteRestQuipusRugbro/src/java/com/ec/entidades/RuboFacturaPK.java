/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Cchristico
 */
@Embeddable
public class RuboFacturaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FACTU")
    private int idFactu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RUBRO")
    private int idRubro;

    public RuboFacturaPK() {
    }

    public RuboFacturaPK(int idFactu, int idRubro) {
        this.idFactu = idFactu;
        this.idRubro = idRubro;
    }

    public int getIdFactu() {
        return idFactu;
    }

    public void setIdFactu(int idFactu) {
        this.idFactu = idFactu;
    }

    public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFactu;
        hash += (int) idRubro;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuboFacturaPK)) {
            return false;
        }
        RuboFacturaPK other = (RuboFacturaPK) object;
        if (this.idFactu != other.idFactu) {
            return false;
        }
        if (this.idRubro != other.idRubro) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.RuboFacturaPK[ idFactu=" + idFactu + ", idRubro=" + idRubro + " ]";
    }
    
}
