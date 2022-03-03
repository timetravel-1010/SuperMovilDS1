package controlador;

import enums.TipoLogin;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Planes;
import modelo.Usuario;
import vista.pagos.PagosBancos;

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
    private PagosBancos pb;
    
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
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM usuarios order by nombre");
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
    	ArrayList<Usuario> usuarios = new ArrayList<>(); // para guardar los datos en un array
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM usuarios WHERE nombre like ?");
            sql.setString(1, "%" + nombre + "%");
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
    * Metodo utilizado para actualizar un usuario
    * @param cedula
    * @param nombre
    * @param telefono
    * @param direccion
    * @param nombre_usuario
    * @param passwordd
    * @param rol 
    * @return booleano true en caso de llevarse a cabo la actualización, en caso contrario false
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
   
     /**
     * Metodo utilizado para obtener los datos del plan que se encuentra en el sistema.
     * @param id
     * @return Plan, un objeto plan con sus datos.
     */
    public Planes getPlan(Integer identificador) {
        Planes planes = null;
        
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM planes WHERE identificador = ?");
            sql.setInt(1, identificador);
            
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                planes = new Planes(rs.getString(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getString(4),
                                        rs.getString(5),
                                        rs.getString(6),
                                        rs.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return planes;
    }
    
    
    /**
     * Metodo utilizado para obtener los datos de todos los planes que se encuentra en el sistema.
     * @param id
     * @return ArrayList, un arreglo que contiene los planes con sus datos.
     */
    public ArrayList<Planes> getPlanes() {

        ArrayList<Planes> planes = new ArrayList<>();        
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM planes order by identificador");
            
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            while (rs.next()) {
                Planes plan = new Planes(rs.getString(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getString(4),
                                        rs.getString(5),
                                        rs.getString(6),
                                        rs.getString(7));
                planes.add(plan);               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return planes;
    }
    
    /**
     * Metodo, registra un nuevo plan en la base de datos
     * @param nombre
     * @param descripcion
     * @param precio
     * @param minutos
     * @param megas
     * @param mensajes
     * @return boolean, true en caso de que el registro se ejecute correctamente, en caso contrario false
     */
    public boolean registrarPlanes(String nombre, String descripcion, Integer precio,
                                  Integer minutos, Integer megas, Integer mensajes){
        
        //validarUsuarioRe(cedula);
        int filasAfectadas = 0;
        
        if(!validar){ // se comprueba que el usuario no exista.
            try {

            PreparedStatement sql = conexion.prepareStatement("INSERT INTO planes(nombre, descripcion, precio, minutos, megas, mensajes) "
                    + "VALUES(?,?,?,?,?,?)");

            sql.setString(1, nombre);
            sql.setString(2, descripcion);
            sql.setInt(3, precio);
            sql.setInt(4, minutos);
            sql.setInt(5, megas);
            sql.setInt(6, mensajes);

            filasAfectadas = sql.executeUpdate();  // ejecutar la sentencia.
            } catch (SQLException ex) {
                System.out.println("No funciona2");
            }
        }
        return filasAfectadas != 0;
    }
    
    /**
     * Metodo, permite actualizar un plan especifico
     * @param identificador
     * @param nombre
     * @param descripcion
     * @param precio
     * @param minutos
     * @param megas
     * @param mensajes
     * @return boolean, true en caso de que la actualización se ejecute correctamente, en caso contrario false
     */
    public boolean updatePlan(Integer identificador, String nombre, String descripcion, Integer precio,
                                  Integer minutos, Integer megas, Integer mensajes){
        
        int filasAfectadas = 0;
        
         try {

            PreparedStatement sql = conexion.prepareStatement("UPDATE planes SET nombre = ?, descripcion = ?, precio = ?," +
                    "minutos = ?, megas = ?, mensajes = ? WHERE identificador = ?");

            sql.setString(1, nombre);
            sql.setString(2, descripcion);
            sql.setInt(3, precio);
            sql.setInt(4, minutos);
            sql.setInt(5, megas);
            sql.setInt(6, mensajes);
            sql.setInt(7, identificador);

            filasAfectadas = sql.executeUpdate();  // ejecutar la sentencia.
            } catch (SQLException ex) {
                System.out.println("No funciona2");
            }
         
        return filasAfectadas != 0;
    }
    
    /**
     * metodo, registra un pago en la base de datos
     * @param numeroFactura
     * @param valorPagado
     * @return boolean, true en caso de que el registro se ejecute correctamente, en caso contrario false
     */
    public boolean registrarPago(Integer numeroFactura, Integer valorPagado) {
        int filasAfectadas = 0;
        
         try {

            PreparedStatement sql = conexion.prepareStatement("UPDATE facturas SET valor_pagado = ? where numero_factura = ?");

            sql.setInt(1, valorPagado);
            sql.setInt(2, numeroFactura);
 

            filasAfectadas = sql.executeUpdate();  // ejecutar la sentencia.
            } catch (SQLException ex) {
                System.out.println("No funciona2");
            }
         
        return filasAfectadas != 0;
        
    }
    
    /**
     * metodo, actualiza la fecha de un pago en la base de datos, tabla cuentas
     * @param numeroFactura
     * @return boolean, true en caso de que la actualización se ejecute correctamente, en caso contrario false
     * @throws ParseException 
     */
    public boolean modificarFechaPago(Integer numeroFactura) throws ParseException {
        int filasAfectadas = 0;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPago = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Date fecha =  formato.parse(fechaPago);
        Date fechaSQL = new java.sql.Date(fecha.getTime());
        
                
         try {

            PreparedStatement sql = conexion.prepareStatement("UPDATE cuentas SET ultimo_pago = ? where identificador in (select numero_cuenta from facturas where numero_factura = ?)");

            sql.setDate(1, (java.sql.Date) fechaSQL);
            sql.setInt(2, numeroFactura);
 

            filasAfectadas = sql.executeUpdate();  // ejecutar la sentencia.
            } catch (SQLException ex) {
                System.out.println("No funciona");
                System.out.println(ex.getMessage());
            }
         
        return filasAfectadas != 0;
        
    }
    
    
    /**
     * metodo, consulta el valor de un pago realizado, en la base de datos
     * @param numeroFactura
     * @return Integer, retorna el valor que se pagó, en que caso de no encontrar un pago, retorna 0
     */
    public Integer consultarPagoRealizado(Integer numeroFactura) { 
        int filasAfectadas = 0;
        
         try {

            PreparedStatement sql = conexion.prepareStatement("select valor_pagado from facturas where  numero_factura = ?");

            sql.setInt(1, numeroFactura);
 

            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("No funciona");
            }
        
        return 0;
    }
    
    
    /**
     * metodo, consulta el valor que debe pagar el cliente
     * @param numeroFactura
     * @return Integer, con el valor que debe pagar el cliente
     */
    public Integer consultarValorPago(Integer numeroFactura) {         
        try {

            PreparedStatement sql = conexion.prepareStatement("select valor_a_pagar from facturas where  numero_factura = ?");

            sql.setInt(1, numeroFactura);
 

            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("No funciona");
        }
        
        return 0;
    }
    
    
    /**
    * valida si el numero de linea existe en la base de datos
    */
   
    public boolean validarLineaCliente(String linea){
       
       boolean lineaExiste=true;
       try {
            PreparedStatement sql = conexion.prepareStatement("SELECT numero FROM lineas WHERE numero = ?");
            sql.setString(1, linea);
                         
            int rs = sql.executeUpdate();  // ejecutar la sentencia.
            System.out.println("Se han actualizado: "+rs+" filas.\n");
            if (rs == 0) { 
                lineaExiste= false; 
                }
            }catch (SQLException ex) {
            System.out.println("no funciona Busqueda");
            }
       return lineaExiste;
    }

   /**
    * Busca el numero de la cedula del cliente segun, el numero de linea
    */
   
    public String bucarIdentificadorCliente (String numero){
       
       String identificador="";
       
       try {
            PreparedStatement sql = conexion.prepareStatement("SELECT identificador FROM cuentas WHERE numero = ?");
            sql.setString(1, numero);
                         
            ResultSet rs = sql.executeQuery(); // ejecutar la sentencia.
            if (rs.next()) {
                identificador = rs.getString(1);
            }
            
            //System.out.println(identificador);
             
        
            }catch (SQLException ex) {
            System.out.println("no funciona Busqueda");
            }

       
       return identificador;
    }
   
    /**
    * realiza cambios masivos en la tabla facturas segun los pagos realizados en los bancos
    */
    public void updateValorPagoCuentaM() {
       
                
        String identificador="";
        String numero ;
        String pago;
        
        ArrayList<String> datos = new ArrayList<>();
        datos = pb.leerDeArchivo();
        
        int n=0;
        int p=1;
        int iteracciones = datos.size()/3;
        
     for(int i = 0; i<iteracciones; i++){
               
           numero = datos.get(n);
           identificador = bucarIdentificadorCliente(numero);
           pago = datos.get(p);
           System.out.println(numero +"\n");
           System.out.println(pago+"\n");
           System.out.println(identificador+"\n");
            try {
            PreparedStatement sql = conexion.prepareStatement("UPDATE facturas SET valor_pagado = ?::int WHERE numero_cuenta = ?::int");
            sql.setString(1, pago);
            sql.setString(2, identificador);
            
            int rs = sql.executeUpdate();  // ejecutar la sentencia.
            System.out.println("Se han actualizado: "+rs+" filas.\n");
            if (rs == 0) { // no se afecto ninguna fila.
                //return false; // se debe mostrar un mensaje de error o algo.
             }
        
            } catch (SQLException ex) {
            System.out.println("no funciona upDate facturas");
            }
         n = 3+n;
         p = 3+p;
           
       } 
       
   }
    
    
    /**
    * realiza cambios masivos en la tabla cuenta segun los pagos realizados en los bancos
    */
    public void updateUltimoPagoCuentaM() {


        String fechaPago ;
        String numero ;

        ArrayList<String> datos = new ArrayList<>();
        datos = pb.leerDeArchivo();

        int n=0;
        int f=2;
        int iteracciones = datos.size()/3;

        for(int i = 0; i<iteracciones; i++){



          numero = datos.get(n);
           fechaPago = datos.get(f);
           System.out.println(numero +"\n");
           System.out.println(fechaPago+"\n");
            try {
            PreparedStatement sql = conexion.prepareStatement("UPDATE cuentas SET ultimo_pago = ?::date WHERE numero = ?");
            sql.setString(1, fechaPago);
            sql.setString(2, numero);

            int rs = sql.executeUpdate();  // ejecutar la sentencia.
            System.out.println("Se han actualizado: "+rs+" filas.\n");
            if (rs == 0) { // no se afecto ninguna fila.
                //return false; // se debe mostrar un mensaje de error o algo.
             }

            } catch (SQLException ex) {
            System.out.println("no funciona upDate");
            }
         n = 3+n;
         f = 3+f;

       } 


   }
    
     
}
