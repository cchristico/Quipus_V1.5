/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicios;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ec.entidades.FacturaRubro;
import com.ec.entidades.Rubro;
import com.ec.servicios.exceptions.NonexistentEntityException;
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
        if (rubro.getFacturaRubroCollection() == null) {
            rubro.setFacturaRubroCollection(new ArrayList<FacturaRubro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<FacturaRubro> attachedFacturaRubroCollection = new ArrayList<FacturaRubro>();
            for (FacturaRubro facturaRubroCollectionFacturaRubroToAttach : rubro.getFacturaRubroCollection()) {
                facturaRubroCollectionFacturaRubroToAttach = em.getReference(facturaRubroCollectionFacturaRubroToAttach.getClass(), facturaRubroCollectionFacturaRubroToAttach.getIdDetRub());
                attachedFacturaRubroCollection.add(facturaRubroCollectionFacturaRubroToAttach);
            }
            rubro.setFacturaRubroCollection(attachedFacturaRubroCollection);
            em.persist(rubro);
            for (FacturaRubro facturaRubroCollectionFacturaRubro : rubro.getFacturaRubroCollection()) {
                Rubro oldIdRubroOfFacturaRubroCollectionFacturaRubro = facturaRubroCollectionFacturaRubro.getIdRubro();
                facturaRubroCollectionFacturaRubro.setIdRubro(rubro);
                facturaRubroCollectionFacturaRubro = em.merge(facturaRubroCollectionFacturaRubro);
                if (oldIdRubroOfFacturaRubroCollectionFacturaRubro != null) {
                    oldIdRubroOfFacturaRubroCollectionFacturaRubro.getFacturaRubroCollection().remove(facturaRubroCollectionFacturaRubro);
                    oldIdRubroOfFacturaRubroCollectionFacturaRubro = em.merge(oldIdRubroOfFacturaRubroCollectionFacturaRubro);
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
            Rubro persistentRubro = em.find(Rubro.class, rubro.getIdRubro());
            Collection<FacturaRubro> facturaRubroCollectionOld = persistentRubro.getFacturaRubroCollection();
            Collection<FacturaRubro> facturaRubroCollectionNew = rubro.getFacturaRubroCollection();
            Collection<FacturaRubro> attachedFacturaRubroCollectionNew = new ArrayList<FacturaRubro>();
            for (FacturaRubro facturaRubroCollectionNewFacturaRubroToAttach : facturaRubroCollectionNew) {
                facturaRubroCollectionNewFacturaRubroToAttach = em.getReference(facturaRubroCollectionNewFacturaRubroToAttach.getClass(), facturaRubroCollectionNewFacturaRubroToAttach.getIdDetRub());
                attachedFacturaRubroCollectionNew.add(facturaRubroCollectionNewFacturaRubroToAttach);
            }
            facturaRubroCollectionNew = attachedFacturaRubroCollectionNew;
            rubro.setFacturaRubroCollection(facturaRubroCollectionNew);
            rubro = em.merge(rubro);
            for (FacturaRubro facturaRubroCollectionOldFacturaRubro : facturaRubroCollectionOld) {
                if (!facturaRubroCollectionNew.contains(facturaRubroCollectionOldFacturaRubro)) {
                    facturaRubroCollectionOldFacturaRubro.setIdRubro(null);
                    facturaRubroCollectionOldFacturaRubro = em.merge(facturaRubroCollectionOldFacturaRubro);
                }
            }
            for (FacturaRubro facturaRubroCollectionNewFacturaRubro : facturaRubroCollectionNew) {
                if (!facturaRubroCollectionOld.contains(facturaRubroCollectionNewFacturaRubro)) {
                    Rubro oldIdRubroOfFacturaRubroCollectionNewFacturaRubro = facturaRubroCollectionNewFacturaRubro.getIdRubro();
                    facturaRubroCollectionNewFacturaRubro.setIdRubro(rubro);
                    facturaRubroCollectionNewFacturaRubro = em.merge(facturaRubroCollectionNewFacturaRubro);
                    if (oldIdRubroOfFacturaRubroCollectionNewFacturaRubro != null && !oldIdRubroOfFacturaRubroCollectionNewFacturaRubro.equals(rubro)) {
                        oldIdRubroOfFacturaRubroCollectionNewFacturaRubro.getFacturaRubroCollection().remove(facturaRubroCollectionNewFacturaRubro);
                        oldIdRubroOfFacturaRubroCollectionNewFacturaRubro = em.merge(oldIdRubroOfFacturaRubroCollectionNewFacturaRubro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rubro.getIdRubro();
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
                rubro.getIdRubro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rubro with id " + id + " no longer exists.", enfe);
            }
            Collection<FacturaRubro> facturaRubroCollection = rubro.getFacturaRubroCollection();
            for (FacturaRubro facturaRubroCollectionFacturaRubro : facturaRubroCollection) {
                facturaRubroCollectionFacturaRubro.setIdRubro(null);
                facturaRubroCollectionFacturaRubro = em.merge(facturaRubroCollectionFacturaRubro);
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
