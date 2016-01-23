/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cchristico
 */
@Entity
@Table(name = "rubro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rubro.findAll", query = "SELECT r FROM Rubro r"),
    @NamedQuery(name = "Rubro.findByIdRubro", query = "SELECT r FROM Rubro r WHERE r.idRubro = :idRubro"),
    @NamedQuery(name = "Rubro.findByNombreRubro", query = "SELECT r FROM Rubro r WHERE r.nombreRubro = :nombreRubro"),
    @NamedQuery(name = "Rubro.findByValorMaximo", query = "SELECT r FROM Rubro r WHERE r.valorMaximo = :valorMaximo")})
public class Rubro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RUBRO")
    private Integer idRubro;
    @Basic(optional = false)
    @Column(name = "NOMBRE_RUBRO")
    private String nombreRubro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "VALOR_MAXIMO")
    private BigDecimal valorMaximo;
    @OneToMany(mappedBy = "idRubro")
    private Collection<FacturaRubro> facturaRubroCollection;

    public Rubro() {
    }

    public Rubro(Integer idRubro) {
        this.idRubro = idRubro;
    }

    public Rubro(Integer idRubro, String nombreRubro, BigDecimal valorMaximo) {
        this.idRubro = idRubro;
        this.nombreRubro = nombreRubro;
        this.valorMaximo = valorMaximo;
    }

    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
    }

    public String getNombreRubro() {
        return nombreRubro;
    }

    public void setNombreRubro(String nombreRubro) {
        this.nombreRubro = nombreRubro;
    }

    public BigDecimal getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(BigDecimal valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    @XmlTransient
    public Collection<FacturaRubro> getFacturaRubroCollection() {
        return facturaRubroCollection;
    }

    public void setFacturaRubroCollection(Collection<FacturaRubro> facturaRubroCollection) {
        this.facturaRubroCollection = facturaRubroCollection;
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
        return "com.ec.entidades.Rubro[ idRubro=" + idRubro + " ]";
    }
    
}
