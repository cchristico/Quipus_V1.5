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
import com.ec.entidades.Detalle;
import com.ec.entidades.Usuario;
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
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getDetalleCollection() == null) {
            usuario.setDetalleCollection(new ArrayList<Detalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Detalle> attachedDetalleCollection = new ArrayList<Detalle>();
            for (Detalle detalleCollectionDetalleToAttach : usuario.getDetalleCollection()) {
                detalleCollectionDetalleToAttach = em.getReference(detalleCollectionDetalleToAttach.getClass(), detalleCollectionDetalleToAttach.getIdDetalle());
                attachedDetalleCollection.add(detalleCollectionDetalleToAttach);
            }
            usuario.setDetalleCollection(attachedDetalleCollection);
            em.persist(usuario);
            for (Detalle detalleCollectionDetalle : usuario.getDetalleCollection()) {
                Usuario oldIdUsuarioOfDetalleCollectionDetalle = detalleCollectionDetalle.getIdUsuario();
                detalleCollectionDetalle.setIdUsuario(usuario);
                detalleCollectionDetalle = em.merge(detalleCollectionDetalle);
                if (oldIdUsuarioOfDetalleCollectionDetalle != null) {
                    oldIdUsuarioOfDetalleCollectionDetalle.getDetalleCollection().remove(detalleCollectionDetalle);
                    oldIdUsuarioOfDetalleCollectionDetalle = em.merge(oldIdUsuarioOfDetalleCollectionDetalle);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Collection<Detalle> detalleCollectionOld = persistentUsuario.getDetalleCollection();
            Collection<Detalle> detalleCollectionNew = usuario.getDetalleCollection();
            Collection<Detalle> attachedDetalleCollectionNew = new ArrayList<Detalle>();
            for (Detalle detalleCollectionNewDetalleToAttach : detalleCollectionNew) {
                detalleCollectionNewDetalleToAttach = em.getReference(detalleCollectionNewDetalleToAttach.getClass(), detalleCollectionNewDetalleToAttach.getIdDetalle());
                attachedDetalleCollectionNew.add(detalleCollectionNewDetalleToAttach);
            }
            detalleCollectionNew = attachedDetalleCollectionNew;
            usuario.setDetalleCollection(detalleCollectionNew);
            usuario = em.merge(usuario);
            for (Detalle detalleCollectionOldDetalle : detalleCollectionOld) {
                if (!detalleCollectionNew.contains(detalleCollectionOldDetalle)) {
                    detalleCollectionOldDetalle.setIdUsuario(null);
                    detalleCollectionOldDetalle = em.merge(detalleCollectionOldDetalle);
                }
            }
            for (Detalle detalleCollectionNewDetalle : detalleCollectionNew) {
                if (!detalleCollectionOld.contains(detalleCollectionNewDetalle)) {
                    Usuario oldIdUsuarioOfDetalleCollectionNewDetalle = detalleCollectionNewDetalle.getIdUsuario();
                    detalleCollectionNewDetalle.setIdUsuario(usuario);
                    detalleCollectionNewDetalle = em.merge(detalleCollectionNewDetalle);
                    if (oldIdUsuarioOfDetalleCollectionNewDetalle != null && !oldIdUsuarioOfDetalleCollectionNewDetalle.equals(usuario)) {
                        oldIdUsuarioOfDetalleCollectionNewDetalle.getDetalleCollection().remove(detalleCollectionNewDetalle);
                        oldIdUsuarioOfDetalleCollectionNewDetalle = em.merge(oldIdUsuarioOfDetalleCollectionNewDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Collection<Detalle> detalleCollection = usuario.getDetalleCollection();
            for (Detalle detalleCollectionDetalle : detalleCollection) {
                detalleCollectionDetalle.setIdUsuario(null);
                detalleCollectionDetalle = em.merge(detalleCollectionDetalle);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
