/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.restfull.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author david
 */
@Entity
@Table(name = "rubro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rubro.findAll", query = "SELECT r FROM Rubro r"),
    @NamedQuery(name = "Rubro.findByIdRubro", query = "SELECT r FROM Rubro r WHERE r.idRubro = :idRubro"),
    @NamedQuery(name = "Rubro.findByValorActual", query = "SELECT r FROM Rubro r WHERE r.valorActual = :valorActual")})
public class Rubro implements Serializable {

    @JoinTable(name = "rubo_factura", joinColumns = {
        @JoinColumn(name = "ID_RUBRO", referencedColumnName = "ID_RUBRO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_FACTU", referencedColumnName = "ID_FACTU")})
    @ManyToMany
    private Collection<Factura> facturaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RUBRO")
    private Integer idRubro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_ACTUAL")
    private BigDecimal valorActual;
    @JoinColumn(name = "ID_DETALLE_RUBRO", referencedColumnName = "ID_DETALLE_RUBRO")
    @ManyToOne
    private DetalleRubro idDetalleRubro;
    @OneToMany(mappedBy = "idRubro")
    private Collection<DetalleRubro> detalleRubroCollection;

    public Rubro() {
    }

    public Rubro(Integer idRubro) {
        this.idRubro = idRubro;
    }

    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
    }

    public BigDecimal getValorActual() {
        return valorActual;
    }

    public void setValorActual(BigDecimal valorActual) {
        this.valorActual = valorActual;
    }

    public DetalleRubro getIdDetalleRubro() {
        return idDetalleRubro;
    }

    public void setIdDetalleRubro(DetalleRubro idDetalleRubro) {
        this.idDetalleRubro = idDetalleRubro;
    }

    @XmlTransient
    public Collection<DetalleRubro> getDetalleRubroCollection() {
        return detalleRubroCollection;
    }

    public void setDetalleRubroCollection(Collection<DetalleRubro> detalleRubroCollection) {
        this.detalleRubroCollection = detalleRubroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRubro != null ? idRubro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubro)) {
            return false;
        }
        Rubro other = (Rubro) object;
        if ((this.idRubro == null && other.idRubro != null) || (this.idRubro != null && !this.idRubro.equals(other.idRubro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.restfull.persistence.Rubro[ idRubro=" + idRubro + " ]";
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }
    
}
