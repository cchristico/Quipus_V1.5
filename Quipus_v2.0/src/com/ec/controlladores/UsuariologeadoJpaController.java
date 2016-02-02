/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlladores;

import com.ec.controlladores.exceptions.NonexistentEntityException;
import com.ec.controlladores.exceptions.PreexistingEntityException;
import com.ec.entidades.Usuariologeado;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Cchristico
 */
public class UsuariologeadoJpaController implements Serializable {

    public UsuariologeadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuariologeado usuariologeado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuariologeado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuariologeado(usuariologeado.getId()) != null) {
                throw new PreexistingEntityException("Usuariologeado " + usuariologeado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuariologeado usuariologeado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuariologeado = em.merge(usuariologeado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuariologeado.getId();
                if (findUsuariologeado(id) == null) {
                    throw new NonexistentEntityException("The usuariologeado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuariologeado usuariologeado;
            try {
                usuariologeado = em.getReference(Usuariologeado.class, id);
                usuariologeado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuariologeado with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuariologeado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuariologeado> findUsuariologeadoEntities() {
        return findUsuariologeadoEntities(true, -1, -1);
    }

    public List<Usuariologeado> findUsuariologeadoEntities(int maxResults, int firstResult) {
        return findUsuariologeadoEntities(false, maxResults, firstResult);
    }

    private List<Usuariologeado> findUsuariologeadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuariologeado.class));
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

    public Usuariologeado findUsuariologeado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuariologeado.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariologeadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuariologeado> rt = cq.from(Usuariologeado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
