/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cchristico
 */
@Entity
@Table(name = "rubo_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RuboFactura.findAll", query = "SELECT r FROM RuboFactura r"),
    @NamedQuery(name = "RuboFactura.findByIdFactu", query = "SELECT r FROM RuboFactura r WHERE r.ruboFacturaPK.idFactu = :idFactu"),
    @NamedQuery(name = "RuboFactura.findByIdRubro", query = "SELECT r FROM RuboFactura r WHERE r.ruboFacturaPK.idRubro = :idRubro")})
public class RuboFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RuboFacturaPK ruboFacturaPK;

    public RuboFactura() {
    }

    public RuboFactura(RuboFacturaPK ruboFacturaPK) {
        this.ruboFacturaPK = ruboFacturaPK;
    }

    public RuboFactura(int idFactu, int idRubro) {
        this.ruboFacturaPK = new RuboFacturaPK(idFactu, idRubro);
    }

    public RuboFacturaPK getRuboFacturaPK() {
        return ruboFacturaPK;
    }

    public void setRuboFacturaPK(RuboFacturaPK ruboFacturaPK) {
        this.ruboFacturaPK = ruboFacturaPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruboFacturaPK != null ? ruboFacturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuboFactura)) {
            return false;
        }
        RuboFactura other = (RuboFactura) object;
        if ((this.ruboFacturaPK == null && other.ruboFacturaPK != null) || (this.ruboFacturaPK != null && !this.ruboFacturaPK.equals(other.ruboFacturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.RuboFactura[ ruboFacturaPK=" + ruboFacturaPK + " ]";
    }
    
}
