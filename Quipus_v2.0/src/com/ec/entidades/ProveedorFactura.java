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
@Table(name = "proveedor_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProveedorFactura.findAll", query = "SELECT p FROM ProveedorFactura p"),
    @NamedQuery(name = "ProveedorFactura.findByIdProveeFacturador", query = "SELECT p FROM ProveedorFactura p WHERE p.idProveeFacturador = :idProveeFacturador"),
    @NamedQuery(name = "ProveedorFactura.findByValorProveedor", query = "SELECT p FROM ProveedorFactura p WHERE p.valorProveedor = :valorProveedor")})
public class ProveedorFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROVEE_FACTURADOR")
    private Integer idProveeFacturador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "VALOR_PROVEEDOR")
    private BigDecimal valorProveedor;
    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne
    private Proveedor idProveedor;
    @JoinColumn(name = "ID_FACTURA", referencedColumnName = "ID_FACTURA")
    @ManyToOne
    private Factura idFactura;

    public ProveedorFactura() {
    }

    public ProveedorFactura(Integer idProveeFacturador) {
        this.idProveeFacturador = idProveeFacturador;
    }

    public ProveedorFactura(Integer idProveeFacturador, BigDecimal valorProveedor) {
        this.idProveeFacturador = idProveeFacturador;
        this.valorProveedor = valorProveedor;
    }

    public Integer getIdProveeFacturador() {
        return idProveeFacturador;
    }

    public void setIdProveeFacturador(Integer idProveeFacturador) {
        this.idProveeFacturador = idProveeFacturador;
    }

    public BigDecimal getValorProveedor() {
        return valorProveedor;
    }

    public void setValorProveedor(BigDecimal valorProveedor) {
        this.valorProveedor = valorProveedor;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
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
        hash += (idProveeFacturador != null ? idProveeFacturador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorFactura)) {
            return false;
        }
        ProveedorFactura other = (ProveedorFactura) object;
        if ((this.idProveeFacturador == null && other.idProveeFacturador != null) || (this.idProveeFacturador != null && !this.idProveeFacturador.equals(other.idProveeFacturador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.ProveedorFactura[ idProveeFacturador=" + idProveeFacturador + " ]";
    }
    
}
