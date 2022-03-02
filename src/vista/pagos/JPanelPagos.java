/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.pagos;

import javax.swing.JPanel;
import vista.clientes.JPanelTablaPlanes;
import vista.menuPrincipal;
import controlador.ConnectionDB;

/**
 *
 * @author Arman
 */
public class JPanelPagos extends javax.swing.JPanel {

    /**
     * Creates new form JPanelPAgos
     */
    private JPanel panelActual;
    private menuPrincipal menup;
    private ConnectionDB db;
    
    public JPanelPagos(menuPrincipal menu) {
        initComponents();
        panelActual=new JPanel();
        this.add(panelActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1110, 570));
        menup=menu;
        db = new ConnectionDB ();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonPagoIndividual = new javax.swing.JButton();
        jButtonPagosBancos = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonPagoIndividual.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jButtonPagoIndividual.setText("Registrar pago individual");
        jButtonPagoIndividual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPagoIndividualActionPerformed(evt);
            }
        });
        add(jButtonPagoIndividual, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 210, 30));

        jButtonPagosBancos.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jButtonPagosBancos.setText("Cargar pagos bancarios");
        jButtonPagosBancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPagosBancosActionPerformed(evt);
            }
        });
        add(jButtonPagosBancos, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 210, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPagoIndividualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagoIndividualActionPerformed
        // TODO add your handling code here:
        this.eliminarPanel();
        //this.pintarPanel(new JPanelTablaPlanes(this)); // Cambiar a JPanelDatosUsuarios
        this.pintarPanel(new JPanelRegistrarPagos(this));
        //this.pintarPanel(new JPanelDatosPlanes(this)); //borrar

        //tabla.agregarTodos();
        
        menup.refrescarGUI();
    }//GEN-LAST:event_jButtonPagoIndividualActionPerformed

    private void jButtonPagosBancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagosBancosActionPerformed
        // TODO add your handling code here:
        db.updateValorPagoCuentaM();
        db.updateUltimoPagoCuentaM();
       /* this.eliminarPanel();
        menup.refrescarGUI();*/
       

    }//GEN-LAST:event_jButtonPagosBancosActionPerformed

    public void eliminarPanel(){
        this.remove(panelActual);
        this.revalidate();
        this.repaint();
    }
        
    public void refrescarGUI(){
        menup.refrescarGUI();
    }
    
    public void pintarPanel(JPanel panel){
        panelActual=panel;
        this.add(panelActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1110, 570));
        this.revalidate();
        this.repaint();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPagoIndividual;
    private javax.swing.JButton jButtonPagosBancos;
    // End of variables declaration//GEN-END:variables
}
