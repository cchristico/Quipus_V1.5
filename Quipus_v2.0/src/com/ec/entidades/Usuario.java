/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import java.util.Set;
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
 * @author david
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsu", query = "SELECT u FROM Usuario u WHERE u.idUsu = :idUsu"),
    @NamedQuery(name = "Usuario.findByNombresapellidos", query = "SELECT u FROM Usuario u WHERE u.nombresapellidos = :nombresapellidos"),
    @NamedQuery(name = "Usuario.findByCedula", query = "SELECT u FROM Usuario u WHERE u.cedula = :cedula")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USU")
    private Integer idUsu;
    @Basic(optional = false)
    @Column(name = "NOMBRESAPELLIDOS")
    private String nombresapellidos;
    @Basic(optional = false)
    @Column(name = "CEDULA")
    private String cedula;
    @OneToMany(mappedBy = "idUsu")
    private Set<Factura> facturaSet;

    public Usuario() {
    }

    public Usuario(Integer idUsu) {
        this.idUsu = idUsu;
    }

    public Usuario(Integer idUsu, String nombresapellidos, String cedula) {
        this.idUsu = idUsu;
        this.nombresapellidos = nombresapellidos;
        this.cedula = cedula;
    }

    public Integer getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(Integer idUsu) {
        this.idUsu = idUsu;
    }

    public String getNombresapellidos() {
        return nombresapellidos;
    }

    public void setNombresapellidos(String nombresapellidos) {
        this.nombresapellidos = nombresapellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @XmlTransient
    public Set<Factura> getFacturaSet() {
        return facturaSet;
    }

    public void setFacturaSet(Set<Factura> facturaSet) {
        this.facturaSet = facturaSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsu != null ? idUsu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsu == null && other.idUsu != null) || (this.idUsu != null && !this.idUsu.equals(other.idUsu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.Usuario[ idUsu=" + idUsu + " ]";
    }
    
}
