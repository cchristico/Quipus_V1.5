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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author david
 */
@Entity
@Table(name = "facturaegreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturaegreso.findAll", query = "SELECT f FROM Facturaegreso f"),
    @NamedQuery(name = "Facturaegreso.findByIdfactura", query = "SELECT f FROM Facturaegreso f WHERE f.idfactura = :idfactura"),
    @NamedQuery(name = "Facturaegreso.findByIdUsu", query = "SELECT f FROM Facturaegreso f WHERE f.idUsu = :idUsu"),
    @NamedQuery(name = "Facturaegreso.findByFechafactura", query = "SELECT f FROM Facturaegreso f WHERE f.fechafactura = :fechafactura"),
    @NamedQuery(name = "Facturaegreso.findByValorfactura", query = "SELECT f FROM Facturaegreso f WHERE f.valorfactura = :valorfactura")})
public class Facturaegreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
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
    @JoinColumn(name = "IDRUBROALCANZADO", referencedColumnName = "IDRUBROALCANZADO")
    @ManyToOne
    private Rubro idrubroalcanzado;

    public Facturaegreso() {
    }

    public Facturaegreso(Integer idfactura) {
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

    public Rubro getIdrubroalcanzado() {
        return idrubroalcanzado;
    }

    public void setIdrubroalcanzado(Rubro idrubroalcanzado) {
        this.idrubroalcanzado = idrubroalcanzado;
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
        if (!(object instanceof Facturaegreso)) {
            return false;
        }
        Facturaegreso other = (Facturaegreso) object;
        if ((this.idfactura == null && other.idfactura != null) || (this.idfactura != null && !this.idfactura.equals(other.idfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.Facturaegreso[ idfactura=" + idfactura + " ]";
    }
    
}
