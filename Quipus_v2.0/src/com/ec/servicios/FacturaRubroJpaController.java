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
import com.ec.entidades.Factura;
import com.ec.entidades.FacturaRubro;
import com.ec.servicios.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Cchristico
 */
public class FacturaRubroJpaController implements Serializable {

    public FacturaRubroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FacturaRubro facturaRubro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rubro idRubro = facturaRubro.getIdRubro();
            if (idRubro != null) {
                idRubro = em.getReference(idRubro.getClass(), idRubro.getIdRubro());
                facturaRubro.setIdRubro(idRubro);
            }
            Factura idFactura = facturaRubro.getIdFactura();
            if (idFactura != null) {
                idFactura = em.getReference(idFactura.getClass(), idFactura.getIdFactura());
                facturaRubro.setIdFactura(idFactura);
            }
            em.persist(facturaRubro);
            if (idRubro != null) {
                idRubro.getFacturaRubroCollection().add(facturaRubro);
                idRubro = em.merge(idRubro);
            }
            if (idFactura != null) {
                idFactura.getFacturaRubroCollection().add(facturaRubro);
                idFactura = em.merge(idFactura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FacturaRubro facturaRubro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacturaRubro persistentFacturaRubro = em.find(FacturaRubro.class, facturaRubro.getIdDetRub());
            Rubro idRubroOld = persistentFacturaRubro.getIdRubro();
            Rubro idRubroNew = facturaRubro.getIdRubro();
            Factura idFacturaOld = persistentFacturaRubro.getIdFactura();
            Factura idFacturaNew = facturaRubro.getIdFactura();
            if (idRubroNew != null) {
                idRubroNew = em.getReference(idRubroNew.getClass(), idRubroNew.getIdRubro());
                facturaRubro.setIdRubro(idRubroNew);
            }
            if (idFacturaNew != null) {
                idFacturaNew = em.getReference(idFacturaNew.getClass(), idFacturaNew.getIdFactura());
                facturaRubro.setIdFactura(idFacturaNew);
            }
            facturaRubro = em.merge(facturaRubro);
            if (idRubroOld != null && !idRubroOld.equals(idRubroNew)) {
                idRubroOld.getFacturaRubroCollection().remove(facturaRubro);
                idRubroOld = em.merge(idRubroOld);
            }
            if (idRubroNew != null && !idRubroNew.equals(idRubroOld)) {
                idRubroNew.getFacturaRubroCollection().add(facturaRubro);
                idRubroNew = em.merge(idRubroNew);
            }
            if (idFacturaOld != null && !idFacturaOld.equals(idFacturaNew)) {
                idFacturaOld.getFacturaRubroCollection().remove(facturaRubro);
                idFacturaOld = em.merge(idFacturaOld);
            }
            if (idFacturaNew != null && !idFacturaNew.equals(idFacturaOld)) {
                idFacturaNew.getFacturaRubroCollection().add(facturaRubro);
                idFacturaNew = em.merge(idFacturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturaRubro.getIdDetRub();
                if (findFacturaRubro(id) == null) {
                    throw new NonexistentEntityException("The facturaRubro with id " + id + " no longer exists.");
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
            FacturaRubro facturaRubro;
            try {
                facturaRubro = em.getReference(FacturaRubro.class, id);
                facturaRubro.getIdDetRub();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturaRubro with id " + id + " no longer exists.", enfe);
            }
            Rubro idRubro = facturaRubro.getIdRubro();
            if (idRubro != null) {
                idRubro.getFacturaRubroCollection().remove(facturaRubro);
                idRubro = em.merge(idRubro);
            }
            Factura idFactura = facturaRubro.getIdFactura();
            if (idFactura != null) {
                idFactura.getFacturaRubroCollection().remove(facturaRubro);
                idFactura = em.merge(idFactura);
            }
            em.remove(facturaRubro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FacturaRubro> findFacturaRubroEntities() {
        return findFacturaRubroEntities(true, -1, -1);
    }

    public List<FacturaRubro> findFacturaRubroEntities(int maxResults, int firstResult) {
        return findFacturaRubroEntities(false, maxResults, firstResult);
    }

    private List<FacturaRubro> findFacturaRubroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacturaRubro.class));
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

    public FacturaRubro findFacturaRubro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacturaRubro.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaRubroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FacturaRubro> rt = cq.from(FacturaRubro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
