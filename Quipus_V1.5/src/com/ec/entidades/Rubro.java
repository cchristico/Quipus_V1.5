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
@Table(name = "rubro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rubro.findAll", query = "SELECT r FROM Rubro r"),
    @NamedQuery(name = "Rubro.findByIdRubro", query = "SELECT r FROM Rubro r WHERE r.idRubro = :idRubro"),
    @NamedQuery(name = "Rubro.findByValorActual", query = "SELECT r FROM Rubro r WHERE r.valorActual = :valorActual")})
public class Rubro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_RUBRO")
    private Integer idRubro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_ACTUAL")
    private BigDecimal valorActual;
    @JoinColumn(name = "ID_DETALLE_RUBRO", referencedColumnName = "ID_DETALLE_RUBRO")
    @ManyToOne
    private DetalleRubro idDetalleRubro;
    @JoinColumn(name = "ID_RUBRO_FACRURA", referencedColumnName = "ID_RUBRO_FACRURA")
    @ManyToOne
    private RubroFacruea idRubroFacrura;

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

    public RubroFacruea getIdRubroFacrura() {
        return idRubroFacrura;
    }

    public void setIdRubroFacrura(RubroFacruea idRubroFacrura) {
        this.idRubroFacrura = idRubroFacrura;
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
