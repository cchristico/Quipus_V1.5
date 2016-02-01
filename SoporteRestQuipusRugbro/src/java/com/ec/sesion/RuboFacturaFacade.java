/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.sesion;

import com.ec.entidades.RuboFactura;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cchristico
 */
@Stateless
public class RuboFacturaFacade extends AbstractFacade<RuboFactura> {

    @PersistenceContext(unitName = "SoporteRestQuipusRugbroPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RuboFacturaFacade() {
        super(RuboFactura.class);
    }
    
}
