/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlladores;

import com.ec.controlladores.exceptions.IllegalOrphanException;
import com.ec.controlladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ec.entidades.Factura;
import com.ec.entidades.Facturaingreso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author david
 */
public class FacturaingresoJpaController implements Serializable {

    public FacturaingresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facturaingreso facturaingreso) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Factura facturaOrphanCheck = facturaingreso.getFactura();
        if (facturaOrphanCheck != null) {
            Facturaingreso oldFacturaingresoOfFactura = facturaOrphanCheck.getFacturaingreso();
            if (oldFacturaingresoOfFactura != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Factura " + facturaOrphanCheck + " already has an item of type Facturaingreso whose factura column cannot be null. Please make another selection for the factura field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura factura = facturaingreso.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getIdfactura());
                facturaingreso.setFactura(factura);
            }
            em.persist(facturaingreso);
            if (factura != null) {
                factura.setFacturaingreso(facturaingreso);
                factura = em.merge(factura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facturaingreso facturaingreso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturaingreso persistentFacturaingreso = em.find(Facturaingreso.class, facturaingreso.getIdfactura());
            Factura facturaOld = persistentFacturaingreso.getFactura();
            Factura facturaNew = facturaingreso.getFactura();
            List<String> illegalOrphanMessages = null;
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                Facturaingreso oldFacturaingresoOfFactura = facturaNew.getFacturaingreso();
                if (oldFacturaingresoOfFactura != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Factura " + facturaNew + " already has an item of type Facturaingreso whose factura column cannot be null. Please make another selection for the factura field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getIdfactura());
                facturaingreso.setFactura(facturaNew);
            }
            facturaingreso = em.merge(facturaingreso);
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.setFacturaingreso(null);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.setFacturaingreso(facturaingreso);
                facturaNew = em.merge(facturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturaingreso.getIdfactura();
                if (findFacturaingreso(id) == null) {
                    throw new NonexistentEntityException("The facturaingreso with id " + id + " no longer exists.");
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
            Facturaingreso facturaingreso;
            try {
                facturaingreso = em.getReference(Facturaingreso.class, id);
                facturaingreso.getIdfactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturaingreso with id " + id + " no longer exists.", enfe);
            }
            Factura factura = facturaingreso.getFactura();
            if (factura != null) {
                factura.setFacturaingreso(null);
                factura = em.merge(factura);
            }
            em.remove(facturaingreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facturaingreso> findFacturaingresoEntities() {
        return findFacturaingresoEntities(true, -1, -1);
    }

    public List<Facturaingreso> findFacturaingresoEntities(int maxResults, int firstResult) {
        return findFacturaingresoEntities(false, maxResults, firstResult);
    }

    private List<Facturaingreso> findFacturaingresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facturaingreso.class));
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

    public Facturaingreso findFacturaingreso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facturaingreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaingresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facturaingreso> rt = cq.from(Facturaingreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
