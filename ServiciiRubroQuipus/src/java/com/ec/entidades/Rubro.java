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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author david
 */
@Entity
@Table(name = "rubro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rubro.findAll", query = "SELECT r FROM Rubro r"),
    @NamedQuery(name = "Rubro.findByIdrubroalcanzado", query = "SELECT r FROM Rubro r WHERE r.idrubroalcanzado = :idrubroalcanzado"),
    @NamedQuery(name = "Rubro.findByNombrerubro", query = "SELECT r FROM Rubro r WHERE r.nombrerubro = :nombrerubro"),
    @NamedQuery(name = "Rubro.findByValoractual", query = "SELECT r FROM Rubro r WHERE r.valoractual = :valoractual"),
    @NamedQuery(name = "Rubro.findByCodigorubro", query = "SELECT r FROM Rubro r WHERE r.codigorubro = :codigorubro"),
    @NamedQuery(name = "Rubro.findByValorreferencia", query = "SELECT r FROM Rubro r WHERE r.valorreferencia = :valorreferencia")})
public class Rubro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDRUBROALCANZADO")
    private Integer idrubroalcanzado;
    @Size(max = 25)
    @Column(name = "NOMBRERUBRO")
    private String nombrerubro;
    @Size(max = 25)
    @Column(name = "VALORACTUAL")
    private String valoractual;
    @Size(max = 25)
    @Column(name = "CODIGORUBRO")
    private String codigorubro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALORREFERENCIA")
    private int valorreferencia;

    public Rubro() {
    }

    public Rubro(Integer idrubroalcanzado) {
        this.idrubroalcanzado = idrubroalcanzado;
    }

    public Rubro(Integer idrubroalcanzado, int valorreferencia) {
        this.idrubroalcanzado = idrubroalcanzado;
        this.valorreferencia = valorreferencia;
    }

    public Integer getIdrubroalcanzado() {
        return idrubroalcanzado;
    }

    public void setIdrubroalcanzado(Integer idrubroalcanzado) {
        this.idrubroalcanzado = idrubroalcanzado;
    }

    public String getNombrerubro() {
        return nombrerubro;
    }

    public void setNombrerubro(String nombrerubro) {
        this.nombrerubro = nombrerubro;
    }

    public String getValoractual() {
        return valoractual;
    }

    public void setValoractual(String valoractual) {
        this.valoractual = valoractual;
    }

    public String getCodigorubro() {
        return codigorubro;
    }

    public void setCodigorubro(String codigorubro) {
        this.codigorubro = codigorubro;
    }

    public int getValorreferencia() {
        return valorreferencia;
    }

    public void setValorreferencia(int valorreferencia) {
        this.valorreferencia = valorreferencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubroalcanzado != null ? idrubroalcanzado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubro)) {
            return false;
        }
        Rubro other = (Rubro) object;
        if ((this.idrubroalcanzado == null && other.idrubroalcanzado != null) || (this.idrubroalcanzado != null && !this.idrubroalcanzado.equals(other.idrubroalcanzado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.Rubro[ idrubroalcanzado=" + idrubroalcanzado + " ]";
    }
    
}
