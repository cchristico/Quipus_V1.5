/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlladores;

import com.ec.controlladores.exceptions.IllegalOrphanException;
import com.ec.controlladores.exceptions.NonexistentEntityException;
import com.ec.entidades.Factura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ec.entidades.Facturaingreso;
import com.ec.entidades.Usuario;
import com.ec.entidades.Facturaegreso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author david
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturaingreso facturaingreso = factura.getFacturaingreso();
            if (facturaingreso != null) {
                facturaingreso = em.getReference(facturaingreso.getClass(), facturaingreso.getIdfactura());
                factura.setFacturaingreso(facturaingreso);
            }
            Usuario idUsu = factura.getIdUsu();
            if (idUsu != null) {
                idUsu = em.getReference(idUsu.getClass(), idUsu.getIdUsu());
                factura.setIdUsu(idUsu);
            }
            Facturaegreso facturaegreso = factura.getFacturaegreso();
            if (facturaegreso != null) {
                facturaegreso = em.getReference(facturaegreso.getClass(), facturaegreso.getIdfactura());
                factura.setFacturaegreso(facturaegreso);
            }
            em.persist(factura);
            if (facturaingreso != null) {
                Factura oldFacturaOfFacturaingreso = facturaingreso.getFactura();
                if (oldFacturaOfFacturaingreso != null) {
                    oldFacturaOfFacturaingreso.setFacturaingreso(null);
                    oldFacturaOfFacturaingreso = em.merge(oldFacturaOfFacturaingreso);
                }
                facturaingreso.setFactura(factura);
                facturaingreso = em.merge(facturaingreso);
            }
            if (idUsu != null) {
                idUsu.getFacturaCollection().add(factura);
                idUsu = em.merge(idUsu);
            }
            if (facturaegreso != null) {
                Factura oldFacturaOfFacturaegreso = facturaegreso.getFactura();
                if (oldFacturaOfFacturaegreso != null) {
                    oldFacturaOfFacturaegreso.setFacturaegreso(null);
                    oldFacturaOfFacturaegreso = em.merge(oldFacturaOfFacturaegreso);
                }
                facturaegreso.setFactura(factura);
                facturaegreso = em.merge(facturaegreso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdfactura());
            Facturaingreso facturaingresoOld = persistentFactura.getFacturaingreso();
            Facturaingreso facturaingresoNew = factura.getFacturaingreso();
            Usuario idUsuOld = persistentFactura.getIdUsu();
            Usuario idUsuNew = factura.getIdUsu();
            Facturaegreso facturaegresoOld = persistentFactura.getFacturaegreso();
            Facturaegreso facturaegresoNew = factura.getFacturaegreso();
            List<String> illegalOrphanMessages = null;
            if (facturaingresoOld != null && !facturaingresoOld.equals(facturaingresoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Facturaingreso " + facturaingresoOld + " since its factura field is not nullable.");
            }
            if (facturaegresoOld != null && !facturaegresoOld.equals(facturaegresoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Facturaegreso " + facturaegresoOld + " since its factura field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (facturaingresoNew != null) {
                facturaingresoNew = em.getReference(facturaingresoNew.getClass(), facturaingresoNew.getIdfactura());
                factura.setFacturaingreso(facturaingresoNew);
            }
            if (idUsuNew != null) {
                idUsuNew = em.getReference(idUsuNew.getClass(), idUsuNew.getIdUsu());
                factura.setIdUsu(idUsuNew);
            }
            if (facturaegresoNew != null) {
                facturaegresoNew = em.getReference(facturaegresoNew.getClass(), facturaegresoNew.getIdfactura());
                factura.setFacturaegreso(facturaegresoNew);
            }
            factura = em.merge(factura);
            if (facturaingresoNew != null && !facturaingresoNew.equals(facturaingresoOld)) {
                Factura oldFacturaOfFacturaingreso = facturaingresoNew.getFactura();
                if (oldFacturaOfFacturaingreso != null) {
                    oldFacturaOfFacturaingreso.setFacturaingreso(null);
                    oldFacturaOfFacturaingreso = em.merge(oldFacturaOfFacturaingreso);
                }
                facturaingresoNew.setFactura(factura);
                facturaingresoNew = em.merge(facturaingresoNew);
            }
            if (idUsuOld != null && !idUsuOld.equals(idUsuNew)) {
                idUsuOld.getFacturaCollection().remove(factura);
                idUsuOld = em.merge(idUsuOld);
            }
            if (idUsuNew != null && !idUsuNew.equals(idUsuOld)) {
                idUsuNew.getFacturaCollection().add(factura);
                idUsuNew = em.merge(idUsuNew);
            }
            if (facturaegresoNew != null && !facturaegresoNew.equals(facturaegresoOld)) {
                Factura oldFacturaOfFacturaegreso = facturaegresoNew.getFactura();
                if (oldFacturaOfFacturaegreso != null) {
                    oldFacturaOfFacturaegreso.setFacturaegreso(null);
                    oldFacturaOfFacturaegreso = em.merge(oldFacturaOfFacturaegreso);
                }
                facturaegresoNew.setFactura(factura);
                facturaegresoNew = em.merge(facturaegresoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getIdfactura();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdfactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Facturaingreso facturaingresoOrphanCheck = factura.getFacturaingreso();
            if (facturaingresoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the Facturaingreso " + facturaingresoOrphanCheck + " in its facturaingreso field has a non-nullable factura field.");
            }
            Facturaegreso facturaegresoOrphanCheck = factura.getFacturaegreso();
            if (facturaegresoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the Facturaegreso " + facturaegresoOrphanCheck + " in its facturaegreso field has a non-nullable factura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idUsu = factura.getIdUsu();
            if (idUsu != null) {
                idUsu.getFacturaCollection().remove(factura);
                idUsu = em.merge(idUsu);
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
