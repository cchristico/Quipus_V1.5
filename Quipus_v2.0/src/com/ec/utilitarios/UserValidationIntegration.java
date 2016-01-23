/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.utilitarios;
import com.ec.negocio.GeneralValidations;
import com.ec.entidades.Usuario;
import com.ec.negocio.*;
import com.ec.servicios.UsuarioJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author Cchristico
 */
public class UserValidationIntegration {
public void UserCreation(Usuario usuario)
{
    if(validFiedls(usuario))
    {
        EntityManagerFactory conexion = Persistence.createEntityManagerFactory("Quipus_v2.0PU");
        UsuarioJpaController usrController = new UsuarioJpaController(conexion);
        usrController.create(usuario);
        //usrController.create(usuario);
        //JOptionPane.showMessageDialog(null, "Usuario Registrado, ya puedes ingresar!");
    }
}


private boolean validFiedls(Usuario usuario)
{
    
    GeneralValidations validation = new GeneralValidations();
    validation.CIValidation(usuario.getCedulaUsuario(), "C.I.");
    validation.validteEmptyField(usuario.getNombreUsuario(), "Nombres y Apellidos");
    /*Registro repetido*/
    return !validation.ErrorMesgIsEmpy();                     
}
}
