/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cchristico
 */
@Entity
@Table(name = "factura_rubro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturaRubro.findAll", query = "SELECT f FROM FacturaRubro f"),
    @NamedQuery(name = "FacturaRubro.findByIdDetRub", query = "SELECT f FROM FacturaRubro f WHERE f.idDetRub = :idDetRub"),
    @NamedQuery(name = "FacturaRubro.findByValor", query = "SELECT f FROM FacturaRubro f WHERE f.valor = :valor")})
public class FacturaRubro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DET_RUB")
    private Integer idDetRub;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "VALOR")
    private BigDecimal valor;
    @JoinColumn(name = "ID_RUBRO", referencedColumnName = "ID_RUBRO")
    @ManyToOne
    private Rubro idRubro;
    @JoinColumn(name = "ID_FACTURA", referencedColumnName = "ID_FACTURA")
    @ManyToOne
    private Factura idFactura;

    public FacturaRubro() {
    }

    public FacturaRubro(Integer idDetRub) {
        this.idDetRub = idDetRub;
    }

    public FacturaRubro(Integer idDetRub, BigDecimal valor) {
        this.idDetRub = idDetRub;
        this.valor = valor;
    }

    public Integer getIdDetRub() {
        return idDetRub;
    }

    public void setIdDetRub(Integer idDetRub) {
        this.idDetRub = idDetRub;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Rubro getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Rubro idRubro) {
        this.idRubro = idRubro;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetRub != null ? idDetRub.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaRubro)) {
            return false;
        }
        FacturaRubro other = (FacturaRubro) object;
        if ((this.idDetRub == null && other.idDetRub != null) || (this.idDetRub != null && !this.idDetRub.equals(other.idDetRub))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.FacturaRubro[ idDetRub=" + idDetRub + " ]";
    }
    
}
