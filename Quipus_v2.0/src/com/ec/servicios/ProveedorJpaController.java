/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicios;

import com.ec.entidades.Proveedor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ec.entidades.ProveedorFactura;
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
public class ProveedorJpaController implements Serializable {

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) {
        if (proveedor.getProveedorFacturaCollection() == null) {
            proveedor.setProveedorFacturaCollection(new ArrayList<ProveedorFactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ProveedorFactura> attachedProveedorFacturaCollection = new ArrayList<ProveedorFactura>();
            for (ProveedorFactura proveedorFacturaCollectionProveedorFacturaToAttach : proveedor.getProveedorFacturaCollection()) {
                proveedorFacturaCollectionProveedorFacturaToAttach = em.getReference(proveedorFacturaCollectionProveedorFacturaToAttach.getClass(), proveedorFacturaCollectionProveedorFacturaToAttach.getIdProveeFacturador());
                attachedProveedorFacturaCollection.add(proveedorFacturaCollectionProveedorFacturaToAttach);
            }
            proveedor.setProveedorFacturaCollection(attachedProveedorFacturaCollection);
            em.persist(proveedor);
            for (ProveedorFactura proveedorFacturaCollectionProveedorFactura : proveedor.getProveedorFacturaCollection()) {
                Proveedor oldIdProveedorOfProveedorFacturaCollectionProveedorFactura = proveedorFacturaCollectionProveedorFactura.getIdProveedor();
                proveedorFacturaCollectionProveedorFactura.setIdProveedor(proveedor);
                proveedorFacturaCollectionProveedorFactura = em.merge(proveedorFacturaCollectionProveedorFactura);
                if (oldIdProveedorOfProveedorFacturaCollectionProveedorFactura != null) {
                    oldIdProveedorOfProveedorFacturaCollectionProveedorFactura.getProveedorFacturaCollection().remove(proveedorFacturaCollectionProveedorFactura);
                    oldIdProveedorOfProveedorFacturaCollectionProveedorFactura = em.merge(oldIdProveedorOfProveedorFacturaCollectionProveedorFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getIdProveedor());
            Collection<ProveedorFactura> proveedorFacturaCollectionOld = persistentProveedor.getProveedorFacturaCollection();
            Collection<ProveedorFactura> proveedorFacturaCollectionNew = proveedor.getProveedorFacturaCollection();
            Collection<ProveedorFactura> attachedProveedorFacturaCollectionNew = new ArrayList<ProveedorFactura>();
            for (ProveedorFactura proveedorFacturaCollectionNewProveedorFacturaToAttach : proveedorFacturaCollectionNew) {
                proveedorFacturaCollectionNewProveedorFacturaToAttach = em.getReference(proveedorFacturaCollectionNewProveedorFacturaToAttach.getClass(), proveedorFacturaCollectionNewProveedorFacturaToAttach.getIdProveeFacturador());
                attachedProveedorFacturaCollectionNew.add(proveedorFacturaCollectionNewProveedorFacturaToAttach);
            }
            proveedorFacturaCollectionNew = attachedProveedorFacturaCollectionNew;
            proveedor.setProveedorFacturaCollection(proveedorFacturaCollectionNew);
            proveedor = em.merge(proveedor);
            for (ProveedorFactura proveedorFacturaCollectionOldProveedorFactura : proveedorFacturaCollectionOld) {
                if (!proveedorFacturaCollectionNew.contains(proveedorFacturaCollectionOldProveedorFactura)) {
                    proveedorFacturaCollectionOldProveedorFactura.setIdProveedor(null);
                    proveedorFacturaCollectionOldProveedorFactura = em.merge(proveedorFacturaCollectionOldProveedorFactura);
                }
            }
            for (ProveedorFactura proveedorFacturaCollectionNewProveedorFactura : proveedorFacturaCollectionNew) {
                if (!proveedorFacturaCollectionOld.contains(proveedorFacturaCollectionNewProveedorFactura)) {
                    Proveedor oldIdProveedorOfProveedorFacturaCollectionNewProveedorFactura = proveedorFacturaCollectionNewProveedorFactura.getIdProveedor();
                    proveedorFacturaCollectionNewProveedorFactura.setIdProveedor(proveedor);
                    proveedorFacturaCollectionNewProveedorFactura = em.merge(proveedorFacturaCollectionNewProveedorFactura);
                    if (oldIdProveedorOfProveedorFacturaCollectionNewProveedorFactura != null && !oldIdProveedorOfProveedorFacturaCollectionNewProveedorFactura.equals(proveedor)) {
                        oldIdProveedorOfProveedorFacturaCollectionNewProveedorFactura.getProveedorFacturaCollection().remove(proveedorFacturaCollectionNewProveedorFactura);
                        oldIdProveedorOfProveedorFacturaCollectionNewProveedorFactura = em.merge(oldIdProveedorOfProveedorFacturaCollectionNewProveedorFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedor.getIdProveedor();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getIdProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            Collection<ProveedorFactura> proveedorFacturaCollection = proveedor.getProveedorFacturaCollection();
            for (ProveedorFactura proveedorFacturaCollectionProveedorFactura : proveedorFacturaCollection) {
                proveedorFacturaCollectionProveedorFactura.setIdProveedor(null);
                proveedorFacturaCollectionProveedorFactura = em.merge(proveedorFacturaCollectionProveedorFactura);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
