package com.ec.controller;

import com.ec.entidades.RuboFactura;
import com.ec.controller.util.JsfUtil;
import com.ec.controller.util.JsfUtil.PersistAction;
import com.ec.sesion.RuboFacturaFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("ruboFacturaController")
@SessionScoped
public class RuboFacturaController implements Serializable {

    @EJB
    private com.ec.sesion.RuboFacturaFacade ejbFacade;
    private List<RuboFactura> items = null;
    private RuboFactura selected;

    public RuboFacturaController() {
    }

    public RuboFactura getSelected() {
        return selected;
    }

    public void setSelected(RuboFactura selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setRuboFacturaPK(new com.ec.entidades.RuboFacturaPK());
    }

    private RuboFacturaFacade getFacade() {
        return ejbFacade;
    }

    public RuboFactura prepareCreate() {
        selected = new RuboFactura();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RuboFacturaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RuboFacturaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RuboFacturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<RuboFactura> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public RuboFactura getRuboFactura(com.ec.entidades.RuboFacturaPK id) {
        return getFacade().find(id);
    }

    public List<RuboFactura> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<RuboFactura> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = RuboFactura.class)
    public static class RuboFacturaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RuboFacturaController controller = (RuboFacturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ruboFacturaController");
            return controller.getRuboFactura(getKey(value));
        }

        com.ec.entidades.RuboFacturaPK getKey(String value) {
            com.ec.entidades.RuboFacturaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.ec.entidades.RuboFacturaPK();
            key.setIdFactu(Integer.parseInt(values[0]));
            key.setIdRubro(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.ec.entidades.RuboFacturaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdFactu());
            sb.append(SEPARATOR);
            sb.append(value.getIdRubro());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof RuboFactura) {
                RuboFactura o = (RuboFactura) object;
                return getStringKey(o.getRuboFacturaPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RuboFactura.class.getName()});
                return null;
            }
        }

    }

}
