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
@Table(name = "rubro_facruea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RubroFacruea.findAll", query = "SELECT r FROM RubroFacruea r"),
    @NamedQuery(name = "RubroFacruea.findByIdRubroFacrura", query = "SELECT r FROM RubroFacruea r WHERE r.idRubroFacrura = :idRubroFacrura"),
    @NamedQuery(name = "RubroFacruea.findByValorrubro", query = "SELECT r FROM RubroFacruea r WHERE r.valorrubro = :valorrubro")})
public class RubroFacruea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RUBRO_FACRURA")
    private Integer idRubroFacrura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "VALORRUBRO")
    private BigDecimal valorrubro;
    @OneToMany(mappedBy = "idRubroFacrura")
    private Collection<Rubro> rubroCollection;
    @OneToMany(mappedBy = "idRubroFacrura")
    private Collection<Factura> facturaCollection;

    public RubroFacruea() {
    }

    public RubroFacruea(Integer idRubroFacrura) {
        this.idRubroFacrura = idRubroFacrura;
    }

    public RubroFacruea(Integer idRubroFacrura, BigDecimal valorrubro) {
        this.idRubroFacrura = idRubroFacrura;
        this.valorrubro = valorrubro;
    }

    public Integer getIdRubroFacrura() {
        return idRubroFacrura;
    }

    public void setIdRubroFacrura(Integer idRubroFacrura) {
        this.idRubroFacrura = idRubroFacrura;
    }

    public BigDecimal getValorrubro() {
        return valorrubro;
    }

    public void setValorrubro(BigDecimal valorrubro) {
        this.valorrubro = valorrubro;
    }

    @XmlTransient
    public Collection<Rubro> getRubroCollection() {
        return rubroCollection;
    }

    public void setRubroCollection(Collection<Rubro> rubroCollection) {
        this.rubroCollection = rubroCollection;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRubroFacrura != null ? idRubroFacrura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RubroFacruea)) {
            return false;
        }
        RubroFacruea other = (RubroFacruea) object;
        if ((this.idRubroFacrura == null && other.idRubroFacrura != null) || (this.idRubroFacrura != null && !this.idRubroFacrura.equals(other.idRubroFacrura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.RubroFacruea[ idRubroFacrura=" + idRubroFacrura + " ]";
    }
    
}
