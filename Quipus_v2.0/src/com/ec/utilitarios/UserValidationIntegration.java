/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.utilitarios;
import com.ec.negocio.GeneralValidations;
import com.ec.entidades.Usuario;
import com.ec.controlladores.UsuarioJpaController;
import com.ec.vistas.Login;
import com.ec.vistas.Principal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.ec.utilitarios.UsuarioLog;
import com.ec.vistas.ListaIngresos;
import javax.swing.JInternalFrame;

/**
 *
 * @author Cchristico
 */
public class UserValidationIntegration {

public void UserCreation(Usuario usuario) throws Exception
{
    if(validFiedls(usuario))
    {
        EntityManagerFactory conexion = Persistence.createEntityManagerFactory("Quipus_v2.0PU");
        UsuarioJpaController usrController = new UsuarioJpaController(conexion);
        usrController.create(usuario);
//    usrController.create(usuario);
        //usrController.create(usuario);
        //JOptionPane.showMessageDialog(null, "Usuario Registrado, ya puedes ingresar!");
    }
}

public void Loging(Usuario usuario)
{
    if(logUser(usuario))
    {
        UsuarioLog usr = new UsuarioLog();
        usr.setCedulaUsr(usuario.nombresapellidos);
        usr.setCedulaUsr(usuario.cedula);
        usr.setIDUsr(usuario.idUsu);
        System.out.println(usr.getCedulaUsr());
        //Usuario usr = new Usuario();
        Principal main = new Principal();
        main.setUsr(usr);
        main.setVisible(true);
        Login log = new Login();
        log.setVisible(false);
    }
    else{
        Login log = new Login();
        log.setVisible(true);
    }
}


private boolean validFiedls(Usuario usuario)
{
    
    GeneralValidations validation = new GeneralValidations();
    validation.CIValidation(usuario.getCedula(), "C.I.");
    validation.validteEmptyField(usuario.getNombresapellidos(), "Nombres y Apellidos");
    validation.userDuplication(usuario);
    /*Registro repetido*/
    return !validation.ErrorMesgIsEmpy();                     
}

private boolean logUser(Usuario usuario)
{
    GeneralValidations val = new GeneralValidations();
    val.UserExist(usuario);
    return !val.ErrorMesgIsEmpy();
}

}
