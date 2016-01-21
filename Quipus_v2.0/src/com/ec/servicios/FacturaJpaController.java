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
import com.ec.entidades.RubroFacruea;
import com.ec.entidades.Rubro;
import java.util.ArrayList;
import java.util.Collection;
import com.ec.entidades.Proveedor;
import com.ec.entidades.Detalle;
import com.ec.entidades.Factura;
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
        if (factura.getRubroCollection() == null) {
            factura.setRubroCollection(new ArrayList<Rubro>());
        }
        if (factura.getProveedorCollection() == null) {
            factura.setProveedorCollection(new ArrayList<Proveedor>());
        }
        if (factura.getDetalleCollection() == null) {
            factura.setDetalleCollection(new ArrayList<Detalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RubroFacruea idRubroFacrura = factura.getIdRubroFacrura();
            if (idRubroFacrura != null) {
                idRubroFacrura = em.getReference(idRubroFacrura.getClass(), idRubroFacrura.getIdRubroFacrura());
                factura.setIdRubroFacrura(idRubroFacrura);
            }
            Collection<Rubro> attachedRubroCollection = new ArrayList<Rubro>();
            for (Rubro rubroCollectionRubroToAttach : factura.getRubroCollection()) {
                rubroCollectionRubroToAttach = em.getReference(rubroCollectionRubroToAttach.getClass(), rubroCollectionRubroToAttach.getIdRubro());
                attachedRubroCollection.add(rubroCollectionRubroToAttach);
            }
            factura.setRubroCollection(attachedRubroCollection);
            Collection<Proveedor> attachedProveedorCollection = new ArrayList<Proveedor>();
            for (Proveedor proveedorCollectionProveedorToAttach : factura.getProveedorCollection()) {
                proveedorCollectionProveedorToAttach = em.getReference(proveedorCollectionProveedorToAttach.getClass(), proveedorCollectionProveedorToAttach.getIdProveedor());
                attachedProveedorCollection.add(proveedorCollectionProveedorToAttach);
            }
            factura.setProveedorCollection(attachedProveedorCollection);
            Collection<Detalle> attachedDetalleCollection = new ArrayList<Detalle>();
            for (Detalle detalleCollectionDetalleToAttach : factura.getDetalleCollection()) {
                detalleCollectionDetalleToAttach = em.getReference(detalleCollectionDetalleToAttach.getClass(), detalleCollectionDetalleToAttach.getIdDetalle());
                attachedDetalleCollection.add(detalleCollectionDetalleToAttach);
            }
            factura.setDetalleCollection(attachedDetalleCollection);
            em.persist(factura);
            if (idRubroFacrura != null) {
                idRubroFacrura.getFacturaCollection().add(factura);
                idRubroFacrura = em.merge(idRubroFacrura);
            }
            for (Rubro rubroCollectionRubro : factura.getRubroCollection()) {
                rubroCollectionRubro.getFacturaCollection().add(factura);
                rubroCollectionRubro = em.merge(rubroCollectionRubro);
            }
            for (Proveedor proveedorCollectionProveedor : factura.getProveedorCollection()) {
                Factura oldIdFactuOfProveedorCollectionProveedor = proveedorCollectionProveedor.getIdFactu();
                proveedorCollectionProveedor.setIdFactu(factura);
                proveedorCollectionProveedor = em.merge(proveedorCollectionProveedor);
                if (oldIdFactuOfProveedorCollectionProveedor != null) {
                    oldIdFactuOfProveedorCollectionProveedor.getProveedorCollection().remove(proveedorCollectionProveedor);
                    oldIdFactuOfProveedorCollectionProveedor = em.merge(oldIdFactuOfProveedorCollectionProveedor);
                }
            }
            for (Detalle detalleCollectionDetalle : factura.getDetalleCollection()) {
                Factura oldIdFactuOfDetalleCollectionDetalle = detalleCollectionDetalle.getIdFactu();
                detalleCollectionDetalle.setIdFactu(factura);
                detalleCollectionDetalle = em.merge(detalleCollectionDetalle);
                if (oldIdFactuOfDetalleCollectionDetalle != null) {
                    oldIdFactuOfDetalleCollectionDetalle.getDetalleCollection().remove(detalleCollectionDetalle);
                    oldIdFactuOfDetalleCollectionDetalle = em.merge(oldIdFactuOfDetalleCollectionDetalle);
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
            Factura persistentFactura = em.find(Factura.class, factura.getIdFactu());
            RubroFacruea idRubroFacruraOld = persistentFactura.getIdRubroFacrura();
            RubroFacruea idRubroFacruraNew = factura.getIdRubroFacrura();
            Collection<Rubro> rubroCollectionOld = persistentFactura.getRubroCollection();
            Collection<Rubro> rubroCollectionNew = factura.getRubroCollection();
            Collection<Proveedor> proveedorCollectionOld = persistentFactura.getProveedorCollection();
            Collection<Proveedor> proveedorCollectionNew = factura.getProveedorCollection();
            Collection<Detalle> detalleCollectionOld = persistentFactura.getDetalleCollection();
            Collection<Detalle> detalleCollectionNew = factura.getDetalleCollection();
            if (idRubroFacruraNew != null) {
                idRubroFacruraNew = em.getReference(idRubroFacruraNew.getClass(), idRubroFacruraNew.getIdRubroFacrura());
                factura.setIdRubroFacrura(idRubroFacruraNew);
            }
            Collection<Rubro> attachedRubroCollectionNew = new ArrayList<Rubro>();
            for (Rubro rubroCollectionNewRubroToAttach : rubroCollectionNew) {
                rubroCollectionNewRubroToAttach = em.getReference(rubroCollectionNewRubroToAttach.getClass(), rubroCollectionNewRubroToAttach.getIdRubro());
                attachedRubroCollectionNew.add(rubroCollectionNewRubroToAttach);
            }
            rubroCollectionNew = attachedRubroCollectionNew;
            factura.setRubroCollection(rubroCollectionNew);
            Collection<Proveedor> attachedProveedorCollectionNew = new ArrayList<Proveedor>();
            for (Proveedor proveedorCollectionNewProveedorToAttach : proveedorCollectionNew) {
                proveedorCollectionNewProveedorToAttach = em.getReference(proveedorCollectionNewProveedorToAttach.getClass(), proveedorCollectionNewProveedorToAttach.getIdProveedor());
                attachedProveedorCollectionNew.add(proveedorCollectionNewProveedorToAttach);
            }
            proveedorCollectionNew = attachedProveedorCollectionNew;
            factura.setProveedorCollection(proveedorCollectionNew);
            Collection<Detalle> attachedDetalleCollectionNew = new ArrayList<Detalle>();
            for (Detalle detalleCollectionNewDetalleToAttach : detalleCollectionNew) {
                detalleCollectionNewDetalleToAttach = em.getReference(detalleCollectionNewDetalleToAttach.getClass(), detalleCollectionNewDetalleToAttach.getIdDetalle());
                attachedDetalleCollectionNew.add(detalleCollectionNewDetalleToAttach);
            }
            detalleCollectionNew = attachedDetalleCollectionNew;
            factura.setDetalleCollection(detalleCollectionNew);
            factura = em.merge(factura);
            if (idRubroFacruraOld != null && !idRubroFacruraOld.equals(idRubroFacruraNew)) {
                idRubroFacruraOld.getFacturaCollection().remove(factura);
                idRubroFacruraOld = em.merge(idRubroFacruraOld);
            }
            if (idRubroFacruraNew != null && !idRubroFacruraNew.equals(idRubroFacruraOld)) {
                idRubroFacruraNew.getFacturaCollection().add(factura);
                idRubroFacruraNew = em.merge(idRubroFacruraNew);
            }
            for (Rubro rubroCollectionOldRubro : rubroCollectionOld) {
                if (!rubroCollectionNew.contains(rubroCollectionOldRubro)) {
                    rubroCollectionOldRubro.getFacturaCollection().remove(factura);
                    rubroCollectionOldRubro = em.merge(rubroCollectionOldRubro);
                }
            }
            for (Rubro rubroCollectionNewRubro : rubroCollectionNew) {
                if (!rubroCollectionOld.contains(rubroCollectionNewRubro)) {
                    rubroCollectionNewRubro.getFacturaCollection().add(factura);
                    rubroCollectionNewRubro = em.merge(rubroCollectionNewRubro);
                }
            }
            for (Proveedor proveedorCollectionOldProveedor : proveedorCollectionOld) {
                if (!proveedorCollectionNew.contains(proveedorCollectionOldProveedor)) {
                    proveedorCollectionOldProveedor.setIdFactu(null);
                    proveedorCollectionOldProveedor = em.merge(proveedorCollectionOldProveedor);
                }
            }
            for (Proveedor proveedorCollectionNewProveedor : proveedorCollectionNew) {
                if (!proveedorCollectionOld.contains(proveedorCollectionNewProveedor)) {
                    Factura oldIdFactuOfProveedorCollectionNewProveedor = proveedorCollectionNewProveedor.getIdFactu();
                    proveedorCollectionNewProveedor.setIdFactu(factura);
                    proveedorCollectionNewProveedor = em.merge(proveedorCollectionNewProveedor);
                    if (oldIdFactuOfProveedorCollectionNewProveedor != null && !oldIdFactuOfProveedorCollectionNewProveedor.equals(factura)) {
                        oldIdFactuOfProveedorCollectionNewProveedor.getProveedorCollection().remove(proveedorCollectionNewProveedor);
                        oldIdFactuOfProveedorCollectionNewProveedor = em.merge(oldIdFactuOfProveedorCollectionNewProveedor);
                    }
                }
            }
            for (Detalle detalleCollectionOldDetalle : detalleCollectionOld) {
                if (!detalleCollectionNew.contains(detalleCollectionOldDetalle)) {
                    detalleCollectionOldDetalle.setIdFactu(null);
                    detalleCollectionOldDetalle = em.merge(detalleCollectionOldDetalle);
                }
            }
            for (Detalle detalleCollectionNewDetalle : detalleCollectionNew) {
                if (!detalleCollectionOld.contains(detalleCollectionNewDetalle)) {
                    Factura oldIdFactuOfDetalleCollectionNewDetalle = detalleCollectionNewDetalle.getIdFactu();
                    detalleCollectionNewDetalle.setIdFactu(factura);
                    detalleCollectionNewDetalle = em.merge(detalleCollectionNewDetalle);
                    if (oldIdFactuOfDetalleCollectionNewDetalle != null && !oldIdFactuOfDetalleCollectionNewDetalle.equals(factura)) {
                        oldIdFactuOfDetalleCollectionNewDetalle.getDetalleCollection().remove(detalleCollectionNewDetalle);
                        oldIdFactuOfDetalleCollectionNewDetalle = em.merge(oldIdFactuOfDetalleCollectionNewDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getIdFactu();
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
                factura.getIdFactu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            RubroFacruea idRubroFacrura = factura.getIdRubroFacrura();
            if (idRubroFacrura != null) {
                idRubroFacrura.getFacturaCollection().remove(factura);
                idRubroFacrura = em.merge(idRubroFacrura);
            }
            Collection<Rubro> rubroCollection = factura.getRubroCollection();
            for (Rubro rubroCollectionRubro : rubroCollection) {
                rubroCollectionRubro.getFacturaCollection().remove(factura);
                rubroCollectionRubro = em.merge(rubroCollectionRubro);
            }
            Collection<Proveedor> proveedorCollection = factura.getProveedorCollection();
            for (Proveedor proveedorCollectionProveedor : proveedorCollection) {
                proveedorCollectionProveedor.setIdFactu(null);
                proveedorCollectionProveedor = em.merge(proveedorCollectionProveedor);
            }
            Collection<Detalle> detalleCollection = factura.getDetalleCollection();
            for (Detalle detalleCollectionDetalle : detalleCollection) {
                detalleCollectionDetalle.setIdFactu(null);
                detalleCollectionDetalle = em.merge(detalleCollectionDetalle);
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
