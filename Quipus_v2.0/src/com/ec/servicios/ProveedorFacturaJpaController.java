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
import com.ec.entidades.Proveedor;
import com.ec.entidades.Factura;
import com.ec.entidades.ProveedorFactura;
import com.ec.servicios.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Cchristico
 */
public class ProveedorFacturaJpaController implements Serializable {

    public ProveedorFacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProveedorFactura proveedorFactura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor idProveedor = proveedorFactura.getIdProveedor();
            if (idProveedor != null) {
                idProveedor = em.getReference(idProveedor.getClass(), idProveedor.getIdProveedor());
                proveedorFactura.setIdProveedor(idProveedor);
            }
            Factura idFactura = proveedorFactura.getIdFactura();
            if (idFactura != null) {
                idFactura = em.getReference(idFactura.getClass(), idFactura.getIdFactura());
                proveedorFactura.setIdFactura(idFactura);
            }
            em.persist(proveedorFactura);
            if (idProveedor != null) {
                idProveedor.getProveedorFacturaCollection().add(proveedorFactura);
                idProveedor = em.merge(idProveedor);
            }
            if (idFactura != null) {
                idFactura.getProveedorFacturaCollection().add(proveedorFactura);
                idFactura = em.merge(idFactura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProveedorFactura proveedorFactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProveedorFactura persistentProveedorFactura = em.find(ProveedorFactura.class, proveedorFactura.getIdProveeFacturador());
            Proveedor idProveedorOld = persistentProveedorFactura.getIdProveedor();
            Proveedor idProveedorNew = proveedorFactura.getIdProveedor();
            Factura idFacturaOld = persistentProveedorFactura.getIdFactura();
            Factura idFacturaNew = proveedorFactura.getIdFactura();
            if (idProveedorNew != null) {
                idProveedorNew = em.getReference(idProveedorNew.getClass(), idProveedorNew.getIdProveedor());
                proveedorFactura.setIdProveedor(idProveedorNew);
            }
            if (idFacturaNew != null) {
                idFacturaNew = em.getReference(idFacturaNew.getClass(), idFacturaNew.getIdFactura());
                proveedorFactura.setIdFactura(idFacturaNew);
            }
            proveedorFactura = em.merge(proveedorFactura);
            if (idProveedorOld != null && !idProveedorOld.equals(idProveedorNew)) {
                idProveedorOld.getProveedorFacturaCollection().remove(proveedorFactura);
                idProveedorOld = em.merge(idProveedorOld);
            }
            if (idProveedorNew != null && !idProveedorNew.equals(idProveedorOld)) {
                idProveedorNew.getProveedorFacturaCollection().add(proveedorFactura);
                idProveedorNew = em.merge(idProveedorNew);
            }
            if (idFacturaOld != null && !idFacturaOld.equals(idFacturaNew)) {
                idFacturaOld.getProveedorFacturaCollection().remove(proveedorFactura);
                idFacturaOld = em.merge(idFacturaOld);
            }
            if (idFacturaNew != null && !idFacturaNew.equals(idFacturaOld)) {
                idFacturaNew.getProveedorFacturaCollection().add(proveedorFactura);
                idFacturaNew = em.merge(idFacturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedorFactura.getIdProveeFacturador();
                if (findProveedorFactura(id) == null) {
                    throw new NonexistentEntityException("The proveedorFactura with id " + id + " no longer exists.");
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
            ProveedorFactura proveedorFactura;
            try {
                proveedorFactura = em.getReference(ProveedorFactura.class, id);
                proveedorFactura.getIdProveeFacturador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedorFactura with id " + id + " no longer exists.", enfe);
            }
            Proveedor idProveedor = proveedorFactura.getIdProveedor();
            if (idProveedor != null) {
                idProveedor.getProveedorFacturaCollection().remove(proveedorFactura);
                idProveedor = em.merge(idProveedor);
            }
            Factura idFactura = proveedorFactura.getIdFactura();
            if (idFactura != null) {
                idFactura.getProveedorFacturaCollection().remove(proveedorFactura);
                idFactura = em.merge(idFactura);
            }
            em.remove(proveedorFactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProveedorFactura> findProveedorFacturaEntities() {
        return findProveedorFacturaEntities(true, -1, -1);
    }

    public List<ProveedorFactura> findProveedorFacturaEntities(int maxResults, int firstResult) {
        return findProveedorFacturaEntities(false, maxResults, firstResult);
    }

    private List<ProveedorFactura> findProveedorFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProveedorFactura.class));
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

    public ProveedorFactura findProveedorFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProveedorFactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProveedorFactura> rt = cq.from(ProveedorFactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
