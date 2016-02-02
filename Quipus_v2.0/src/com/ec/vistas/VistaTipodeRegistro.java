/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.vistas;

/**
 *
 * @author Toshiba
 */
import com.ec.utilitarios.UsuarioLog;
import java.awt.Dimension;
import static com.ec.vistas.Principal.desktopPane;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
public class VistaTipodeRegistro extends javax.swing.JInternalFrame {

    UsuarioLog usr;
    public void setUsr(UsuarioLog usr)
    {
        this.usr=usr;
    }
    /**
     * Creates new form VistoTipodeRegistro
     */
    
    public VistaTipodeRegistro() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnIngreso = new javax.swing.JButton();
        btnEgreso = new javax.swing.JButton();

        jLabel1.setText("Seleccione el tipo de Registro:");

        btnIngreso.setText("Ingreso");
        btnIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresoActionPerformed(evt);
            }
        });

        btnEgreso.setText("Egreso");
        btnEgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEgresoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEgreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIngreso)
                .addGap(18, 18, 18)
                .addComponent(btnEgreso)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void btnIngresoActionPerformed(java.awt.event.ActionEvent evt) {                                           
        ListaIngresos ObjListaIngreso = new ListaIngresos(this);
        desktopPane.add(ObjListaIngreso);
        
        //ObjListaIngreso.show();
        Dimension desktopSize = desktopPane.getSize();
        Dimension FrameSize = ObjListaIngreso.getSize();
        ObjListaIngreso.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        ObjListaIngreso.show();
        this.setVisible(false);
// TODO add your handling code here:
    }                                          

    private void btnEgresoActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        RegistrarEgreso ObjTipoEgreso = new RegistrarEgreso();
        desktopPane.add(ObjTipoEgreso);
        Dimension desktopSize = desktopPane.getSize();
        Dimension FrameSize = ObjTipoEgreso.getSize();
        ObjTipoEgreso.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
//        this.setVisible(false);
        ObjTipoEgreso.show();
        
        
        
    }                                         



    // Variables declaration - do not modify                     
    private javax.swing.JButton btnEgreso;
    private javax.swing.JButton btnIngreso;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration                   
}
