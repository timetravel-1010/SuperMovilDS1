package vista.usuarios;

import controlador.ConnectionDB;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Manuel
 */
public class JPanelDatosUsuario extends javax.swing.JPanel {
    
    private JPanelAdministrarUsuarios padreAdmin;
    private JPanelUsuarios padreUsers;
    private JPanel panelActual;
    private ConnectionDB db;
    private boolean tipoPanel; //true=panelAdmin, false = panelUsuarios.
    /**
     * Creates new form JPanelDatosUsuario
     */
    public JPanelDatosUsuario(JPanelAdministrarUsuarios papa) {
        initComponents();
        this.setBackground(new java.awt.Color(218, 234, 255));
        panelActual = new JPanel();
        this.panelActual.setBackground(new java.awt.Color(218, 234, 255));
        tipoPanel=true;
        this.add(panelActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 890, 490));
        padreAdmin = papa;
        db = new ConnectionDB();
    }
    
    public JPanelDatosUsuario(JPanelUsuarios papa) {
        initComponents();
        titulo.setText("Registro:");
        panelActual = new JPanel();
        this.panelActual.setBackground(new java.awt.Color(218, 234, 255));
        tipoPanel=false;
        this.add(panelActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 890, 490));
        padreUsers = papa;
        db = new ConnectionDB();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonEnviar2 = new javax.swing.JButton();
        jTextPassword = new javax.swing.JTextField();
        jTextUsername = new javax.swing.JTextField();
        jTextDireccion = new javax.swing.JTextField();
        jTextTelefono = new javax.swing.JTextField();
        jTextNombre = new javax.swing.JTextField();
        jTextCedula = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        jComboBoxRol = new javax.swing.JComboBox<>();
        jButtonCancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(218, 234, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonEnviar2.setBackground(new java.awt.Color(149, 193, 255));
        jButtonEnviar2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonEnviar2.setForeground(new java.awt.Color(0, 0, 0));
        jButtonEnviar2.setText("Enviar");
        jButtonEnviar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviar2jButtonEnviarActionPerformed(evt);
            }
        });
        add(jButtonEnviar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, 100, 30));

        jTextPassword.setBackground(new java.awt.Color(149, 193, 255));
        jTextPassword.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jTextPassword.setForeground(new java.awt.Color(0, 0, 0));
        add(jTextPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 230, 30));

        jTextUsername.setBackground(new java.awt.Color(149, 193, 255));
        jTextUsername.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jTextUsername.setForeground(new java.awt.Color(0, 0, 0));
        add(jTextUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 230, 30));

        jTextDireccion.setBackground(new java.awt.Color(149, 193, 255));
        jTextDireccion.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jTextDireccion.setForeground(new java.awt.Color(0, 0, 0));
        add(jTextDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 230, 30));

        jTextTelefono.setBackground(new java.awt.Color(149, 193, 255));
        jTextTelefono.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jTextTelefono.setForeground(new java.awt.Color(0, 0, 0));
        jTextTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTelefonojTextTelefonoActionPerformed(evt);
            }
        });
        add(jTextTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 230, 30));

        jTextNombre.setBackground(new java.awt.Color(149, 193, 255));
        jTextNombre.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jTextNombre.setForeground(new java.awt.Color(0, 0, 0));
        add(jTextNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 230, 30));

        jTextCedula.setBackground(new java.awt.Color(149, 193, 255));
        jTextCedula.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jTextCedula.setForeground(new java.awt.Color(0, 0, 0));
        jTextCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextCedulajTextCedulaActionPerformed(evt);
            }
        });
        add(jTextCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 230, 30));

        jLabel20.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Cedula:");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 170, 30));

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Nombre:");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 170, 30));

        jLabel19.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Telefono:");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 170, 30));

        jLabel23.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Dirección:");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 170, 30));

        jLabel21.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Nombre de usuario:");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 170, 30));

        jLabel22.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Password:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 170, 30));

        jLabel24.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Rol:");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 170, 30));

        titulo.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 0, 0));
        titulo.setText("jLabel1");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 120, 30));

        jComboBoxRol.setBackground(new java.awt.Color(149, 193, 255));
        jComboBoxRol.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jComboBoxRol.setForeground(new java.awt.Color(0, 0, 0));
        jComboBoxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "administrador", "gerente", "operador" }));
        jComboBoxRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRolActionPerformed(evt);
            }
        });
        add(jComboBoxRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 231, 30));

        jButtonCancelar.setBackground(new java.awt.Color(149, 193, 255));
        jButtonCancelar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonCancelar.setForeground(new java.awt.Color(0, 0, 0));
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 100, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnviar2jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviar2jButtonEnviarActionPerformed
        // TODO add your handling code here:
        
        if (validarRegistroCliente()) {
            String cedula = jTextCedula.getText();
            String nombre = jTextNombre.getText();
            String telefono = jTextTelefono.getText();
            String direccion = jTextDireccion.getText();
            String nombre_usuario = jTextUsername.getText();
            String passwordd = jTextPassword.getText();

            String rol = jComboBoxRol.getItemAt(jComboBoxRol.getSelectedIndex());
            boolean resultado;

            if (tipoPanel) {
                resultado = db.updateUsuario(cedula, nombre, telefono, direccion, nombre_usuario, passwordd, rol);

                if (resultado) {
                    JOptionPane.showMessageDialog(null, "¡La actualización de los datos del usuario fue exitosa!",
                            "Actualización", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "¡No se realizó ninguna modificación!",
                            "Actualización", JOptionPane.ERROR_MESSAGE);
                }
                padreAdmin.eliminarPanel();
                padreAdmin.enableButtons(true);
            } else {
                resultado = db.registrarUsuarios(cedula, nombre, telefono, direccion, nombre_usuario, passwordd, rol);

                if(resultado){
                    JOptionPane.showMessageDialog(null, "¡El registro de los datos del usuario fue exitoso!",
                            "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                    padreUsers.eliminarPanel();
                    padreUsers.enableButtons(true);
                } else {
                    JOptionPane.showMessageDialog(null, "¡No se pudo realizar el registro!", 
                            "Registro Incorrecto", JOptionPane.ERROR_MESSAGE);
                    padreUsers.eliminarPanel();
                    padreUsers.enableButtons(true);
                }
            }
        }          
    }//GEN-LAST:event_jButtonEnviar2jButtonEnviarActionPerformed

    private void jTextTelefonojTextTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTelefonojTextTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTelefonojTextTelefonoActionPerformed

    private void jTextCedulajTextCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextCedulajTextCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextCedulajTextCedulaActionPerformed

    private void jComboBoxRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxRolActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        if(tipoPanel){
            padreAdmin.eliminarPanel();
            padreAdmin.enableButtons(true);
        } else {
            padreUsers.enableButtons(true);
            padreUsers.eliminarPanel();
        }
        
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private boolean validarRegistroCliente(){
        boolean exito = true;
        
        if(jTextCedula.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "¡Ingrese una cedula valida!",
                            "Validacion incorrecta", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        
        if(jTextNombre.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "¡Ingrese un nombre valido!",
                            "Validacion incorrecta", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        
        if(jTextTelefono.getText().length()<7){
            JOptionPane.showMessageDialog(null, "¡Ingrese una numero telefonico valido!",
                            "Validacion incorrecta", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(jTextPassword.getText());
        if(!mat.find()){
            JOptionPane.showMessageDialog(null, "¡Ingrese una contraeña valida!\n- Su contraseña debe contener al menos 8 caracteres"+
                                          "\n- Una mezcla de letras mayúsculas y minúsculas \n- Una mezcla de letras y números"+ 
                                          "\n- La inclusión de al menos un carácter especial, por ejemplo: #!@] *($",
                            "Validacion incorrecta", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        
        
        if(jTextUsername.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "¡Ingrese un username valido!",
                            "Validacion incorrecta", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        
        if(jTextDireccion.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "¡Ingrese una direccion valida!",
                            "Validacion incorrecta", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        
        if(db.consultarUsername(jTextUsername.getText())) {
            JOptionPane.showMessageDialog(null, "¡Nombre de usuario ya existe!",
                            "Validacion incorrecta", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        
        return exito;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEnviar2;
    public javax.swing.JComboBox<String> jComboBoxRol;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    public javax.swing.JTextField jTextCedula;
    public javax.swing.JTextField jTextDireccion;
    public javax.swing.JTextField jTextNombre;
    public javax.swing.JTextField jTextPassword;
    public javax.swing.JTextField jTextTelefono;
    public javax.swing.JTextField jTextUsername;
    public javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
