/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlladores;

import com.ec.controlladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ec.entidades.Facturaegreso;
import com.ec.entidades.Rubro;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        if (rubro.getFacturaegresoSet() == null) {
            rubro.setFacturaegresoSet(new HashSet<Facturaegreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Facturaegreso> attachedFacturaegresoSet = new HashSet<Facturaegreso>();
            for (Facturaegreso facturaegresoSetFacturaegresoToAttach : rubro.getFacturaegresoSet()) {
                facturaegresoSetFacturaegresoToAttach = em.getReference(facturaegresoSetFacturaegresoToAttach.getClass(), facturaegresoSetFacturaegresoToAttach.getIdfactura());
                attachedFacturaegresoSet.add(facturaegresoSetFacturaegresoToAttach);
            }
            rubro.setFacturaegresoSet(attachedFacturaegresoSet);
            em.persist(rubro);
            for (Facturaegreso facturaegresoSetFacturaegreso : rubro.getFacturaegresoSet()) {
                Rubro oldIdrubroalcanzadoOfFacturaegresoSetFacturaegreso = facturaegresoSetFacturaegreso.getIdrubroalcanzado();
                facturaegresoSetFacturaegreso.setIdrubroalcanzado(rubro);
                facturaegresoSetFacturaegreso = em.merge(facturaegresoSetFacturaegreso);
                if (oldIdrubroalcanzadoOfFacturaegresoSetFacturaegreso != null) {
                    oldIdrubroalcanzadoOfFacturaegresoSetFacturaegreso.getFacturaegresoSet().remove(facturaegresoSetFacturaegreso);
                    oldIdrubroalcanzadoOfFacturaegresoSetFacturaegreso = em.merge(oldIdrubroalcanzadoOfFacturaegresoSetFacturaegreso);
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
            Set<Facturaegreso> facturaegresoSetOld = persistentRubro.getFacturaegresoSet();
            Set<Facturaegreso> facturaegresoSetNew = rubro.getFacturaegresoSet();
            Set<Facturaegreso> attachedFacturaegresoSetNew = new HashSet<Facturaegreso>();
            for (Facturaegreso facturaegresoSetNewFacturaegresoToAttach : facturaegresoSetNew) {
                facturaegresoSetNewFacturaegresoToAttach = em.getReference(facturaegresoSetNewFacturaegresoToAttach.getClass(), facturaegresoSetNewFacturaegresoToAttach.getIdfactura());
                attachedFacturaegresoSetNew.add(facturaegresoSetNewFacturaegresoToAttach);
            }
            facturaegresoSetNew = attachedFacturaegresoSetNew;
            rubro.setFacturaegresoSet(facturaegresoSetNew);
            rubro = em.merge(rubro);
            for (Facturaegreso facturaegresoSetOldFacturaegreso : facturaegresoSetOld) {
                if (!facturaegresoSetNew.contains(facturaegresoSetOldFacturaegreso)) {
                    facturaegresoSetOldFacturaegreso.setIdrubroalcanzado(null);
                    facturaegresoSetOldFacturaegreso = em.merge(facturaegresoSetOldFacturaegreso);
                }
            }
            for (Facturaegreso facturaegresoSetNewFacturaegreso : facturaegresoSetNew) {
                if (!facturaegresoSetOld.contains(facturaegresoSetNewFacturaegreso)) {
                    Rubro oldIdrubroalcanzadoOfFacturaegresoSetNewFacturaegreso = facturaegresoSetNewFacturaegreso.getIdrubroalcanzado();
                    facturaegresoSetNewFacturaegreso.setIdrubroalcanzado(rubro);
                    facturaegresoSetNewFacturaegreso = em.merge(facturaegresoSetNewFacturaegreso);
                    if (oldIdrubroalcanzadoOfFacturaegresoSetNewFacturaegreso != null && !oldIdrubroalcanzadoOfFacturaegresoSetNewFacturaegreso.equals(rubro)) {
                        oldIdrubroalcanzadoOfFacturaegresoSetNewFacturaegreso.getFacturaegresoSet().remove(facturaegresoSetNewFacturaegreso);
                        oldIdrubroalcanzadoOfFacturaegresoSetNewFacturaegreso = em.merge(oldIdrubroalcanzadoOfFacturaegresoSetNewFacturaegreso);
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
            Set<Facturaegreso> facturaegresoSet = rubro.getFacturaegresoSet();
            for (Facturaegreso facturaegresoSetFacturaegreso : facturaegresoSet) {
                facturaegresoSetFacturaegreso.setIdrubroalcanzado(null);
                facturaegresoSetFacturaegreso = em.merge(facturaegresoSetFacturaegreso);
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
