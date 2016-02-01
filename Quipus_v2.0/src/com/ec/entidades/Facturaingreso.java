/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cchristico
 */
@Entity
@Table(name = "facturaingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturaingreso.findAll", query = "SELECT f FROM Facturaingreso f"),
    @NamedQuery(name = "Facturaingreso.findByIdfactura", query = "SELECT f FROM Facturaingreso f WHERE f.idfactura = :idfactura"),
    @NamedQuery(name = "Facturaingreso.findByIdUsu", query = "SELECT f FROM Facturaingreso f WHERE f.idUsu = :idUsu"),
    @NamedQuery(name = "Facturaingreso.findByFechafactura", query = "SELECT f FROM Facturaingreso f WHERE f.fechafactura = :fechafactura"),
    @NamedQuery(name = "Facturaingreso.findByValorfactura", query = "SELECT f FROM Facturaingreso f WHERE f.valorfactura = :valorfactura")})
public class Facturaingreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFACTURA")
    private Integer idfactura;
    @Column(name = "ID_USU")
    private Integer idUsu;
    @Column(name = "FECHAFACTURA")
    @Temporal(TemporalType.DATE)
    private Date fechafactura;
    @Column(name = "VALORFACTURA")
    private String valorfactura;
    @JoinColumn(name = "IDFACTURA", referencedColumnName = "IDFACTURA", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Factura factura;

    public Facturaingreso() {
    }

    public Facturaingreso(Integer idfactura) {
        this.idfactura = idfactura;
    }

    public Integer getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(Integer idfactura) {
        this.idfactura = idfactura;
    }

    public Integer getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(Integer idUsu) {
        this.idUsu = idUsu;
    }

    public Date getFechafactura() {
        return fechafactura;
    }

    public void setFechafactura(Date fechafactura) {
        this.fechafactura = fechafactura;
    }

    public String getValorfactura() {
        return valorfactura;
    }

    public void setValorfactura(String valorfactura) {
        this.valorfactura = valorfactura;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfactura != null ? idfactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturaingreso)) {
            return false;
        }
        Facturaingreso other = (Facturaingreso) object;
        if ((this.idfactura == null && other.idfactura != null) || (this.idfactura != null && !this.idfactura.equals(other.idfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.Facturaingreso[ idfactura=" + idfactura + " ]";
    }
    
}
