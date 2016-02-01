/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlladores;

import com.ec.controlladores.exceptions.NonexistentEntityException;
import com.ec.controlladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ec.entidades.Factura;
import com.ec.entidades.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author david
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getFacturaCollection() == null) {
            usuario.setFacturaCollection(new ArrayList<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Factura> attachedFacturaCollection = new ArrayList<Factura>();
            for (Factura facturaCollectionFacturaToAttach : usuario.getFacturaCollection()) {
                facturaCollectionFacturaToAttach = em.getReference(facturaCollectionFacturaToAttach.getClass(), facturaCollectionFacturaToAttach.getIdfactura());
                attachedFacturaCollection.add(facturaCollectionFacturaToAttach);
            }
            usuario.setFacturaCollection(attachedFacturaCollection);
            em.persist(usuario);
            for (Factura facturaCollectionFactura : usuario.getFacturaCollection()) {
                Usuario oldIdUsuOfFacturaCollectionFactura = facturaCollectionFactura.getIdUsu();
                facturaCollectionFactura.setIdUsu(usuario);
                facturaCollectionFactura = em.merge(facturaCollectionFactura);
                if (oldIdUsuOfFacturaCollectionFactura != null) {
                    oldIdUsuOfFacturaCollectionFactura.getFacturaCollection().remove(facturaCollectionFactura);
                    oldIdUsuOfFacturaCollectionFactura = em.merge(oldIdUsuOfFacturaCollectionFactura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getIdUsu()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
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
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsu());
            Collection<Factura> facturaCollectionOld = persistentUsuario.getFacturaCollection();
            Collection<Factura> facturaCollectionNew = usuario.getFacturaCollection();
            Collection<Factura> attachedFacturaCollectionNew = new ArrayList<Factura>();
            for (Factura facturaCollectionNewFacturaToAttach : facturaCollectionNew) {
                facturaCollectionNewFacturaToAttach = em.getReference(facturaCollectionNewFacturaToAttach.getClass(), facturaCollectionNewFacturaToAttach.getIdfactura());
                attachedFacturaCollectionNew.add(facturaCollectionNewFacturaToAttach);
            }
            facturaCollectionNew = attachedFacturaCollectionNew;
            usuario.setFacturaCollection(facturaCollectionNew);
            usuario = em.merge(usuario);
            for (Factura facturaCollectionOldFactura : facturaCollectionOld) {
                if (!facturaCollectionNew.contains(facturaCollectionOldFactura)) {
                    facturaCollectionOldFactura.setIdUsu(null);
                    facturaCollectionOldFactura = em.merge(facturaCollectionOldFactura);
                }
            }
            for (Factura facturaCollectionNewFactura : facturaCollectionNew) {
                if (!facturaCollectionOld.contains(facturaCollectionNewFactura)) {
                    Usuario oldIdUsuOfFacturaCollectionNewFactura = facturaCollectionNewFactura.getIdUsu();
                    facturaCollectionNewFactura.setIdUsu(usuario);
                    facturaCollectionNewFactura = em.merge(facturaCollectionNewFactura);
                    if (oldIdUsuOfFacturaCollectionNewFactura != null && !oldIdUsuOfFacturaCollectionNewFactura.equals(usuario)) {
                        oldIdUsuOfFacturaCollectionNewFactura.getFacturaCollection().remove(facturaCollectionNewFactura);
                        oldIdUsuOfFacturaCollectionNewFactura = em.merge(oldIdUsuOfFacturaCollectionNewFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsu();
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
                usuario.getIdUsu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Collection<Factura> facturaCollection = usuario.getFacturaCollection();
            for (Factura facturaCollectionFactura : facturaCollection) {
                facturaCollectionFactura.setIdUsu(null);
                facturaCollectionFactura = em.merge(facturaCollectionFactura);
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
