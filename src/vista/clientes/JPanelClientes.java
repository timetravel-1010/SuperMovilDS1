/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.clientes;

import controlador.ConnectionDB;
import javax.swing.JPanel;
import vista.menuPrincipal;

/**
 *
 * @author Arman
 */
public class JPanelClientes extends javax.swing.JPanel {

    /**
     * Creates new form JPanelClientes
     */
    private JPanel panelActual;
    private menuPrincipal menup;
    private ConnectionDB conexion;
    private JPanelAdminClientes panelTablaClientes;
    
    public JPanelClientes(menuPrincipal menu) { //Metodo constructor de la vista de clientes, recibe el menú principal
        initComponents();
        this.setBackground(new java.awt.Color(218, 234, 255));
        this.conexion = new ConnectionDB();
        this.panelActual = new JPanel();
        this.panelActual.setBackground(new java.awt.Color(218, 234, 255));
        this.menup = menu;
        this.panelTablaClientes = new JPanelAdminClientes(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonRegistrarCliente = new javax.swing.JButton();
        jButtonAdministrarClientes = new javax.swing.JButton();
        jButtonAtras = new javax.swing.JButton();

        setBackground(new java.awt.Color(218, 234, 255));
        setMinimumSize(new java.awt.Dimension(1110, 655));
        setPreferredSize(new java.awt.Dimension(1110, 655));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonRegistrarCliente.setBackground(new java.awt.Color(149, 193, 255));
        jButtonRegistrarCliente.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtonRegistrarCliente.setForeground(new java.awt.Color(0, 0, 0));
        jButtonRegistrarCliente.setText("Registrar Cliente");
        jButtonRegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarClienteActionPerformed(evt);
            }
        });
        add(jButtonRegistrarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 200, 30));

        jButtonAdministrarClientes.setBackground(new java.awt.Color(149, 193, 255));
        jButtonAdministrarClientes.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtonAdministrarClientes.setForeground(new java.awt.Color(0, 0, 0));
        jButtonAdministrarClientes.setText("Administrar Clientes");
        jButtonAdministrarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdministrarClientesActionPerformed(evt);
            }
        });
        add(jButtonAdministrarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 200, 30));

        jButtonAtras.setBackground(new java.awt.Color(149, 193, 255));
        jButtonAtras.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtonAtras.setForeground(new java.awt.Color(0, 0, 0));
        jButtonAtras.setText("Atras");
        jButtonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtrasActionPerformed(evt);
            }
        });
        add(jButtonAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 30, 100, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarClienteActionPerformed
        // TODO add your handling code here:
        this.eliminarPanel();
        this.pintarPanel(new JPanelRegistrarCliente(this));
        menup.refrescarGUI();
        this.enableButtons(false);
    }//GEN-LAST:event_jButtonRegistrarClienteActionPerformed

    private void jButtonAdministrarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdministrarClientesActionPerformed
        // TODO add your handling code here:
        this.eliminarPanel();
        // Se debe mostrar la tabla de clientes
        this.pintarPanel(panelTablaClientes);
        menup.refrescarGUI();
        this.enableButtons(false);
    }//GEN-LAST:event_jButtonAdministrarClientesActionPerformed

    private void jButtonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtrasActionPerformed
        this.menup.buttonPagos.setEnabled(true);
        this.menup.buttonClientes.setEnabled(true);
        this.menup.buttonFacturacion.setEnabled(true);
        this.menup.eliminarPanelActual();
        this.menup.refrescarGUI();
    }//GEN-LAST:event_jButtonAtrasActionPerformed

    public void pintarPanel(JPanel panel){//Metodo que recibe como parametro un panel y lo pinta en el espacio disponible
        panelActual=panel;
        this.add(panelActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1110, 570));
        this.revalidate();
        this.repaint();
    }

    
    public void pintarPanel(JPanel panel, int x, int y){//Sobrecarga que recibe coordenadas para centrar el panel
        panelActual=panel;
        this.add(panelActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 1110, 570));
        this.revalidate();
        this.repaint();
    }
    
    public void eliminarPanel(){//Metodo que elimina el panel pintado actualmente
        this.remove(panelActual);
        this.revalidate();
        this.repaint();
    }
    
    public void enableButtons (Boolean b){//Metodo que recibe un Boolean, si es true, se habilitan los botones, si es false, se inhabilitan
        jButtonRegistrarCliente.setEnabled(b);
        jButtonAdministrarClientes.setEnabled(b);
        jButtonAtras.setEnabled(b);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdministrarClientes;
    private javax.swing.JButton jButtonAtras;
    private javax.swing.JButton jButtonRegistrarCliente;
    // End of variables declaration//GEN-END:variables

}
