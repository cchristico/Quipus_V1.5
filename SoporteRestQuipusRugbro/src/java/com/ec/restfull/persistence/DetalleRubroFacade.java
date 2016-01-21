/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.restfull.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author david
 */
@Stateless
public class DetalleRubroFacade extends AbstractFacade<DetalleRubro> {

    @PersistenceContext(unitName = "SoporteRestQuipusRugbroPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleRubroFacade() {
        super(DetalleRubro.class);
    }
    
}