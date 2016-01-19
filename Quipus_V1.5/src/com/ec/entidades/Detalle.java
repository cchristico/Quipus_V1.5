/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
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
@Table(name = "detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalle.findAll", query = "SELECT d FROM Detalle d"),
    @NamedQuery(name = "Detalle.findByIdDetalle", query = "SELECT d FROM Detalle d WHERE d.idDetalle = :idDetalle"),
    @NamedQuery(name = "Detalle.findByIdFactura", query = "SELECT d FROM Detalle d WHERE d.idFactura = :idFactura")})
public class Detalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DETALLE")
    private Integer idDetalle;
    @Basic(optional = false)
    @Column(name = "ID_FACTURA")
    private int idFactura;
    @JoinColumn(name = "ID_FACTU", referencedColumnName = "ID_FACTU")
    @ManyToOne
    private Factura idFactu;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public Detalle() {
    }

    public Detalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Detalle(Integer idDetalle, int idFactura) {
        this.idDetalle = idDetalle;
        this.idFactura = idFactura;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Factura getIdFactu() {
        return idFactu;
    }

    public void setIdFactu(Factura idFactu) {
        this.idFactu = idFactu;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalle)) {
            return false;
        }
        Detalle other = (Detalle) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.Detalle[ idDetalle=" + idDetalle + " ]";
    }
    
}
