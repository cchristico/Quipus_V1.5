/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.utilitarios;


/**
 *
 * @author Cchristico
 */
public class UsuarioLog {
    public Integer  IDUsr=0;
    public String NombreUsr="";
    public String CedulaUsr="";

    public UsuarioLog() {
    }
    
    public UsuarioLog(Integer IDUsr,String NombreUsr, String CedulaUsr){
        this.IDUsr=IDUsr;
        this.NombreUsr=NombreUsr;
        this.CedulaUsr=CedulaUsr;
    }


    

    public int getIDUsr() {
        return IDUsr;
    }

    public String getNombreUsr() {
        return NombreUsr;
    }

    public String getCedulaUsr() {
        return CedulaUsr;
    }

    public void setIDUsr(int IDUsr) {
        this.IDUsr = IDUsr;
    }

    public void setNombreUsr(String NombreUsr) {
        this.NombreUsr = NombreUsr;
    }

    public void setCedulaUsr(String CedulaUsr) {
        this.CedulaUsr = CedulaUsr;
    }
    
    
    
    
}
