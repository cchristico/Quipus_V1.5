/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicios.usuarios;

import com.ec.negocio.GeneralValidations;
import com.ec.entidades.Usuario;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.ec.negocio.*;
import com.ec.utilitarios.UserValidationIntegration;
/**
 *
 * @author Cchristico
 */
public class ingresoUsuario {

public void UserCreate(Usuario usuario)
{
    UserValidationIntegration validation = new UserValidationIntegration();   
    validation.UserCreation(usuario);
}

        public boolean userPasswordLength(String userPassword)
        {
            GeneralValidations val = new GeneralValidations();
            val.matchPassw(userPassword, userPassword);
            Pattern pat = Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
            Matcher mat = pat.matcher(userPassword);
            return mat.find();
        }

}

