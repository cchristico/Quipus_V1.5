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
@Table(name = "detalle_rubro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleRubro.findAll", query = "SELECT d FROM DetalleRubro d"),
    @NamedQuery(name = "DetalleRubro.findByIdDetalleRubro", query = "SELECT d FROM DetalleRubro d WHERE d.idDetalleRubro = :idDetalleRubro"),
    @NamedQuery(name = "DetalleRubro.findByNombreRubro", query = "SELECT d FROM DetalleRubro d WHERE d.nombreRubro = :nombreRubro"),
    @NamedQuery(name = "DetalleRubro.findByValorMaximo", query = "SELECT d FROM DetalleRubro d WHERE d.valorMaximo = :valorMaximo"),
    @NamedQuery(name = "DetalleRubro.findByTipo", query = "SELECT d FROM DetalleRubro d WHERE d.tipo = :tipo")})
public class DetalleRubro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DETALLE_RUBRO")
    private Integer idDetalleRubro;
    @Basic(optional = false)
    @Column(name = "NOMBRE_RUBRO")
    private String nombreRubro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "VALOR_MAXIMO")
    private BigDecimal valorMaximo;
    @Column(name = "TIPO")
    private String tipo;
    @OneToMany(mappedBy = "idDetalleRubro")
    private Collection<Rubro> rubroCollection;

    public DetalleRubro() {
    }

    public DetalleRubro(Integer idDetalleRubro) {
        this.idDetalleRubro = idDetalleRubro;
    }

    public DetalleRubro(Integer idDetalleRubro, String nombreRubro, BigDecimal valorMaximo) {
        this.idDetalleRubro = idDetalleRubro;
        this.nombreRubro = nombreRubro;
        this.valorMaximo = valorMaximo;
    }

    public Integer getIdDetalleRubro() {
        return idDetalleRubro;
    }

    public void setIdDetalleRubro(Integer idDetalleRubro) {
        this.idDetalleRubro = idDetalleRubro;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<Rubro> getRubroCollection() {
        return rubroCollection;
    }

    public void setRubroCollection(Collection<Rubro> rubroCollection) {
        this.rubroCollection = rubroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleRubro != null ? idDetalleRubro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleRubro)) {
            return false;
        }
        DetalleRubro other = (DetalleRubro) object;
        if ((this.idDetalleRubro == null && other.idDetalleRubro != null) || (this.idDetalleRubro != null && !this.idDetalleRubro.equals(other.idDetalleRubro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.DetalleRubro[ idDetalleRubro=" + idDetalleRubro + " ]";
    }
    
}
