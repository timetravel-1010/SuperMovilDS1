package controlador;

import enums.TipoLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author pc
 */
public class ConnectionDB {
    
    private String username = "cjtfsjkanranmo";
    private String password = "50b8ab7ebf0bb2df1f231e72fe435ee98c1d29696c1aa5faebeeeaa8390b3055";
    private String dbname = "dd17g890crnth6";
    private String host = "ec2-54-235-98-1.compute-1.amazonaws.com";
    private int puerto = 5432;
    private Connection conexion;
    private Usuario elUsuario;
    private boolean validar ;
    
    public ConnectionDB() {        
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            String url = "jdbc:postgresql://"+host+":"+puerto+"/"+dbname;
            
            conexion = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public ArrayList<Usuario> getUsuarios() {
    	ArrayList<Usuario> usuarios = new ArrayList<>(); // para guardar los datos en un array.
        
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            
            while (rs.next()){ // guardar los datos en la lista de usuarios.
                Usuario unUsuario = new Usuario(rs.getString(1),
                                                rs.getString(2),
                                                rs.getString(3),
                                                rs.getString(4),
                                                rs.getString(5),
                                                rs.getString(6),
                                                rs.getString(7),
                                                rs.getString(8),
                                                rs.getString(9));
                usuarios.add(unUsuario);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return usuarios;   
    }
    
    public ArrayList<Usuario> getUsuariosByName(String nombre) {
    	ArrayList<Usuario> usuarios = new ArrayList<>(); // para guardar los datos en un array.
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM usuarios WHERE nombre = ?");
            sql.setString(1, nombre);
            
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            
            while (rs.next()){ // guardar los datos en la lista de usuarios.
                Usuario unUsuario = new Usuario(rs.getString(1),
                                                rs.getString(2),
                                                rs.getString(3),
                                                rs.getString(4),
                                                rs.getString(5),
                                                rs.getString(6),
                                                rs.getString(7),
                                                rs.getString(8),
                                                rs.getString(9));
                usuarios.add(unUsuario);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println(nombre);
        for(int i=0;i<usuarios.size();i++){
            System.out.println(usuarios.get(i).getCedula());
        }
        return usuarios;   
    }
    
    /**
     * Metodo utilizado para obtener los datos del usuario que se encuentra en el sistema.
     * @param nombreUsuario
     * @param password
     * @return Usuario, un objeto usuario con sus datos.
     */
    public Usuario getUsuario(String nombreUsuario, String password) {
        Usuario usuario = null;
        
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM usuarios WHERE nombre_usuario = ? and passwordd = ?");
            sql.setString(1, nombreUsuario);
            sql.setString(2, password);
            
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                usuario = new Usuario(rs.getString(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getString(4),
                                        rs.getString(5),
                                        rs.getString(6),
                                        rs.getString(7),
                                        rs.getString(8),
                                        rs.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
    
    
        /**
     * Metodo utilizado para obtener los datos del usuario que se encuentra en el sistema.
     * @param cedula
     * @return Usuario, un objeto usuario con sus datos.
     */
    public Usuario getUsuario2(String cedula) {
        Usuario usuario = null;
        
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM usuarios WHERE cedula = ?");
            sql.setString(1, cedula);
            
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                usuario = new Usuario(rs.getString(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getString(4),
                                        rs.getString(5),
                                        rs.getString(6),
                                        rs.getString(7),
                                        rs.getString(8),
                                        rs.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
    

    public TipoLogin validarUsuario(String nombreUsuario, String clave) {
        elUsuario = getUsuario(nombreUsuario, clave);
        
        if (elUsuario != null) {
            if (elUsuario.getEstado().equals("inactivo")) 
                return TipoLogin.Inactivo;
            else if ("online".equals(elUsuario.getStatus_login()))
                return TipoLogin.YaEnLinea;
            else {
                elUsuario.setStatus_login("online");
                updateStatus_login(elUsuario.getCedula(), true);
                return TipoLogin.Correcto;
            }
        }
        return TipoLogin.Incorrecto;
    }
    
    public void updateStatus_login(String cedula, boolean status){
        String estado = status ? "online" : "offline";
        if (!status)
            elUsuario = null;
        try {
            PreparedStatement sql = conexion.prepareStatement("UPDATE usuarios set status_login = ?::online_status WHERE cedula = ?");
            sql.setString(1, estado);
            sql.setString(2, cedula);
            int rs = sql.executeUpdate();  // ejecutar la sentencia.

            if (rs == 0) { // no se afecto ninguna fila.
                // se debe mostrar un mensaje de error o algo.
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void validarUsuarioRe(String cedula){

        String validarU = "";
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT cedula FROM usuarios WHERE cedula = ?");

            sql.setString(1, cedula);


            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                validarU = rs.getString(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        if(validarU.equals(cedula)){
             validar=true;
             System.out.println("True" );
        }else{
             validar=false;
             System.out.println("false");
        }
    }

    public boolean registrarUsuarios(String cedula, String nombre, String telefono,
                                  String direccion, String nombreU, String password,
                                  String rol){
        String estado="activo";
        String status_login="offline";
        validarUsuarioRe(cedula);
        int filasAfectadas = 0;
        
        if(!validar){ // se comprueba que el usuario no exista.
            try {

            PreparedStatement sql = conexion.prepareStatement("INSERT INTO usuarios VALUES(?,?,?,?,?,?,?::status,?::roles,?::online_status)");

            sql.setString(1, cedula);
            sql.setString(2, nombre);
            sql.setString(3, telefono);
            sql.setString(4, direccion);
            sql.setString(5, nombreU);
            sql.setString(6, password);
            sql.setString(7, estado);
            sql.setString(8, rol);
            sql.setString(9, status_login);

            filasAfectadas = sql.executeUpdate();  // ejecutar la sentencia.
            } catch (SQLException ex) {
                System.out.println("No funciona2");
            }
        }
        return filasAfectadas != 0;
    }
    
    public Usuario getUsuarioOnline() {
        return elUsuario;
    }


        /**
     * Metodo utilizado para actualizar el estado de un usuario.
     * @param nombreUsuario
     * @return Usuario, un objeto usuario con sus datos.
     */
    public Boolean updateEstadoUsuario(String cedula, boolean status) {
        //Usuario usuario = null;
                
        String estado = status ? "activo" : "inactivo";
        
        try {
            PreparedStatement sql = conexion.prepareStatement("UPDATE usuarios SET estado = ?::status WHERE cedula = ?");
            sql.setString(1, estado);
            sql.setString(2, cedula);
            
            int rs = sql.executeUpdate();  // ejecutar la sentencia.
            System.out.println("Se han actualizado: "+rs+" filas.\n");
            if (rs == 0) { // no se afecto ninguna fila.
                return false; // se debe mostrar un mensaje de error o algo.
            }
                        
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
   /**
    * 
    * @param cedula
    * @param nombre
    * @param telefono
    * @param direccion
    * @param nombre_usuario
    * @param passwordd
    * @param rol 
    */
   public boolean updateUsuario(String cedula, String nombre, String telefono, String direccion, 
                            String nombre_usuario, String passwordd, String rol) {
       try {
            PreparedStatement sql = conexion.prepareStatement("UPDATE usuarios SET nombre = ?, telefono = ?, direccion = ?, nombre_usuario = ?," 
                                                                +""+ "passwordd = ?, rol = ?::roles  WHERE cedula = ?"                                                           
                                                               );
            sql.setString(1, nombre);
            sql.setString(2, telefono);
            sql.setString(3, direccion);
            sql.setString(4, nombre_usuario);
            sql.setString(5, passwordd);
            sql.setString(6, rol);
            sql.setString(7, cedula);
            
            int rs = sql.executeUpdate();
            
            return rs != 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
   }
}
