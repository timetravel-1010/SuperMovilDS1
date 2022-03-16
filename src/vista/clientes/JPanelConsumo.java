/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.clientes;

import controlador.ConnectionDB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import vista.clientes.JPanelClientes;

/**
 *
 * @author cristian
 */
public class JPanelConsumo extends javax.swing.JPanel {

    /**
     * Creates new form JPanelConsumo
     */
    private JPanelClientes padre;
    private ConnectionDB db;
    private FileReader archivos;
    private String ruta="";
    public JPanelConsumo(JPanelClientes papa, ConnectionDB db) {
        this.padre = papa;
        initComponents();
        this.db = db;
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
        jButtonSeleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButtonCancelar = new javax.swing.JButton();
        jButtonCargar = new javax.swing.JButton();

        setBackground(new java.awt.Color(218, 234, 255));

        jLabelTitulo.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabelTitulo.setText("Cargar consumo clientes:");

        jButtonSeleccionar.setBackground(new java.awt.Color(149, 193, 255));
        jButtonSeleccionar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtonSeleccionar.setText("Seleccionar archivo");
        jButtonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarjButtonEnviarActionPerformed(evt);
            }
        });

        jTextArea1.setBackground(new java.awt.Color(149, 193, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        jButtonCancelar.setBackground(new java.awt.Color(149, 193, 255));
        jButtonCancelar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonCargar.setBackground(new java.awt.Color(149, 193, 255));
        jButtonCargar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtonCargar.setText("Enviar");
        jButtonCargar.setEnabled(false);
        jButtonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarjButtonEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(150, 150, 150)
                            .addComponent(jButtonSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(110, 110, 110)
                            .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(90, 90, 90)
                            .addComponent(jButtonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(150, 150, 150)
                            .addComponent(jLabelTitulo)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(jButtonSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSeleccionarjButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarjButtonEnviarActionPerformed
        // TODO add your handling code here:
        String aux="";
        String texto="";
        try
        {
            /**llamamos el metodo que permite cargar la ventana*/
            JFileChooser file=new JFileChooser();
            file.showOpenDialog(this);
            /**abrimos el archivo seleccionado*/
            File abre=file.getSelectedFile();
            this.ruta=abre.getAbsolutePath();
            /**recorremos el archivo, lo leemos para plasmarlo
            *en el area de texto*/
            if(abre!=null)
            {
                this.jButtonCargar.setEnabled(true);
                this.archivos=new FileReader(abre);
                BufferedReader lee=new BufferedReader(archivos);
                while((aux=lee.readLine())!=null)
                {
                    texto+= aux+ "\n";
                }
                lee.close();
            }
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null,ex+"" +
                "\nNo se ha encontrado el archivo",
                "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
        }
        this.jTextArea1.setText(texto);
    }//GEN-LAST:event_jButtonSeleccionarjButtonEnviarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.padre.eliminarPanel();
        this.padre.enableButtons(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonCargarjButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarjButtonEnviarActionPerformed
        // TODO add your handling code here:
        //Se capturan los datos del cliente para registrarlo o actualizarlo
        db.updateConsumoClientes(ruta);
        this.padre.eliminarPanel();
        this.padre.enableButtons(true);

    }//GEN-LAST:event_jButtonCargarjButtonEnviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCargar;
    private javax.swing.JButton jButtonSeleccionar;
    public javax.swing.JLabel jLabelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
