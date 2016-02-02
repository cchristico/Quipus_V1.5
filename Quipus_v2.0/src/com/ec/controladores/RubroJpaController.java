/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controladores;

import com.ec.controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ec.entidades.Facturaegreso;
import com.ec.entidades.Rubro;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Cchristico
 */
public class RubroJpaController implements Serializable {

    public RubroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rubro rubro) {
        if (rubro.getFacturaegresoCollection() == null) {
            rubro.setFacturaegresoCollection(new ArrayList<Facturaegreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Facturaegreso> attachedFacturaegresoCollection = new ArrayList<Facturaegreso>();
            for (Facturaegreso facturaegresoCollectionFacturaegresoToAttach : rubro.getFacturaegresoCollection()) {
                facturaegresoCollectionFacturaegresoToAttach = em.getReference(facturaegresoCollectionFacturaegresoToAttach.getClass(), facturaegresoCollectionFacturaegresoToAttach.getIdfactura());
                attachedFacturaegresoCollection.add(facturaegresoCollectionFacturaegresoToAttach);
            }
            rubro.setFacturaegresoCollection(attachedFacturaegresoCollection);
            em.persist(rubro);
            for (Facturaegreso facturaegresoCollectionFacturaegreso : rubro.getFacturaegresoCollection()) {
                Rubro oldIdrubroalcanzadoOfFacturaegresoCollectionFacturaegreso = facturaegresoCollectionFacturaegreso.getIdrubroalcanzado();
                facturaegresoCollectionFacturaegreso.setIdrubroalcanzado(rubro);
                facturaegresoCollectionFacturaegreso = em.merge(facturaegresoCollectionFacturaegreso);
                if (oldIdrubroalcanzadoOfFacturaegresoCollectionFacturaegreso != null) {
                    oldIdrubroalcanzadoOfFacturaegresoCollectionFacturaegreso.getFacturaegresoCollection().remove(facturaegresoCollectionFacturaegreso);
                    oldIdrubroalcanzadoOfFacturaegresoCollectionFacturaegreso = em.merge(oldIdrubroalcanzadoOfFacturaegresoCollectionFacturaegreso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rubro rubro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rubro persistentRubro = em.find(Rubro.class, rubro.getIdrubroalcanzado());
            Collection<Facturaegreso> facturaegresoCollectionOld = persistentRubro.getFacturaegresoCollection();
            Collection<Facturaegreso> facturaegresoCollectionNew = rubro.getFacturaegresoCollection();
            Collection<Facturaegreso> attachedFacturaegresoCollectionNew = new ArrayList<Facturaegreso>();
            for (Facturaegreso facturaegresoCollectionNewFacturaegresoToAttach : facturaegresoCollectionNew) {
                facturaegresoCollectionNewFacturaegresoToAttach = em.getReference(facturaegresoCollectionNewFacturaegresoToAttach.getClass(), facturaegresoCollectionNewFacturaegresoToAttach.getIdfactura());
                attachedFacturaegresoCollectionNew.add(facturaegresoCollectionNewFacturaegresoToAttach);
            }
            facturaegresoCollectionNew = attachedFacturaegresoCollectionNew;
            rubro.setFacturaegresoCollection(facturaegresoCollectionNew);
            rubro = em.merge(rubro);
            for (Facturaegreso facturaegresoCollectionOldFacturaegreso : facturaegresoCollectionOld) {
                if (!facturaegresoCollectionNew.contains(facturaegresoCollectionOldFacturaegreso)) {
                    facturaegresoCollectionOldFacturaegreso.setIdrubroalcanzado(null);
                    facturaegresoCollectionOldFacturaegreso = em.merge(facturaegresoCollectionOldFacturaegreso);
                }
            }
            for (Facturaegreso facturaegresoCollectionNewFacturaegreso : facturaegresoCollectionNew) {
                if (!facturaegresoCollectionOld.contains(facturaegresoCollectionNewFacturaegreso)) {
                    Rubro oldIdrubroalcanzadoOfFacturaegresoCollectionNewFacturaegreso = facturaegresoCollectionNewFacturaegreso.getIdrubroalcanzado();
                    facturaegresoCollectionNewFacturaegreso.setIdrubroalcanzado(rubro);
                    facturaegresoCollectionNewFacturaegreso = em.merge(facturaegresoCollectionNewFacturaegreso);
                    if (oldIdrubroalcanzadoOfFacturaegresoCollectionNewFacturaegreso != null && !oldIdrubroalcanzadoOfFacturaegresoCollectionNewFacturaegreso.equals(rubro)) {
                        oldIdrubroalcanzadoOfFacturaegresoCollectionNewFacturaegreso.getFacturaegresoCollection().remove(facturaegresoCollectionNewFacturaegreso);
                        oldIdrubroalcanzadoOfFacturaegresoCollectionNewFacturaegreso = em.merge(oldIdrubroalcanzadoOfFacturaegresoCollectionNewFacturaegreso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rubro.getIdrubroalcanzado();
                if (findRubro(id) == null) {
                    throw new NonexistentEntityException("The rubro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rubro rubro;
            try {
                rubro = em.getReference(Rubro.class, id);
                rubro.getIdrubroalcanzado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rubro with id " + id + " no longer exists.", enfe);
            }
            Collection<Facturaegreso> facturaegresoCollection = rubro.getFacturaegresoCollection();
            for (Facturaegreso facturaegresoCollectionFacturaegreso : facturaegresoCollection) {
                facturaegresoCollectionFacturaegreso.setIdrubroalcanzado(null);
                facturaegresoCollectionFacturaegreso = em.merge(facturaegresoCollectionFacturaegreso);
            }
            em.remove(rubro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rubro> findRubroEntities() {
        return findRubroEntities(true, -1, -1);
    }

    public List<Rubro> findRubroEntities(int maxResults, int firstResult) {
        return findRubroEntities(false, maxResults, firstResult);
    }

    private List<Rubro> findRubroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rubro.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Rubro findRubro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rubro.class, id);
        } finally {
            em.close();
        }
    }

    public int getRubroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rubro> rt = cq.from(Rubro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
