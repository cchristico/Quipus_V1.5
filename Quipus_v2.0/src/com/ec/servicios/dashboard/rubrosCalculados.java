/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicios.dashboard;

import com.ec.entidades.Rubro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Toshiba
 */
public class rubrosCalculados {
    
    public void userDuplication(Rubro max_rubro)
        {        
    EntityManagerFactory emFac = Persistence.createEntityManagerFactory("Quipus_v2.0PU");
    EntityManager entityManager = emFac.createEntityManager();
    Query query1 = entityManager.createQuery("Select r from Rubro r where r. = :usuarioCI or u.nombreUsuario= :usuarioName");
    //query1.setParameter("usuarioCI", usuario.getCedula())
      //      .setParameter("usuarioName", usuario.getNombresapellidos());
        //        List<Usuario> list = query1.getResultList();
        }
}
