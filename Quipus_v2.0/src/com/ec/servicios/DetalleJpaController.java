/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicios;

import com.ec.entidades.Detalle;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ec.entidades.Factura;
import com.ec.entidades.Usuario;
import com.ec.servicios.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Cchristico
 */
public class DetalleJpaController implements Serializable {

    public DetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalle detalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura idFactu = detalle.getIdFactu();
            if (idFactu != null) {
                idFactu = em.getReference(idFactu.getClass(), idFactu.getIdFactu());
                detalle.setIdFactu(idFactu);
            }
            Usuario idUsuario = detalle.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                detalle.setIdUsuario(idUsuario);
            }
            em.persist(detalle);
            if (idFactu != null) {
                idFactu.getDetalleCollection().add(detalle);
                idFactu = em.merge(idFactu);
            }
            if (idUsuario != null) {
                idUsuario.getDetalleCollection().add(detalle);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalle detalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalle persistentDetalle = em.find(Detalle.class, detalle.getIdDetalle());
            Factura idFactuOld = persistentDetalle.getIdFactu();
            Factura idFactuNew = detalle.getIdFactu();
            Usuario idUsuarioOld = persistentDetalle.getIdUsuario();
            Usuario idUsuarioNew = detalle.getIdUsuario();
            if (idFactuNew != null) {
                idFactuNew = em.getReference(idFactuNew.getClass(), idFactuNew.getIdFactu());
                detalle.setIdFactu(idFactuNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                detalle.setIdUsuario(idUsuarioNew);
            }
            detalle = em.merge(detalle);
            if (idFactuOld != null && !idFactuOld.equals(idFactuNew)) {
                idFactuOld.getDetalleCollection().remove(detalle);
                idFactuOld = em.merge(idFactuOld);
            }
            if (idFactuNew != null && !idFactuNew.equals(idFactuOld)) {
                idFactuNew.getDetalleCollection().add(detalle);
                idFactuNew = em.merge(idFactuNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getDetalleCollection().remove(detalle);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getDetalleCollection().add(detalle);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalle.getIdDetalle();
                if (findDetalle(id) == null) {
                    throw new NonexistentEntityException("The detalle with id " + id + " no longer exists.");
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
            Detalle detalle;
            try {
                detalle = em.getReference(Detalle.class, id);
                detalle.getIdDetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalle with id " + id + " no longer exists.", enfe);
            }
            Factura idFactu = detalle.getIdFactu();
            if (idFactu != null) {
                idFactu.getDetalleCollection().remove(detalle);
                idFactu = em.merge(idFactu);
            }
            Usuario idUsuario = detalle.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getDetalleCollection().remove(detalle);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(detalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalle> findDetalleEntities() {
        return findDetalleEntities(true, -1, -1);
    }

    public List<Detalle> findDetalleEntities(int maxResults, int firstResult) {
        return findDetalleEntities(false, maxResults, firstResult);
    }

    private List<Detalle> findDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalle.class));
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

    public Detalle findDetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalle> rt = cq.from(Detalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
