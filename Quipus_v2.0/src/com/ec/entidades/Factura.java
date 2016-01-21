/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cchristico
 */
@Entity
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByIdFactu", query = "SELECT f FROM Factura f WHERE f.idFactu = :idFactu"),
    @NamedQuery(name = "Factura.findByFecha", query = "SELECT f FROM Factura f WHERE f.fecha = :fecha")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FACTU")
    private Integer idFactu;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinTable(name = "rubo_factura", joinColumns = {
        @JoinColumn(name = "ID_FACTU", referencedColumnName = "ID_FACTU")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_RUBRO", referencedColumnName = "ID_RUBRO")})
    @ManyToMany
    private Collection<Rubro> rubroCollection;
    @JoinColumn(name = "ID_RUBRO_FACRURA", referencedColumnName = "ID_RUBRO_FACRURA")
    @ManyToOne
    private RubroFacruea idRubroFacrura;
    @OneToMany(mappedBy = "idFactu")
    private Collection<Proveedor> proveedorCollection;
    @OneToMany(mappedBy = "idFactu")
    private Collection<Detalle> detalleCollection;

    public Factura() {
    }

    public Factura(Integer idFactu) {
        this.idFactu = idFactu;
    }

    public Factura(Integer idFactu, Date fecha) {
        this.idFactu = idFactu;
        this.fecha = fecha;
    }

    public Integer getIdFactu() {
        return idFactu;
    }

    public void setIdFactu(Integer idFactu) {
        this.idFactu = idFactu;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public Collection<Rubro> getRubroCollection() {
        return rubroCollection;
    }

    public void setRubroCollection(Collection<Rubro> rubroCollection) {
        this.rubroCollection = rubroCollection;
    }

    public RubroFacruea getIdRubroFacrura() {
        return idRubroFacrura;
    }

    public void setIdRubroFacrura(RubroFacruea idRubroFacrura) {
        this.idRubroFacrura = idRubroFacrura;
    }

    @XmlTransient
    public Collection<Proveedor> getProveedorCollection() {
        return proveedorCollection;
    }

    public void setProveedorCollection(Collection<Proveedor> proveedorCollection) {
        this.proveedorCollection = proveedorCollection;
    }

    @XmlTransient
    public Collection<Detalle> getDetalleCollection() {
        return detalleCollection;
    }

    public void setDetalleCollection(Collection<Detalle> detalleCollection) {
        this.detalleCollection = detalleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactu != null ? idFactu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.idFactu == null && other.idFactu != null) || (this.idFactu != null && !this.idFactu.equals(other.idFactu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.Factura[ idFactu=" + idFactu + " ]";
    }
    
}
