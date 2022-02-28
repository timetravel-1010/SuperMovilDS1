/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.clientes;

import javax.swing.JPanel;

/**
 *
 * @author pc
 */
public class ModuloClientes extends javax.swing.JFrame {
    
    private JPanelTablaClientes panelTabla;

    /**
     * Creates new form ModuloClientes
     */
    public ModuloClientes() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        tablaC.setVisible(false);
    }
    
    public void pintarPanel(){
        add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 620, 256));
        panelTabla.agregarTodos();
        this.revalidate();
        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTablaClientes1 = new vista.clientes.JPanelTablaClientes();
        jPanel1 = new javax.swing.JPanel();
        panelInicial = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        tablaC = new vista.clientes.JPanelTablaClientes();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelInicial.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelInicial.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));
        panelInicial.add(tablaC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 540, 270));

        getContentPane().add(panelInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 825, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:        
        //this.remove(panelInicial);
        //this.revalidate();
        //this.repaint();
        //panelTabla = new JPanelTablaClientes();
        //this.pintarPanel();
        tablaC.setVisible(true);
        tablaC.agregarTodos();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ModuloClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private vista.clientes.JPanelTablaClientes jPanelTablaClientes1;
    private javax.swing.JPanel panelInicial;
    private vista.clientes.JPanelTablaClientes tablaC;
    // End of variables declaration//GEN-END:variables
}
