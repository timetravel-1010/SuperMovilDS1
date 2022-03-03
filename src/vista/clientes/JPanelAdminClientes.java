package vista.clientes;

import vista.usuarios.*;
import controlador.ConnectionDB;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;

/**
 *
 * @author cristian
 */
public class JPanelAdminClientes extends javax.swing.JPanel {
    private ConnectionDB db;
    private JPanelClientes padre;
    private DefaultTableModel modeloT;  
    private JPanelClientesSuspender tablaClientesPlan;
    private JPanelTablaClientes tablaCliente;
    private JPanelClientesReactivar tablaClientesReactivar;
    private JPanel panelActual;
    

    public JPanelAdminClientes(JPanelClientes papa) {
        this.padre = papa;
        this.db = new ConnectionDB();
        this.tablaClientesPlan = new JPanelClientesSuspender(this);
        this.tablaCliente = new JPanelTablaClientes();
        this.tablaClientesReactivar = new JPanelClientesReactivar();
        initComponents();
        //tablaClientes.setModel(modeloT);
        //this.agregarClientesPlan();
        panelActual = tablaCliente;
        //this.pintarPanel(tablaCliente);
        this.pintarPanel();
        this.agregarTodos();
        this.setVisible(true);
        this.enableMRS(true, false, false);
    }
    
    private void pintarPanel(JPanel panel) {
        Dimension dimTabla = panel.getPreferredSize();
        add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, dimTabla.width, dimTabla.height));
        this.refrescarGUI();
    }
    
    private void pintarPanel() {
        Dimension dimTabla = panelActual.getPreferredSize();
        add(panelActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, dimTabla.width, dimTabla.height));
        this.refrescarGUI();
    }
    
    public void agregarTodos(){
        List<Cliente> lista = db.obtenerClientes();
        tablaCliente.agregarTodos(lista);
        this.refrescarGUI();
    }
    
    public void agregarClientesPlan(){
        List<String[]> lista = db.obtenerClientesPagoAtrasado();
        tablaClientesPlan.agregarClientesPlan(lista);
        this.refrescarGUI();
    }
    
    public void agregarClientesReactivar() {
        List<String[]> lista = db.obtenerClientesReactivacion();
        tablaClientesReactivar.agregarClientesReactivar(lista);
        this.refrescarGUI();
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JPanelDatosUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JPanelDatosUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JPanelDatosUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JPanelDatosUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold> 

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               //new JPanelAdminClientes();
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reactivarBtn = new javax.swing.JButton();
        suspenderBtn = new javax.swing.JButton();
        modificarClienteBtn = new javax.swing.JButton();
        filtrarLabel = new javax.swing.JLabel();
        comboBoxTiposTablas = new javax.swing.JComboBox<>();

        setMinimumSize(new java.awt.Dimension(890, 510));
        setPreferredSize(new java.awt.Dimension(987, 364));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        reactivarBtn.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        reactivarBtn.setText("Reactivar Plan");
        reactivarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reactivarBtnActionPerformed(evt);
            }
        });
        add(reactivarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, -1, -1));

        suspenderBtn.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        suspenderBtn.setText("Suspender Plan");
        suspenderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suspenderBtnActionPerformed(evt);
            }
        });
        add(suspenderBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 150, -1));

        modificarClienteBtn.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        modificarClienteBtn.setText("Modificar Cliente");
        modificarClienteBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        modificarClienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarClienteBtnActionPerformed(evt);
            }
        });
        add(modificarClienteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 160, 40));

        filtrarLabel.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        filtrarLabel.setText("Filtrar por:");
        filtrarLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(filtrarLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        comboBoxTiposTablas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los Clientes", "Clientes Pago Atrasado", "Clientes Reactivar Plan" }));
        comboBoxTiposTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTiposTablasActionPerformed(evt);
            }
        });
        add(comboBoxTiposTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));
    }// </editor-fold>//GEN-END:initComponents
        
    public String obtenerPlan() {
        return tablaClientesPlan.obtenerPlan();
    }
    
    public String obtenerNumeroReactivar() {
        return this.tablaClientesReactivar.obtenerNumeroSeleccionado();
    }
    
    public String obtenerNumeroSuspender() {
        return this.tablaClientesPlan.obtenerNumeroSeleccionado();
    }
    
    public String obtenerCedulaSeleccionada() {
        return this.tablaCliente.obtenerCedulaSeleccionada();
    }
    
    private void reactivarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reactivarBtnActionPerformed
        // TODO add your handling code here:
        String numero = this.obtenerNumeroReactivar();
        int result = JOptionPane.showConfirmDialog(null,"¿Seguro que desea reactivar el plan del cliente?", "Confirmación",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION){
           Boolean correcto = db.cambiarEstadoPlan(numero, true);
            if (correcto) {
               JOptionPane.showMessageDialog(null, "¡La reactivación fue exitosa!",
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
               this.refrescarGUI();
            } else {
               JOptionPane.showMessageDialog(null, "¡No se ha realizado ninguna modificación!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        this.actualizarTablaClientesPlan();
    }//GEN-LAST:event_reactivarBtnActionPerformed

    private void suspenderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suspenderBtnActionPerformed
        String numero = this.obtenerNumeroSuspender();
        int result = JOptionPane.showConfirmDialog(null,"¿Seguro que desea suspender el plan del cliente?", "Confirmación",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION){
           Boolean correcto = db.cambiarEstadoPlan(numero, false);
           if (correcto) {
               JOptionPane.showMessageDialog(null, "¡Se ha suspendido el plan correctamente!",
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
               this.refrescarGUI();
           } else {
               JOptionPane.showMessageDialog(null, "¡No se ha realizado ninguna modificación!",
                        "Error", JOptionPane.ERROR_MESSAGE);
           }
        }
        this.actualizarTablaClientesPlan();
    }//GEN-LAST:event_suspenderBtnActionPerformed

    public void suspenderTodos(List<String> numeros) { 
        int result = JOptionPane.showConfirmDialog(null,"¿Seguro que desea suspender el plan de todos los cliente?", "Confirmación",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION){
           for (String numero : numeros) {
                Boolean correcto = db.cambiarEstadoPlan(numero, false);
                if (!correcto) {
                    JOptionPane.showMessageDialog(null, "¡No se pudo realizar una modificación!",
                             "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
           }
           JOptionPane.showMessageDialog(null, "¡Se ha suspendido el plan correctamente!",
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
               this.refrescarGUI();
        }
        this.actualizarTablaClientesPlan();
    }      
    
    private void modificarClienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarClienteBtnActionPerformed
        this.padre.modificarCliente(this.obtenerCedulaSeleccionada()); //modificar cliente.
    }//GEN-LAST:event_modificarClienteBtnActionPerformed

    private void comboBoxTiposTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTiposTablasActionPerformed
        // cambiar el tipo de la tabla que se muestra.
        int opcion = comboBoxTiposTablas.getSelectedIndex();

        switch (opcion) {
            case 0: //tabla con todos los clientes.
                //this.eliminarPanel(tablaClientesPlan);
                this.eliminarPanel(panelActual);
                //pintarPanel(this.tablaCliente);
                this.agregarTodos();
                this.panelActual = tablaCliente;
                this.pintarPanel();
                enableMRS(true, false, false);
                break;
            case 1: //tabla de clientes con pagos retrasados. Para suspender su plan.
                //this.eliminarPanel(tablaCliente);
                this.eliminarPanel(panelActual);
                //pintarPanel(this.tablaClientesPlan);
                this.panelActual = tablaClientesPlan;
                this.pintarPanel();
                this.agregarClientesPlan();
                enableMRS(false, false, true);
                break;
            case 2: //tabla de clientes para reactivar su plan.
                this.eliminarPanel(panelActual);
                this.panelActual = tablaClientesReactivar;
                this.pintarPanel();
                this.agregarClientesReactivar();
                enableMRS(false, true, false);
                break;
        }
    }//GEN-LAST:event_comboBoxTiposTablasActionPerformed
    
    public void eliminarPanel(JPanel panel) {
        this.remove(panel);
        this.refrescarGUI();
    }
    
    public void actualizarTablaClientesPlan() {
        this.agregarClientesPlan();
    }
    
    public void refrescarGUI(){
        this.revalidate();
        this.repaint();
    }
    
    public int obtenerAncho() {
        return this.getWidth();
    }
    
    public int obtenerAlto() {
        return this.getHeight();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxTiposTablas;
    private javax.swing.JLabel filtrarLabel;
    private javax.swing.JButton modificarClienteBtn;
    private javax.swing.JButton reactivarBtn;
    private javax.swing.JButton suspenderBtn;
    // End of variables declaration//GEN-END:variables

    private void enableMRS(boolean mod, boolean reac, boolean susp) {
        this.reactivarBtn.setEnabled(reac);
        this.suspenderBtn.setEnabled(susp);
        this.modificarClienteBtn.setEnabled(mod);
    }
}
