/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.vistas;


import static com.ec.vistas.Principal.desktopPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author gato
 */
public class ListaProveedor extends javax.swing.JInternalFrame {
//componentes para diseño

    static int openFrameCount = 0;
    static final int xOffset = 100, yOffset = 30;
    DefaultTableModel model = null;


    /**
     * Creates new form ListaUsuriosView
     */
    public ListaProveedor() {
        super("Listado de Proveedor #" + (++openFrameCount),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable

        //Set the window's location.
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
        initComponents();
        txtRuc.setText("");
        String col[] = {"PK", "RUC", "NICK_NAME", "NOMBRES", "APELLIDO", "CONTRASEÑA", "SALARIO", "CORREO", "PREGUNTA"};
        String data[][] = {{"", "", "", "", "", "", "", "", ""}};
        model = new DefaultTableModel(data, col);
        model.setRowCount(0);

        dgvUsuario.setModel(model);

        //TableRowSorter<TableModel> tablaOrdena = new TableRowSorter<TableModel>(model);
        dgvUsuario.setRowSorter(new TableRowSorter<TableModel>(model));
        consultar();

    }

    private void consultar() {
        int i = 0;
        model.setRowCount(0);
        /*
         PersonasJpaController control = new PersonasJpaController(Persistence.createEntityManagerFactory("BaseDeDatosPU"));
         listaPersonas = control.findPersonasEntities();
         for (Personas p : listaPersonas) {
         model.insertRow(i++,new Object[]{
         p.getPersonaId().toString(),
         p.getNombre(),
         p.getApellido(),
         p.getCedula()
         });
         }
        
         */
       

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dgvUsuario = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setTitle("Lista de proveedores");
        setVisible(true);

        dgvUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        dgvUsuario.setToolTipText("Haga doble clic en un registro para modificar ");
        dgvUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvUsuarioMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dgvUsuarioMousePressed(evt);
            }
        });
        dgvUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dgvUsuarioKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(dgvUsuario);

        jLabel1.setText("RUC");

        txtRuc.setText(" ");
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucKeyPressed(evt);
            }
        });

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jButton3)
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyPressed
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_txtRucKeyPressed

    private void dgvUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dgvUsuarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dgvUsuarioKeyPressed

    private void dgvUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvUsuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dgvUsuarioMouseClicked

    private void dgvUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvUsuarioMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() > 1) {
//            UsuarioEditView vistaUsuario = new UsuarioEditView(new Usuario("1718264839", "e", "e", "33", "55", Double.NaN, "rrr", "rr", "tt"));
//            vistaUsuario.show();
           

           
             consultar();

        } else {
        }
    }//GEN-LAST:event_dgvUsuarioMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        RegistrarProveedor RegProv = new RegistrarProveedor();
        desktopPane.add(RegProv);
        RegProv.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showConfirmDialog(null, "Eliminacion de Proveedor \nNombre Proveedor","Desea eliminar",JOptionPane.YES_NO_OPTION);
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dgvUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtRuc;
    // End of variables declaration//GEN-END:variables
}
