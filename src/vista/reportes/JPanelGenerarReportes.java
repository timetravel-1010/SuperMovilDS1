/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.reportes;

import controlador.ConnectionDB;
import java.util.ArrayList;
import modelo.Planes;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Arman
 */
public class JPanelGenerarReportes extends javax.swing.JPanel {

    private ChartPanel grafico;
    private JPanelReportes padre;
    private ConnectionDB conexion;
    /**
     * Creates new form JPanelGenerarReportes
     */
    public JPanelGenerarReportes(JPanelReportes papa, ConnectionDB conexion) {
        this.padre = papa;
        this.conexion = conexion;
        initComponents();
        this.grafico = new ChartPanel(null);
        this.grafico.setBackground(new java.awt.Color(218, 234, 255));
        this.pintarGrafico(this.grafico);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jLabelTipoReporte = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jLabelMes = new javax.swing.JLabel();
        jComboBoxMes = new javax.swing.JComboBox<>();
        jButtonEnviar = new javax.swing.JButton();
        jButtonAtras = new javax.swing.JButton();

        setBackground(new java.awt.Color(218, 234, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setBackground(new java.awt.Color(218, 234, 255));
        jLabelTitulo.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTitulo.setText("Generacion de reportes:");
        add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 30));

        jLabelTipoReporte.setBackground(new java.awt.Color(218, 234, 255));
        jLabelTipoReporte.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabelTipoReporte.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTipoReporte.setText("Tipo de reporte:");
        add(jLabelTipoReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 130, 30));

        jComboBoxTipo.setBackground(new java.awt.Color(149, 193, 255));
        jComboBoxTipo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jComboBoxTipo.setForeground(new java.awt.Color(0, 0, 0));
        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pagos recibidos segun tipo de cliente", "Distribucion de planes", "Clientes por ciudad", "Clientes nuevos" }));
        jComboBoxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoActionPerformed(evt);
            }
        });
        add(jComboBoxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 231, 30));

        jLabelMes.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabelMes.setForeground(new java.awt.Color(0, 0, 0));
        jLabelMes.setText("Mes del reporte:");
        add(jLabelMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 110, 30));

        jComboBoxMes.setBackground(new java.awt.Color(149, 193, 255));
        jComboBoxMes.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jComboBoxMes.setForeground(new java.awt.Color(0, 0, 0));
        jComboBoxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBoxMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMesActionPerformed(evt);
            }
        });
        add(jComboBoxMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 110, 30));

        jButtonEnviar.setBackground(new java.awt.Color(149, 193, 255));
        jButtonEnviar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonEnviar.setForeground(new java.awt.Color(0, 0, 0));
        jButtonEnviar.setText("Generar reporte");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarjButtonEnviarActionPerformed(evt);
            }
        });
        add(jButtonEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 150, 30));

        jButtonAtras.setBackground(new java.awt.Color(149, 193, 255));
        jButtonAtras.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonAtras.setForeground(new java.awt.Color(0, 0, 0));
        jButtonAtras.setText("Atras");
        jButtonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtrasActionPerformed(evt);
            }
        });
        add(jButtonAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, 100, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoActionPerformed
        int opcion = this.jComboBoxTipo.getSelectedIndex();
        switch(opcion){
            case 1:
                this.jComboBoxMes.setEnabled(false);
                break;
            case 2:
                this.jComboBoxMes.setEnabled(false);
            break;
            default:
                this.jComboBoxMes.setEnabled(true);
                break;
        }
    }//GEN-LAST:event_jComboBoxTipoActionPerformed

    private void jComboBoxMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMesActionPerformed

    private void jButtonEnviarjButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarjButtonEnviarActionPerformed
        //Dependiendo del tipo de reporte y el mes:
        String mesEs = this.jComboBoxMes.getSelectedItem().toString();
        String mesEn = "null";
        String mesAnt = "null";
        switch(mesEs){ //Se traduce el mes a ingles para poder hacer la consulta
            case "Enero":
                mesEn = "January";
                mesAnt = "2022-01-01";
                break;
            case "Febrero":
                mesEn = "February";
                mesAnt = "2022-02-01";
                break;
            case "Marzo":
                mesEn = "March";
                mesAnt = "2022-03-01";
                break;
            case "Abril":
                mesEn = "April";
                mesAnt = "2022-04-01";
                break;
            case "Mayo":
                mesEn = "May";
                mesAnt = "2022-05-01";
                break;
            case "Junio":
                mesEn = "June";
                mesAnt = "2022-06-01";
                break;
            case "Julio":
                mesEn = "July";
                mesAnt = "2022-07-01";
                break;
            case "Agosto":
                mesEn = "August";
                mesAnt = "2022-08-01";
                break;
            case "Septiembre":
                mesAnt = "2022-09-01";
                mesEn = "September";
                break;
            case "Octubre":
                mesEn = "October";
                mesAnt = "2022-10-01";
                break;
            case "Noviembre":
                mesEn = "November";
                mesAnt = "2022-11-01";
                break;
            case "Diciembre":
                mesEn = "December";
                mesAnt = "2022-12-01";
                break;
        }
        String tipoReporte = this.jComboBoxTipo.getSelectedItem().toString();
        switch(tipoReporte){
            case "Pagos recibidos segun tipo de cliente"://Reporte que muestra los ingresos por tipo de cliente del mes seleccionado
                DefaultCategoryDataset datos = new DefaultCategoryDataset();
                datos.setValue(conexion.consultarTotalPagos(mesEn, "natural"), "Dinero total recibido", "Clientes naturales");
                datos.setValue(conexion.consultarTotalPagos(mesEn, "corporativo"), "Dinero total recibido", "Clientes corporativos");
                JFreeChart graficoBarras = ChartFactory.createBarChart3D("Pagos recibidos segun tipo de cliente en "+mesEs,"Tipos de clientes", "Ingresos",datos, PlotOrientation.VERTICAL, true, true, false);
                ChartPanel panelGrafico = new ChartPanel(graficoBarras);
                panelGrafico.setMouseWheelEnabled(true);
                this.borrarGrafico();
                this.pintarGrafico(panelGrafico);
                break;
            case "Distribucion de planes":
                ArrayList<Planes> planes = conexion.getPlanes();
                DefaultPieDataset pieDataset = new DefaultPieDataset();
                for(int i=0; i<planes.size(); i++){
                    pieDataset.setValue(planes.get(i).getNombre(), conexion.consultarTotalNumerosPlan(i+1));
                }
                JFreeChart chart = ChartFactory.createPieChart3D(
                        "Numero de clientes por plan",
                        pieDataset,
                        true,
                        true,
                        false
                );
                //Mostramos la grafica en pantalla
                ChartPanel graficoTorta = new ChartPanel(chart);
                graficoTorta.setMouseWheelEnabled(true);
                this.borrarGrafico();
                this.pintarGrafico(graficoTorta);
                break;
            case "Clientes por ciudad":
                ArrayList<String> ciudades = conexion.obtenerCiudades();
                DefaultCategoryDataset datos1 = new DefaultCategoryDataset();
                for(int i=0; i<ciudades.size(); i++){
                    datos1.setValue(conexion.consultarTotalClientesCiudad(ciudades.get(i)), "Cantidad de clientes", ciudades.get(i));
                }
                JFreeChart graficoBarras1 = ChartFactory.createBarChart3D("Numero de clientes por ciudad","Ciudades", "Clientes",datos1, PlotOrientation.VERTICAL, true, true, false);
                ChartPanel panelGrafico1 = new ChartPanel(graficoBarras1);
                panelGrafico1.setMouseWheelEnabled(true);
                this.borrarGrafico();
                this.pintarGrafico(panelGrafico1);
                break;
            case "Clientes nuevos":
                DefaultPieDataset pieDataset1 = new DefaultPieDataset();
                pieDataset1.setValue("Clientes nuevos en "+mesEs, conexion.consultarClientesNuevos(mesEn));
                pieDataset1.setValue("Clientes anteriores", conexion.consultarClientesViejos(mesAnt));
                JFreeChart chart1 = ChartFactory.createPieChart3D(
                        "Clientes nuevos en "+ mesEs+" vs Clientes anteriores",
                        pieDataset1,
                        true,
                        true,
                        false
                );
                //Mostramos la grafica en pantalla
                ChartPanel graficoTorta1 = new ChartPanel(chart1);
                graficoTorta1.setMouseWheelEnabled(true);
                this.borrarGrafico();
                this.pintarGrafico(graficoTorta1);
                break;
        }
    }//GEN-LAST:event_jButtonEnviarjButtonEnviarActionPerformed

    private void jButtonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtrasActionPerformed
        // TODO add your handling code here:
        padre.eliminarPanel();
        padre.enableButtons(true);
    }//GEN-LAST:event_jButtonAtrasActionPerformed

    private void pintarGrafico(ChartPanel grafico){
        this.grafico = grafico;
        this.add(this.grafico , new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1110, 528));
        this.validate();
    }
    
    private void borrarGrafico(){
        this.remove(this.grafico);
        this.validate();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtras;
    private javax.swing.JButton jButtonEnviar;
    public javax.swing.JComboBox<String> jComboBoxMes;
    public javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel jLabelMes;
    private javax.swing.JLabel jLabelTipoReporte;
    public javax.swing.JLabel jLabelTitulo;
    // End of variables declaration//GEN-END:variables
}
