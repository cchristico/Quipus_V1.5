/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.vistas;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Toshiba
 */
public class Dashboards extends javax.swing.JPanel {

    /**
     * Creates new form Dashboards
     */
    public Dashboards() {
        initComponents();
        this.RAlimentación.setVisible(false);
        this.REducación.setVisible(false);
        this.RSalud.setVisible(false);
        this.RVestimenta.setVisible(false);
        this.RVivienda.setVisible(false);
        
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Vivienda = new javax.swing.JRadioButton();
        Educación = new javax.swing.JRadioButton();
        Alimentación = new javax.swing.JRadioButton();
        Vestimenta = new javax.swing.JRadioButton();
        Salud = new javax.swing.JRadioButton();
        Rubros = new javax.swing.JLayeredPane();
        REducación = new javax.swing.JPanel();
        RAlimentación = new javax.swing.JPanel();
        RVestimenta = new javax.swing.JPanel();
        RSalud = new javax.swing.JPanel();
        RVivienda = new javax.swing.JPanel();
        btnGraficar = new javax.swing.JButton();

        Vivienda.setText("Vivienda");
        Vivienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViviendaActionPerformed(evt);
            }
        });

        Educación.setText("Educación");
        Educación.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EducaciónActionPerformed(evt);
            }
        });

        Alimentación.setText("Alimentación");
        Alimentación.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlimentaciónActionPerformed(evt);
            }
        });

        Vestimenta.setText("Vestimenta");
        Vestimenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VestimentaActionPerformed(evt);
            }
        });

        Salud.setText("Salud");
        Salud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaludActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout REducaciónLayout = new javax.swing.GroupLayout(REducación);
        REducación.setLayout(REducaciónLayout);
        REducaciónLayout.setHorizontalGroup(
            REducaciónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );
        REducaciónLayout.setVerticalGroup(
            REducaciónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout RAlimentaciónLayout = new javax.swing.GroupLayout(RAlimentación);
        RAlimentación.setLayout(RAlimentaciónLayout);
        RAlimentaciónLayout.setHorizontalGroup(
            RAlimentaciónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );
        RAlimentaciónLayout.setVerticalGroup(
            RAlimentaciónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout RVestimentaLayout = new javax.swing.GroupLayout(RVestimenta);
        RVestimenta.setLayout(RVestimentaLayout);
        RVestimentaLayout.setHorizontalGroup(
            RVestimentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );
        RVestimentaLayout.setVerticalGroup(
            RVestimentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout RSaludLayout = new javax.swing.GroupLayout(RSalud);
        RSalud.setLayout(RSaludLayout);
        RSaludLayout.setHorizontalGroup(
            RSaludLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );
        RSaludLayout.setVerticalGroup(
            RSaludLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout RViviendaLayout = new javax.swing.GroupLayout(RVivienda);
        RVivienda.setLayout(RViviendaLayout);
        RViviendaLayout.setHorizontalGroup(
            RViviendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );
        RViviendaLayout.setVerticalGroup(
            RViviendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        Rubros.setLayer(REducación, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Rubros.setLayer(RAlimentación, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Rubros.setLayer(RVestimenta, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Rubros.setLayer(RSalud, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Rubros.setLayer(RVivienda, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout RubrosLayout = new javax.swing.GroupLayout(Rubros);
        Rubros.setLayout(RubrosLayout);
        RubrosLayout.setHorizontalGroup(
            RubrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(RubrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(RubrosLayout.createSequentialGroup()
                    .addComponent(REducación, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 31, Short.MAX_VALUE)))
            .addGroup(RubrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(RubrosLayout.createSequentialGroup()
                    .addComponent(RAlimentación, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 46, Short.MAX_VALUE)))
            .addGroup(RubrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(RubrosLayout.createSequentialGroup()
                    .addComponent(RVestimenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 40, Short.MAX_VALUE)))
            .addGroup(RubrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RubrosLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(41, 41, 41)))
            .addGroup(RubrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(RubrosLayout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(RVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(42, Short.MAX_VALUE)))
        );
        RubrosLayout.setVerticalGroup(
            RubrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
            .addGroup(RubrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(REducación, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RubrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(RAlimentación, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RubrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(RVestimenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RubrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(RSalud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RubrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(RubrosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RVivienda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        btnGraficar.setText("Graficar");
        btnGraficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(btnGraficar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(Vivienda)
                        .addGap(18, 18, 18)
                        .addComponent(Educación)
                        .addGap(29, 29, 29)
                        .addComponent(Alimentación)
                        .addGap(30, 30, 30)
                        .addComponent(Vestimenta)
                        .addGap(45, 45, 45)
                        .addComponent(Salud))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(Rubros, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Vivienda)
                    .addComponent(Educación)
                    .addComponent(Alimentación)
                    .addComponent(Vestimenta)
                    .addComponent(Salud))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Rubros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(btnGraficar)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SaludActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaludActionPerformed
        RSalud.setVisible(true);
        Rubros.setLayer(RSalud, 0,0);
        Vivienda.setSelected(false);
        Vestimenta.setSelected(false);
        Educación.setSelected(false);
        Alimentación.setSelected(false);
    }//GEN-LAST:event_SaludActionPerformed

    private void ViviendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViviendaActionPerformed
        RVivienda.setVisible(true);
        Rubros.setLayer(RVivienda, 0,0);
        Salud.setSelected(false);
        Vestimenta.setSelected(false);
        Educación.setSelected(false);
        Alimentación.setSelected(false);
    }//GEN-LAST:event_ViviendaActionPerformed

    private void EducaciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EducaciónActionPerformed
        REducación.setVisible(true);
        Rubros.setLayer(REducación, 0,0);
        Vivienda.setSelected(false);
        Vestimenta.setSelected(false);
        Salud.setSelected(false);
        Alimentación.setSelected(false);
    }//GEN-LAST:event_EducaciónActionPerformed

    private void AlimentaciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlimentaciónActionPerformed
        RAlimentación.setVisible(true);
        Rubros.setLayer(RAlimentación, 0,0);
        Vivienda.setSelected(false);
        Vestimenta.setSelected(false);
        Educación.setSelected(false);
        Salud.setSelected(false);
    }//GEN-LAST:event_AlimentaciónActionPerformed

    private void VestimentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VestimentaActionPerformed
        RVestimenta.setVisible(true);
        Rubros.setLayer(RVestimenta, 0,0);
        Vivienda.setSelected(false);
        Salud.setSelected(false);
        Educación.setSelected(false);
        Alimentación.setSelected(false);
    }//GEN-LAST:event_VestimentaActionPerformed

    private void btnGraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarActionPerformed
        ChartPanel panel;
        JFreeChart chart = null;
        if(Vivienda.isSelected()){
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Utilizado",50);
        data.setValue("Disponible",50);
       
        //Creamos un Chart
        chart = ChartFactory.createPieChart(
                           "Vivienda", //Títrulo del gráfico
                           data,
                           true,//Leyenda
                           true,//ToolTips
                           true);
        } else {
            if (Educación.isSelected()){
                DefaultPieDataset data = new DefaultPieDataset();
                data.setValue("Utilizado",43.2);
                data.setValue("Disponible",56.8);
                ;
        //Creamos un Chart
        chart = ChartFactory.createPieChart(
                           "Educación", //Títrulo del gráfico
                           data,
                           true,//Leyenda
                           true,//ToolTips
                           true);
            } else {
                if(Vestimenta.isSelected()){
                    DefaultPieDataset data = new DefaultPieDataset();
                data.setValue("Utilizado",43.2);
                data.setValue("Disponible",56.8);
                ;
                //Creamos un Chart
                chart = ChartFactory.createPieChart(
                           "Vestimenta", //Títrulo del gráfico
                           data,
                           true,//Leyenda
                           true,//ToolTips
                           true);
                } else {
                    if(Alimentación.isSelected()){
                        DefaultPieDataset data = new DefaultPieDataset();
                data.setValue("Utilizado",43.2);
                data.setValue("Disponible",56.8);
                ;
        //Creamos un Chart
        chart = ChartFactory.createPieChart(
                           "Alimentación", //Títrulo del gráfico
                           data,
                           true,//Leyenda
                           true,//ToolTips
                           true);
                    } else {
                        if(Salud.isSelected()){
                            DefaultPieDataset data = new DefaultPieDataset();
                data.setValue("Utilizado",43.2);
                data.setValue("Disponible",56.8);
                ;
        //Creamos un Chart
        chart = ChartFactory.createPieChart(
                           "Salud", //Títrulo del gráfico
                           data,
                           true,//Leyenda
                           true,//ToolTips
                           true);
                        }
                    }
                }
            }
        }
        panel = new ChartPanel(chart);
        panel.setBounds(5,10,500,480);
        
        if(Vivienda.isSelected()){
            RVivienda.add(panel);
            RVivienda.repaint();
        }else{
            if (Educación.isSelected()){
                REducación.add(panel);
                REducación.repaint();
            } else {
                if (Vestimenta.isSelected()){
                    RVestimenta.add(panel);
                    RVestimenta.repaint();
                } else{
                    if (Alimentación.isSelected()){
                        RAlimentación.add(panel);
                        RAlimentación.repaint();
                    } else {
                        if (Salud.isSelected()){
                            RSalud.add(panel);
                            RSalud.repaint();
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnGraficarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Alimentación;
    private javax.swing.JRadioButton Educación;
    private javax.swing.JPanel RAlimentación;
    private javax.swing.JPanel REducación;
    private javax.swing.JPanel RSalud;
    private javax.swing.JPanel RVestimenta;
    private javax.swing.JPanel RVivienda;
    private javax.swing.JLayeredPane Rubros;
    private javax.swing.JRadioButton Salud;
    private javax.swing.JRadioButton Vestimenta;
    private javax.swing.JRadioButton Vivienda;
    private javax.swing.JButton btnGraficar;
    // End of variables declaration//GEN-END:variables
}
