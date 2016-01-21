/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.vistas;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Toshiba
 */
@Entity
@Table(name = "proveedor", catalog = "quipus", schema = "")
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdProveedor", query = "SELECT p FROM Proveedor p WHERE p.idProveedor = :idProveedor"),
    @NamedQuery(name = "Proveedor.findByNombreProveedor", query = "SELECT p FROM Proveedor p WHERE p.nombreProveedor = :nombreProveedor"),
    @NamedQuery(name = "Proveedor.findByRuc", query = "SELECT p FROM Proveedor p WHERE p.ruc = :ruc")})
public class Proveedor implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;
    @Basic(optional = false)
    @Column(name = "NOMBRE_PROVEEDOR")
    private String nombreProveedor;
    @Basic(optional = false)
    @Column(name = "RUC")
    private int ruc;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedor(Integer idProveedor, String nombreProveedor, int ruc) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.ruc = ruc;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        Integer oldIdProveedor = this.idProveedor;
        this.idProveedor = idProveedor;
        changeSupport.firePropertyChange("idProveedor", oldIdProveedor, idProveedor);
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        String oldNombreProveedor = this.nombreProveedor;
        this.nombreProveedor = nombreProveedor;
        changeSupport.firePropertyChange("nombreProveedor", oldNombreProveedor, nombreProveedor);
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        int oldRuc = this.ruc;
        this.ruc = ruc;
        changeSupport.firePropertyChange("ruc", oldRuc, ruc);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.vistas.Proveedor[ idProveedor=" + idProveedor + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
