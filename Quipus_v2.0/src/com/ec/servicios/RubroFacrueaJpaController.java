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
import com.ec.entidades.Rubro;
import java.util.ArrayList;
import java.util.Collection;
import com.ec.entidades.Factura;
import com.ec.entidades.RubroFacruea;
import com.ec.servicios.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Cchristico
 */
public class RubroFacrueaJpaController implements Serializable {

    public RubroFacrueaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RubroFacruea rubroFacruea) {
        if (rubroFacruea.getRubroCollection() == null) {
            rubroFacruea.setRubroCollection(new ArrayList<Rubro>());
        }
        if (rubroFacruea.getFacturaCollection() == null) {
            rubroFacruea.setFacturaCollection(new ArrayList<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Rubro> attachedRubroCollection = new ArrayList<Rubro>();
            for (Rubro rubroCollectionRubroToAttach : rubroFacruea.getRubroCollection()) {
                rubroCollectionRubroToAttach = em.getReference(rubroCollectionRubroToAttach.getClass(), rubroCollectionRubroToAttach.getIdRubro());
                attachedRubroCollection.add(rubroCollectionRubroToAttach);
            }
            rubroFacruea.setRubroCollection(attachedRubroCollection);
            Collection<Factura> attachedFacturaCollection = new ArrayList<Factura>();
            for (Factura facturaCollectionFacturaToAttach : rubroFacruea.getFacturaCollection()) {
                facturaCollectionFacturaToAttach = em.getReference(facturaCollectionFacturaToAttach.getClass(), facturaCollectionFacturaToAttach.getIdFactu());
                attachedFacturaCollection.add(facturaCollectionFacturaToAttach);
            }
            rubroFacruea.setFacturaCollection(attachedFacturaCollection);
            em.persist(rubroFacruea);
            for (Rubro rubroCollectionRubro : rubroFacruea.getRubroCollection()) {
                RubroFacruea oldIdRubroFacruraOfRubroCollectionRubro = rubroCollectionRubro.getIdRubroFacrura();
                rubroCollectionRubro.setIdRubroFacrura(rubroFacruea);
                rubroCollectionRubro = em.merge(rubroCollectionRubro);
                if (oldIdRubroFacruraOfRubroCollectionRubro != null) {
                    oldIdRubroFacruraOfRubroCollectionRubro.getRubroCollection().remove(rubroCollectionRubro);
                    oldIdRubroFacruraOfRubroCollectionRubro = em.merge(oldIdRubroFacruraOfRubroCollectionRubro);
                }
            }
            for (Factura facturaCollectionFactura : rubroFacruea.getFacturaCollection()) {
                RubroFacruea oldIdRubroFacruraOfFacturaCollectionFactura = facturaCollectionFactura.getIdRubroFacrura();
                facturaCollectionFactura.setIdRubroFacrura(rubroFacruea);
                facturaCollectionFactura = em.merge(facturaCollectionFactura);
                if (oldIdRubroFacruraOfFacturaCollectionFactura != null) {
                    oldIdRubroFacruraOfFacturaCollectionFactura.getFacturaCollection().remove(facturaCollectionFactura);
                    oldIdRubroFacruraOfFacturaCollectionFactura = em.merge(oldIdRubroFacruraOfFacturaCollectionFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RubroFacruea rubroFacruea) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RubroFacruea persistentRubroFacruea = em.find(RubroFacruea.class, rubroFacruea.getIdRubroFacrura());
            Collection<Rubro> rubroCollectionOld = persistentRubroFacruea.getRubroCollection();
            Collection<Rubro> rubroCollectionNew = rubroFacruea.getRubroCollection();
            Collection<Factura> facturaCollectionOld = persistentRubroFacruea.getFacturaCollection();
            Collection<Factura> facturaCollectionNew = rubroFacruea.getFacturaCollection();
            Collection<Rubro> attachedRubroCollectionNew = new ArrayList<Rubro>();
            for (Rubro rubroCollectionNewRubroToAttach : rubroCollectionNew) {
                rubroCollectionNewRubroToAttach = em.getReference(rubroCollectionNewRubroToAttach.getClass(), rubroCollectionNewRubroToAttach.getIdRubro());
                attachedRubroCollectionNew.add(rubroCollectionNewRubroToAttach);
            }
            rubroCollectionNew = attachedRubroCollectionNew;
            rubroFacruea.setRubroCollection(rubroCollectionNew);
            Collection<Factura> attachedFacturaCollectionNew = new ArrayList<Factura>();
            for (Factura facturaCollectionNewFacturaToAttach : facturaCollectionNew) {
                facturaCollectionNewFacturaToAttach = em.getReference(facturaCollectionNewFacturaToAttach.getClass(), facturaCollectionNewFacturaToAttach.getIdFactu());
                attachedFacturaCollectionNew.add(facturaCollectionNewFacturaToAttach);
            }
            facturaCollectionNew = attachedFacturaCollectionNew;
            rubroFacruea.setFacturaCollection(facturaCollectionNew);
            rubroFacruea = em.merge(rubroFacruea);
            for (Rubro rubroCollectionOldRubro : rubroCollectionOld) {
                if (!rubroCollectionNew.contains(rubroCollectionOldRubro)) {
                    rubroCollectionOldRubro.setIdRubroFacrura(null);
                    rubroCollectionOldRubro = em.merge(rubroCollectionOldRubro);
                }
            }
            for (Rubro rubroCollectionNewRubro : rubroCollectionNew) {
                if (!rubroCollectionOld.contains(rubroCollectionNewRubro)) {
                    RubroFacruea oldIdRubroFacruraOfRubroCollectionNewRubro = rubroCollectionNewRubro.getIdRubroFacrura();
                    rubroCollectionNewRubro.setIdRubroFacrura(rubroFacruea);
                    rubroCollectionNewRubro = em.merge(rubroCollectionNewRubro);
                    if (oldIdRubroFacruraOfRubroCollectionNewRubro != null && !oldIdRubroFacruraOfRubroCollectionNewRubro.equals(rubroFacruea)) {
                        oldIdRubroFacruraOfRubroCollectionNewRubro.getRubroCollection().remove(rubroCollectionNewRubro);
                        oldIdRubroFacruraOfRubroCollectionNewRubro = em.merge(oldIdRubroFacruraOfRubroCollectionNewRubro);
                    }
                }
            }
            for (Factura facturaCollectionOldFactura : facturaCollectionOld) {
                if (!facturaCollectionNew.contains(facturaCollectionOldFactura)) {
                    facturaCollectionOldFactura.setIdRubroFacrura(null);
                    facturaCollectionOldFactura = em.merge(facturaCollectionOldFactura);
                }
            }
            for (Factura facturaCollectionNewFactura : facturaCollectionNew) {
                if (!facturaCollectionOld.contains(facturaCollectionNewFactura)) {
                    RubroFacruea oldIdRubroFacruraOfFacturaCollectionNewFactura = facturaCollectionNewFactura.getIdRubroFacrura();
                    facturaCollectionNewFactura.setIdRubroFacrura(rubroFacruea);
                    facturaCollectionNewFactura = em.merge(facturaCollectionNewFactura);
                    if (oldIdRubroFacruraOfFacturaCollectionNewFactura != null && !oldIdRubroFacruraOfFacturaCollectionNewFactura.equals(rubroFacruea)) {
                        oldIdRubroFacruraOfFacturaCollectionNewFactura.getFacturaCollection().remove(facturaCollectionNewFactura);
                        oldIdRubroFacruraOfFacturaCollectionNewFactura = em.merge(oldIdRubroFacruraOfFacturaCollectionNewFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rubroFacruea.getIdRubroFacrura();
                if (findRubroFacruea(id) == null) {
                    throw new NonexistentEntityException("The rubroFacruea with id " + id + " no longer exists.");
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
            RubroFacruea rubroFacruea;
            try {
                rubroFacruea = em.getReference(RubroFacruea.class, id);
                rubroFacruea.getIdRubroFacrura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rubroFacruea with id " + id + " no longer exists.", enfe);
            }
            Collection<Rubro> rubroCollection = rubroFacruea.getRubroCollection();
            for (Rubro rubroCollectionRubro : rubroCollection) {
                rubroCollectionRubro.setIdRubroFacrura(null);
                rubroCollectionRubro = em.merge(rubroCollectionRubro);
            }
            Collection<Factura> facturaCollection = rubroFacruea.getFacturaCollection();
            for (Factura facturaCollectionFactura : facturaCollection) {
                facturaCollectionFactura.setIdRubroFacrura(null);
                facturaCollectionFactura = em.merge(facturaCollectionFactura);
            }
            em.remove(rubroFacruea);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RubroFacruea> findRubroFacrueaEntities() {
        return findRubroFacrueaEntities(true, -1, -1);
    }

    public List<RubroFacruea> findRubroFacrueaEntities(int maxResults, int firstResult) {
        return findRubroFacrueaEntities(false, maxResults, firstResult);
    }

    private List<RubroFacruea> findRubroFacrueaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RubroFacruea.class));
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

    public RubroFacruea findRubroFacruea(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RubroFacruea.class, id);
        } finally {
            em.close();
        }
    }

    public int getRubroFacrueaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RubroFacruea> rt = cq.from(RubroFacruea.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
