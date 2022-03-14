package vista.usuarios;

import javax.swing.JPanel;
import vista.clientes.JPanelDatosPlanes;
import vista.clientes.JPanelTablaPlanes;
import vista.menuPrincipal;
import vista.pagos.JPanelRegistrarPagos;

/**
 *
 * @author Arman
 */
public class JPanelUsuarios extends javax.swing.JPanel {

    /**
     * Creates new form JPanelUsuarios
     */
    private JPanel panelActual;
    private menuPrincipal menup;
    private JPanelTablaPlanes tablaPlanes;
    private JPanelTablaUsuarios tablaUsuarios;
    //private JPanelRegistrarPagos pagos;
    
    public JPanelUsuarios(menuPrincipal menu) {
        initComponents();
        this.setBackground(new java.awt.Color(218, 234, 255));
        panelActual=new JPanel();
        this.panelActual.setBackground(new java.awt.Color(218, 234, 255));
        this.add(panelActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1110, 570));
        menup=menu;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonRegistrarPlan = new javax.swing.JButton();
        jButtonAdmiinistrarPlan = new javax.swing.JButton();
        jButtonRegistrarUsuario = new javax.swing.JButton();
        jButtonAdmiinistrarUsuarios = new javax.swing.JButton();
        jButtonAtras = new javax.swing.JButton();

        setBackground(new java.awt.Color(218, 234, 255));
        setMinimumSize(new java.awt.Dimension(1110, 655));
        setPreferredSize(new java.awt.Dimension(1110, 655));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonRegistrarPlan.setBackground(new java.awt.Color(149, 193, 255));
        jButtonRegistrarPlan.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtonRegistrarPlan.setForeground(new java.awt.Color(0, 0, 0));
        jButtonRegistrarPlan.setText("Registrar Plan");
        jButtonRegistrarPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarPlanActionPerformed(evt);
            }
        });
        add(jButtonRegistrarPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 200, 30));

        jButtonAdmiinistrarPlan.setBackground(new java.awt.Color(149, 193, 255));
        jButtonAdmiinistrarPlan.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtonAdmiinistrarPlan.setForeground(new java.awt.Color(0, 0, 0));
        jButtonAdmiinistrarPlan.setText("Administrar Planes");
        jButtonAdmiinistrarPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdmiinistrarPlanActionPerformed(evt);
            }
        });
        add(jButtonAdmiinistrarPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 200, 30));

        jButtonRegistrarUsuario.setBackground(new java.awt.Color(149, 193, 255));
        jButtonRegistrarUsuario.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtonRegistrarUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jButtonRegistrarUsuario.setText("Registrar Usuario");
        jButtonRegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarUsuarioActionPerformed(evt);
            }
        });
        add(jButtonRegistrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 200, 30));

        jButtonAdmiinistrarUsuarios.setBackground(new java.awt.Color(149, 193, 255));
        jButtonAdmiinistrarUsuarios.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtonAdmiinistrarUsuarios.setForeground(new java.awt.Color(0, 0, 0));
        jButtonAdmiinistrarUsuarios.setText("Administrar Usuarios");
        jButtonAdmiinistrarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdmiinistrarUsuariosActionPerformed(evt);
            }
        });
        add(jButtonAdmiinistrarUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, 30));

        jButtonAtras.setBackground(new java.awt.Color(149, 193, 255));
        jButtonAtras.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtonAtras.setForeground(new java.awt.Color(0, 0, 0));
        jButtonAtras.setText("Atras");
        jButtonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtrasActionPerformed(evt);
            }
        });
        add(jButtonAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 30, 100, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdmiinistrarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdmiinistrarPlanActionPerformed
        // TODO add your handling code here:
        this.eliminarPanel();
        this.pintarPanel(new JPanelTablaPlanes(this),100,100);
        tablaPlanes.agregarTodos();
        menup.refrescarGUI();
        this.enableButtons(false);
        
    }//GEN-LAST:event_jButtonAdmiinistrarPlanActionPerformed

    private void jButtonRegistrarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarPlanActionPerformed
        // TODO add your handling code here:
        this.eliminarPanel();
        this.pintarPanel(new JPanelDatosPlanes(this), 130, 100); // Cambiar a JPanelDatosUsuarios        
        menup.refrescarGUI();
        this.enableButtons(false);
    }//GEN-LAST:event_jButtonRegistrarPlanActionPerformed

    private void jButtonRegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarUsuarioActionPerformed
        // TODO add your handling code here:
        this.eliminarPanel();
        this.pintarPanel(new JPanelDatosUsuario(this), 250, 130);
        menup.refrescarGUI();
        this.enableButtons(false);
         
    }//GEN-LAST:event_jButtonRegistrarUsuarioActionPerformed

    private void jButtonAdmiinistrarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdmiinistrarUsuariosActionPerformed
        // TODO add your handling code here:
        this.eliminarPanel();
        this.pintarPanel(new JPanelAdministrarUsuarios(this));
        //tablaUsuarios.agregarTodos();
        menup.refrescarGUI();
        this.enableButtons(false);
    }//GEN-LAST:event_jButtonAdmiinistrarUsuariosActionPerformed

    private void jButtonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtrasActionPerformed
        this.menup.buttonUsuarios.setEnabled(true);
        this.menup.eliminarPanelActual();
        this.menup.refrescarGUI();
    }//GEN-LAST:event_jButtonAtrasActionPerformed
    
    public void pintarPanel(JPanel panel){
        panelActual=panel;
        this.add(panelActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1110, 570));
        this.revalidate();
        this.repaint();
    }
    

    public void pintarPanel(JPanel panel, int x, int y){//Sobrecarga que recibe coordenadas para centrar el panel
        panelActual=panel;
        this.add(panelActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 1110, 570));
        this.refrescarGUI();
    }
    
    public void pintarPanel(JPanelTablaPlanes panel){
        tablaPlanes = panel;
        //panelTabla = panel;
        add(tablaPlanes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 890, 490));
        this.refrescarGUI();
    }
    
    public void pintarPanel(JPanelTablaPlanes panel, int x, int y){//Sobrecarga que recibe coordenadas para centrar el panel
        tablaPlanes = panel;
        //panelTabla = panel;
        add(tablaPlanes, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 890, 490));
        this.refrescarGUI();
    }
    
    public void pintarPanel(JPanelTablaUsuarios panel){
        tablaUsuarios = panel;
        //panelTabla = panel;
        add(tablaUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 890, 490));
        this.refrescarGUI();
    }
    
    public void eliminarPanel(){
        this.remove(panelActual);
        this.refrescarGUI();
    }
    

    public void enableButtons(boolean b){
        jButtonAdmiinistrarPlan.setEnabled(b);
        jButtonRegistrarPlan.setEnabled(b);
        jButtonRegistrarUsuario.setEnabled(b);
        jButtonAdmiinistrarUsuarios.setEnabled(b);
        jButtonAtras.setEnabled(b);
    }

    
    //Borrar despues de pruebas
    public void eliminarTablaPlanes(){
        this.remove(tablaPlanes);
        tablaPlanes=null;
        //tabla=null;
        this.revalidate();
        this.repaint();
    }
    
    public void eliminarTablaUsuarios(){
        this.remove(tablaUsuarios);
        tablaUsuarios=null;
        //tabla=null;
        this.revalidate();
        this.repaint();
    }
    
    
    public void refrescarGUI(){
        menup.refrescarGUI();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdmiinistrarPlan;
    private javax.swing.JButton jButtonAdmiinistrarUsuarios;
    private javax.swing.JButton jButtonAtras;
    private javax.swing.JButton jButtonRegistrarPlan;
    private javax.swing.JButton jButtonRegistrarUsuario;
    // End of variables declaration//GEN-END:variables
}
