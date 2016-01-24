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
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

public void Loging(Usuario usuario)
{
    
}


private boolean validFiedls(Usuario usuario)
{
    
    GeneralValidations validation = new GeneralValidations();
    validation.CIValidation(usuario.getCedulaUsuario(), "C.I.");
    validation.validteEmptyField(usuario.getNombreUsuario(), "Nombres y Apellidos");
    /*Registro repetido*/
    return !validation.ErrorMesgIsEmpy();                     
}

public boolean  UserExist(Usuario usuario)
{
        EntityManagerFactory emFac = Persistence.createEntityManagerFactory("Quipus_v2.0PU");
    EntityManager entityManager = emFac.createEntityManager();
    Query query1 = entityManager.createQuery("Select u.idUsuario from Usuario u where u.cedulaUsuario = :usuarioCI");
    query1.setParameter("usuarioCI", usuario.getCedulaUsuario());
    int resutado = (int) query1.getSingleResult(); 
    System.out.println("ID"+resutado);
    return true;
}

}
