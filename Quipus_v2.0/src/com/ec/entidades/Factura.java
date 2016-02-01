/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Cchristico
 */
@Entity
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByIdfactura", query = "SELECT f FROM Factura f WHERE f.idfactura = :idfactura"),
    @NamedQuery(name = "Factura.findByFechafactura", query = "SELECT f FROM Factura f WHERE f.fechafactura = :fechafactura"),
    @NamedQuery(name = "Factura.findByValorfactura", query = "SELECT f FROM Factura f WHERE f.valorfactura = :valorfactura")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFACTURA")
    private Integer idfactura;
    @Column(name = "FECHAFACTURA")
    @Temporal(TemporalType.DATE)
    private Date fechafactura;
    @Column(name = "VALORFACTURA")
    private String valorfactura;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "factura")
    private Facturaingreso facturaingreso;
    @JoinColumn(name = "ID_USU", referencedColumnName = "ID_USU")
    @ManyToOne
    private Usuario idUsu;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "factura")
    private Facturaegreso facturaegreso;

    public Factura() {
    }

    public Factura(Integer idfactura) {
        this.idfactura = idfactura;
    }

    public Integer getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(Integer idfactura) {
        this.idfactura = idfactura;
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

    public Facturaingreso getFacturaingreso() {
        return facturaingreso;
    }

    public void setFacturaingreso(Facturaingreso facturaingreso) {
        this.facturaingreso = facturaingreso;
    }

    public Usuario getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(Usuario idUsu) {
        this.idUsu = idUsu;
    }

    public Facturaegreso getFacturaegreso() {
        return facturaegreso;
    }

    public void setFacturaegreso(Facturaegreso facturaegreso) {
        this.facturaegreso = facturaegreso;
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
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.idfactura == null && other.idfactura != null) || (this.idfactura != null && !this.idfactura.equals(other.idfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.Factura[ idfactura=" + idfactura + " ]";
    }
    
}
