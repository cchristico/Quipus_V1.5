/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicios.usuarios;

import com.ec.entidades.Usuario;
import com.ec.servicios.UsuarioJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Cchristico
 */
public class RestarPaswUser {
    public void RestartPassw(){
        
}
    /*private Boolean validUser(Usuario usuario){
        EntityManagerFactory conexion = Persistence.createEntityManagerFactory("QuipusPersistence");
        UsuarioJpaController usrController = new UsuarioJpaController(conexion);
        usrController.create(usuario);
        
        //return !validationErrorMesgIsEmpy();   
    }*/
    
}
