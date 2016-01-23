/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicios;

import com.ec.entidades.Factura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ec.entidades.Usuario;
import com.ec.entidades.FacturaRubro;
import java.util.ArrayList;
import java.util.Collection;
import com.ec.entidades.ProveedorFactura;
import com.ec.servicios.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Cchristico
 */
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        if (factura.getFacturaRubroCollection() == null) {
            factura.setFacturaRubroCollection(new ArrayList<FacturaRubro>());
        }
        if (factura.getProveedorFacturaCollection() == null) {
            factura.setProveedorFacturaCollection(new ArrayList<ProveedorFactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idUsuario = factura.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                factura.setIdUsuario(idUsuario);
            }
            Collection<FacturaRubro> attachedFacturaRubroCollection = new ArrayList<FacturaRubro>();
            for (FacturaRubro facturaRubroCollectionFacturaRubroToAttach : factura.getFacturaRubroCollection()) {
                facturaRubroCollectionFacturaRubroToAttach = em.getReference(facturaRubroCollectionFacturaRubroToAttach.getClass(), facturaRubroCollectionFacturaRubroToAttach.getIdDetRub());
                attachedFacturaRubroCollection.add(facturaRubroCollectionFacturaRubroToAttach);
            }
            factura.setFacturaRubroCollection(attachedFacturaRubroCollection);
            Collection<ProveedorFactura> attachedProveedorFacturaCollection = new ArrayList<ProveedorFactura>();
            for (ProveedorFactura proveedorFacturaCollectionProveedorFacturaToAttach : factura.getProveedorFacturaCollection()) {
                proveedorFacturaCollectionProveedorFacturaToAttach = em.getReference(proveedorFacturaCollectionProveedorFacturaToAttach.getClass(), proveedorFacturaCollectionProveedorFacturaToAttach.getIdProveeFacturador());
                attachedProveedorFacturaCollection.add(proveedorFacturaCollectionProveedorFacturaToAttach);
            }
            factura.setProveedorFacturaCollection(attachedProveedorFacturaCollection);
            em.persist(factura);
            if (idUsuario != null) {
                idUsuario.getFacturaCollection().add(factura);
                idUsuario = em.merge(idUsuario);
            }
            for (FacturaRubro facturaRubroCollectionFacturaRubro : factura.getFacturaRubroCollection()) {
                Factura oldIdFacturaOfFacturaRubroCollectionFacturaRubro = facturaRubroCollectionFacturaRubro.getIdFactura();
                facturaRubroCollectionFacturaRubro.setIdFactura(factura);
                facturaRubroCollectionFacturaRubro = em.merge(facturaRubroCollectionFacturaRubro);
                if (oldIdFacturaOfFacturaRubroCollectionFacturaRubro != null) {
                    oldIdFacturaOfFacturaRubroCollectionFacturaRubro.getFacturaRubroCollection().remove(facturaRubroCollectionFacturaRubro);
                    oldIdFacturaOfFacturaRubroCollectionFacturaRubro = em.merge(oldIdFacturaOfFacturaRubroCollectionFacturaRubro);
                }
            }
            for (ProveedorFactura proveedorFacturaCollectionProveedorFactura : factura.getProveedorFacturaCollection()) {
                Factura oldIdFacturaOfProveedorFacturaCollectionProveedorFactura = proveedorFacturaCollectionProveedorFactura.getIdFactura();
                proveedorFacturaCollectionProveedorFactura.setIdFactura(factura);
                proveedorFacturaCollectionProveedorFactura = em.merge(proveedorFacturaCollectionProveedorFactura);
                if (oldIdFacturaOfProveedorFacturaCollectionProveedorFactura != null) {
                    oldIdFacturaOfProveedorFacturaCollectionProveedorFactura.getProveedorFacturaCollection().remove(proveedorFacturaCollectionProveedorFactura);
                    oldIdFacturaOfProveedorFacturaCollectionProveedorFactura = em.merge(oldIdFacturaOfProveedorFacturaCollectionProveedorFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdFactura());
            Usuario idUsuarioOld = persistentFactura.getIdUsuario();
            Usuario idUsuarioNew = factura.getIdUsuario();
            Collection<FacturaRubro> facturaRubroCollectionOld = persistentFactura.getFacturaRubroCollection();
            Collection<FacturaRubro> facturaRubroCollectionNew = factura.getFacturaRubroCollection();
            Collection<ProveedorFactura> proveedorFacturaCollectionOld = persistentFactura.getProveedorFacturaCollection();
            Collection<ProveedorFactura> proveedorFacturaCollectionNew = factura.getProveedorFacturaCollection();
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                factura.setIdUsuario(idUsuarioNew);
            }
            Collection<FacturaRubro> attachedFacturaRubroCollectionNew = new ArrayList<FacturaRubro>();
            for (FacturaRubro facturaRubroCollectionNewFacturaRubroToAttach : facturaRubroCollectionNew) {
                facturaRubroCollectionNewFacturaRubroToAttach = em.getReference(facturaRubroCollectionNewFacturaRubroToAttach.getClass(), facturaRubroCollectionNewFacturaRubroToAttach.getIdDetRub());
                attachedFacturaRubroCollectionNew.add(facturaRubroCollectionNewFacturaRubroToAttach);
            }
            facturaRubroCollectionNew = attachedFacturaRubroCollectionNew;
            factura.setFacturaRubroCollection(facturaRubroCollectionNew);
            Collection<ProveedorFactura> attachedProveedorFacturaCollectionNew = new ArrayList<ProveedorFactura>();
            for (ProveedorFactura proveedorFacturaCollectionNewProveedorFacturaToAttach : proveedorFacturaCollectionNew) {
                proveedorFacturaCollectionNewProveedorFacturaToAttach = em.getReference(proveedorFacturaCollectionNewProveedorFacturaToAttach.getClass(), proveedorFacturaCollectionNewProveedorFacturaToAttach.getIdProveeFacturador());
                attachedProveedorFacturaCollectionNew.add(proveedorFacturaCollectionNewProveedorFacturaToAttach);
            }
            proveedorFacturaCollectionNew = attachedProveedorFacturaCollectionNew;
            factura.setProveedorFacturaCollection(proveedorFacturaCollectionNew);
            factura = em.merge(factura);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getFacturaCollection().remove(factura);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getFacturaCollection().add(factura);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (FacturaRubro facturaRubroCollectionOldFacturaRubro : facturaRubroCollectionOld) {
                if (!facturaRubroCollectionNew.contains(facturaRubroCollectionOldFacturaRubro)) {
                    facturaRubroCollectionOldFacturaRubro.setIdFactura(null);
                    facturaRubroCollectionOldFacturaRubro = em.merge(facturaRubroCollectionOldFacturaRubro);
                }
            }
            for (FacturaRubro facturaRubroCollectionNewFacturaRubro : facturaRubroCollectionNew) {
                if (!facturaRubroCollectionOld.contains(facturaRubroCollectionNewFacturaRubro)) {
                    Factura oldIdFacturaOfFacturaRubroCollectionNewFacturaRubro = facturaRubroCollectionNewFacturaRubro.getIdFactura();
                    facturaRubroCollectionNewFacturaRubro.setIdFactura(factura);
                    facturaRubroCollectionNewFacturaRubro = em.merge(facturaRubroCollectionNewFacturaRubro);
                    if (oldIdFacturaOfFacturaRubroCollectionNewFacturaRubro != null && !oldIdFacturaOfFacturaRubroCollectionNewFacturaRubro.equals(factura)) {
                        oldIdFacturaOfFacturaRubroCollectionNewFacturaRubro.getFacturaRubroCollection().remove(facturaRubroCollectionNewFacturaRubro);
                        oldIdFacturaOfFacturaRubroCollectionNewFacturaRubro = em.merge(oldIdFacturaOfFacturaRubroCollectionNewFacturaRubro);
                    }
                }
            }
            for (ProveedorFactura proveedorFacturaCollectionOldProveedorFactura : proveedorFacturaCollectionOld) {
                if (!proveedorFacturaCollectionNew.contains(proveedorFacturaCollectionOldProveedorFactura)) {
                    proveedorFacturaCollectionOldProveedorFactura.setIdFactura(null);
                    proveedorFacturaCollectionOldProveedorFactura = em.merge(proveedorFacturaCollectionOldProveedorFactura);
                }
            }
            for (ProveedorFactura proveedorFacturaCollectionNewProveedorFactura : proveedorFacturaCollectionNew) {
                if (!proveedorFacturaCollectionOld.contains(proveedorFacturaCollectionNewProveedorFactura)) {
                    Factura oldIdFacturaOfProveedorFacturaCollectionNewProveedorFactura = proveedorFacturaCollectionNewProveedorFactura.getIdFactura();
                    proveedorFacturaCollectionNewProveedorFactura.setIdFactura(factura);
                    proveedorFacturaCollectionNewProveedorFactura = em.merge(proveedorFacturaCollectionNewProveedorFactura);
                    if (oldIdFacturaOfProveedorFacturaCollectionNewProveedorFactura != null && !oldIdFacturaOfProveedorFacturaCollectionNewProveedorFactura.equals(factura)) {
                        oldIdFacturaOfProveedorFacturaCollectionNewProveedorFactura.getProveedorFacturaCollection().remove(proveedorFacturaCollectionNewProveedorFactura);
                        oldIdFacturaOfProveedorFacturaCollectionNewProveedorFactura = em.merge(oldIdFacturaOfProveedorFacturaCollectionNewProveedorFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getIdFactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            Usuario idUsuario = factura.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getFacturaCollection().remove(factura);
                idUsuario = em.merge(idUsuario);
            }
            Collection<FacturaRubro> facturaRubroCollection = factura.getFacturaRubroCollection();
            for (FacturaRubro facturaRubroCollectionFacturaRubro : facturaRubroCollection) {
                facturaRubroCollectionFacturaRubro.setIdFactura(null);
                facturaRubroCollectionFacturaRubro = em.merge(facturaRubroCollectionFacturaRubro);
            }
            Collection<ProveedorFactura> proveedorFacturaCollection = factura.getProveedorFacturaCollection();
            for (ProveedorFactura proveedorFacturaCollectionProveedorFactura : proveedorFacturaCollection) {
                proveedorFacturaCollectionProveedorFactura.setIdFactura(null);
                proveedorFacturaCollectionProveedorFactura = em.merge(proveedorFacturaCollectionProveedorFactura);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
