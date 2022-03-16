package controlador;

import enums.TipoCliente;
import enums.TipoLogin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Cuenta;
import modelo.Linea;
import modelo.Plan;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
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
    private Plan elPlan;
    private PagosBancos pb;

    
    public ConnectionDB() {        
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            String url = "jdbc:postgresql://"+host+":"+puerto+"/"+dbname;
            
            conexion = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        pb = new PagosBancos();
        
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
    
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>(); // para guardar los datos en un array.
        
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM Clientes");
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            
            while (rs.next()){ // guardar los datos en la lista de usuarios.
                Cliente unCliente = new Cliente(rs.getString(1),
                                                rs.getString(2),
                                                rs.getString(3),
                                                rs.getString(4),
                                                rs.getString(5),
                                                TipoCliente.valueOf(rs.getString(6)));
                clientes.add(unCliente);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return clientes;   
    }
    
    public ArrayList<String[]> obtenerClientesPagoAtrasado() {
    	ArrayList<String[]> tabla = new ArrayList<>();
        try {
        	PreparedStatement sql = conexion.prepareStatement("SELECT * FROM mostrar_clientes_pago_atrasado;");
                ResultSet rs = sql.executeQuery();
                int numCol = 6;
                while (rs.next()){
                    String[] fila = new String[numCol];
                    for (int i = 0; i < numCol; i++) {
                        fila[i] = rs.getString(i+1);
                    }
                    tabla.add(fila);
                }
            
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return tabla;
    }
    
    public ArrayList<String[]> obtenerClientesReactivacion() {
    	ArrayList<String[]> tabla = new ArrayList<>();
        try {
        	PreparedStatement sql = conexion.prepareStatement("SELECT * FROM mostrar_clientes_activar_plan;");
                ResultSet rs = sql.executeQuery();
                int numCol = 6;
                while (rs.next()){
                    String[] fila = new String[numCol];
                    for (int i = 0; i < numCol; i++) {
                        fila[i] = rs.getString(i+1);
                    }
                    tabla.add(fila);
                }
            
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return tabla;
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
    public Boolean updateUsuario(String cedula, String nombre, String telefono, String direccion, 
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
    
    public boolean registrarClientes(String cedula, String nombre, String telefono,
                                  String direccion, String ciudad, String tipo) throws ParseException{
        int filasAfectadas = 0;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPago = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Date fecha =  formato.parse(fechaPago);
        Date fechaSQL = new java.sql.Date(fecha.getTime());
            try {

            PreparedStatement sql = conexion.prepareStatement("INSERT INTO clientes VALUES(?,?,?,?,?,?::tipo_cliente,?)");

            sql.setString(1, cedula);
            sql.setString(2, nombre);
            sql.setString(3, telefono);
            sql.setString(4, direccion);
            sql.setString(5, ciudad);
            sql.setString(6, tipo);
            sql.setDate(7, (java.sql.Date) fechaSQL);

            filasAfectadas = sql.executeUpdate();  // ejecutar la sentencia.
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        
        return filasAfectadas != 0;
    }
    
    public boolean validarCliente(String cedula){ //Metodo que valida si un cliente ya se encuentra registrado
        String validarC = "";
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT cedula FROM clientes WHERE cedula = ?");
            sql.setString(1, cedula);
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                validarC = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return validarC.equals(cedula);
    }
    
    public boolean eliminarCliente(String cedula){
        int filasAfectadas = 0;
            try {

            PreparedStatement sql = conexion.prepareStatement("DELETE from clientes where cedula=?");

            sql.setString(1, cedula);

            filasAfectadas = sql.executeUpdate();  // ejecutar la sentencia.
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return filasAfectadas != 0;
    }
    
    public Boolean updateCliente(String cedula, String nombre, String telefono, String direccion, 
                            String ciudad) {
       try {
            PreparedStatement sql = conexion.prepareStatement("UPDATE clientes SET nombre = ?, telefono = ?, direccion = ?, ciudad = ? WHERE cedula = ?"                                                           
                                                               );
            sql.setString(1, nombre);
            sql.setString(2, telefono);
            sql.setString(3, direccion);
            sql.setString(4, ciudad);
            sql.setString(5, cedula);
            
            int rs = sql.executeUpdate();
            
            return rs != 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
   }
    
    public Cliente getCliente(String cedula) {
        Cliente cliente = null;
        
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM clientes WHERE cedula = ?");
            sql.setString(1, cedula);
            
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                cliente = new Cliente(rs.getString(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getString(4),
                                        rs.getString(5),
                                        TipoCliente.valueOf(rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cliente;
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
    /*
    public ArrayList<Plan> getPlanes() {
    	ArrayList<Plan> planes = new ArrayList<>(); // para guardar los datos en un array.
        
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM planes order by identificador");
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            
            while (rs.next()){ // guardar los datos en la lista de usuarios.
                Plan unPlan = new Plan(rs.getInt(1),
                                                rs.getString(2),
                                                rs.getString(3),
                                                rs.getInt(4),
                                                rs.getInt(5),
                                                rs.getInt(6),
                                                rs.getInt(7));
                planes.add(unPlan);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return planes;   
    }*/
    
    public boolean registrarCuenta(String cedula_titular, String numero){
        String estado="activo";
        int filasAfectadas = 0;
            try {

            PreparedStatement sql = conexion.prepareStatement("INSERT INTO cuentas (cedula_titular, numero, estado) VALUES(?,?,?::status)");

            sql.setString(1, cedula_titular);
            sql.setString(2, numero);
            sql.setString(3, estado);

            filasAfectadas = sql.executeUpdate();  // ejecutar la sentencia.
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return filasAfectadas != 0;
    }
    
    public ArrayList<Cuenta> getCuentas(String cedula_titular) {
    	ArrayList<Cuenta> cuentas = new ArrayList<>(); // para guardar los datos en un array
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM cuentas WHERE cedula_titular = ?");
            sql.setString(1, cedula_titular);
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            while (rs.next()){ // guardar los datos en la lista de usuarios.
                Cuenta unaCuenta = new Cuenta(  rs.getInt(1),
                                                rs.getString(2),
                                                rs.getString(3),
                                                rs.getString(4),
                                                rs.getString(5));
                cuentas.add(unaCuenta);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return cuentas;   
    }
    
   
    
    public boolean registrarLinea(String numero, int idPlan){
        int filasAfectadas = 0;
            try {

            PreparedStatement sql = conexion.prepareStatement("INSERT INTO lineas VALUES(?,?,0,0,0)");

            sql.setString(1, numero);
            sql.setInt(2, idPlan);

            filasAfectadas = sql.executeUpdate();  // ejecutar la sentencia.
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return filasAfectadas != 0;
    }
    
     public boolean eliminarLinea(String numero){
        int filasAfectadas = 0;
            try {

            PreparedStatement sql = conexion.prepareStatement("DELETE from lineas where numero =?");
            sql.setString(1, numero);
            filasAfectadas = sql.executeUpdate();  // ejecutar la sentencia.
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return filasAfectadas != 0;
    }
     
    public Boolean updateLinea(String numeroViejo, int plan, String numeroNuevo) { //Metodo que actualiza los datos de un cliente en la base segun su indentificacion(cedula)
        try {
            PreparedStatement sql = conexion.prepareStatement("UPDATE lineas SET numero = ?, plan = ? WHERE numero = ?");
            sql.setString(1, numeroNuevo);
            sql.setInt(2, plan);
            sql.setString(3, numeroViejo);
            
            int rs = sql.executeUpdate();
            
            return rs != 0;
            
        } catch (SQLException ex) {
            System.out.println(ex);
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

            PreparedStatement sql = conexion.prepareStatement("select valor_pagado from facturas where numero_factura = ?");

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
     * metodo, consulta si una factura existe, en la base de datos
     * @param numeroFactura
     * @return Boolean, retorna true si existe la factura, en que caso de no encontrar una factura, retorna false
     */
    public boolean consultarFactura(Integer numeroFactura) { 
        boolean filasAfectadas = false;
        
         try {

            PreparedStatement sql = conexion.prepareStatement("select * from facturas where numero_factura = ?");

            sql.setInt(1, numeroFactura);
 

            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                System.out.println("No funciona");
            }
        
        return false;
    }
    
    
    /**
     * metodo, consulta si un nombre de usuario existe, en la base de datos
     * @param nombre_usuario
     * @return Boolean, retorna true si existe la factura, en que caso de no encontrar una factura, retorna false
     */
    public boolean consultarNombreUsuario(String nombre_usuario) { 
        boolean filasAfectadas = false;
        
         try {

            PreparedStatement sql = conexion.prepareStatement("select * from usuarios where nombre_usuario = ?");

            sql.setString(1, nombre_usuario);
 

            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                System.out.println("No funciona");
            }
        
        return false;
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
    public void updateValorPagoCuentaM(String ruta) {
       
                
        String identificador="";
        String numero ;
        String pago;
        
        ArrayList<String> datos = new ArrayList<>();
        datos = pb.leerDeArchivo(ruta);
        
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
    public void updateUltimoPagoCuentaM(String ruta) {


        String fechaPago ;
        String numero ;

        ArrayList<String> datos = new ArrayList<>();
        datos = pb.leerDeArchivo(ruta);

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
     
    public Linea getLinea(String numero) {
        Linea linea = null;
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM lineas WHERE numero = ?");
            sql.setString(1, numero);
            
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                linea = new Linea(rs.getString(1),
                                        rs.getInt(2),
                                        rs.getInt(3),
                                        rs.getInt(4),
                                        rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return linea;
    }
    
    public ArrayList<Linea> getLineas(Cliente cliente) {//Metodo que obtiene todas las lineas asociadas a un cliente
    	ArrayList<Linea> lineas = new ArrayList<>(); // para guardar los datos en un array.
        
        try {
            PreparedStatement sql = conexion.prepareStatement("select l.numero, l.plan, l.minutos_usados, l.megas_usadas, l.mensajes_enviados \n" +
                                                                "from lineas l, cuentas c where c.numero = l.numero and c.cedula_titular = ?");
            sql.setString(1, cliente.getCedula());
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            while (rs.next()){ // guardar los datos en la lista de usuarios.
                Linea unaLinea = new Linea(rs.getString(1),
                                                rs.getInt(2),
                                                rs.getInt(3),
                                                rs.getInt(4),
                                                rs.getInt(5));
                lineas.add(unaLinea);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lineas;   
    }
    
    public boolean validarLinea(String numero){ //Metodo que valida si un numero ya esta en uso
        String validarL = "";
        try {
            PreparedStatement sql = conexion.prepareStatement("SELECT numero FROM lineas WHERE numero = ?");
            sql.setString(1, numero);
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                validarL = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return validarL.equals(numero);
    }

    public boolean cambiarEstadoPlan(String numero, Boolean activar) {
        try {
            PreparedStatement sql = conexion.prepareStatement("UPDATE cuentas SET estado = ?::status WHERE numero = ?");
            
            String nuevoEstado = activar ? "activo" : "inactivo";
            sql.setString(1, nuevoEstado);
            sql.setString(2, numero);
            
            int rs = sql.executeUpdate();
            
            return rs != 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    public int consultarTotalPagos(String mes, String tipo){//Metodo que recibe un mes y un tipo de cliente para retornar el total de pagos recibidos por ese tipo de clientes en ese mes
        int resultado = 0;
        try {
            PreparedStatement sql = conexion.prepareStatement("select SUM(f.valor_pagado) from facturas f, cuentas cu, clientes cl\n" +
                                                                "where cl.tipo = ?::tipo_cliente and cl.cedula = cu.cedula_titular \n" +
                                                                "and f.numero_cuenta = cu.identificador and to_char(f.fecha_limite_pago, 'FMMonth') = ?");
            sql.setString(1, tipo);
            sql.setString(2, mes);
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                resultado = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    public int consultarTotalNumerosPlan(int plan){
        int resultado = 0;
        try {
            PreparedStatement sql = conexion.prepareStatement("select count(l.numero) from lineas l, cuentas c  where plan = ? and l.numero = c.numero and c.estado ='activo';");
            sql.setInt(1, plan);
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                resultado = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    public ArrayList<String> obtenerCiudades(){
        String ciudad = " ";
        ArrayList<String> ciudades = new ArrayList<>();
        try {
            PreparedStatement sql = conexion.prepareStatement("select distinct UPPER(ciudad) from clientes");
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            while (rs.next()){ // guardar los datos en la lista de usuarios.
                ciudad = rs.getString(1);
                ciudades.add(ciudad);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ciudades;
    }
    
    public int consultarTotalClientesCiudad(String ciudad){
        int resultado = 0;
        try {
            PreparedStatement sql = conexion.prepareStatement("select count(cedula) from clientes c  where upper(ciudad) =?;");
            sql.setString(1, ciudad);
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                resultado = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    public int consultarClientesNuevos(String mes){
        int resultado = 0;
        try {
            PreparedStatement sql = conexion.prepareStatement("select count(cedula) from clientes c where to_char(c.fecha_registro, 'FMMonth') = ? ; ");
            sql.setString(1, mes);
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                resultado = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    public int consultarClientesViejos(String mes){
        int resultado = 0;
        try {
            PreparedStatement sql = conexion.prepareStatement("select count(c.cedula)  from clientes c where c.fecha_registro < ?::date;");
            sql.setString(1, mes);
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                resultado = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    public boolean consultarUsername(String username) { // Si retorna true es porque existe
        boolean resultado = false;
        try {
            PreparedStatement sql = conexion.prepareStatement("select * from usuarios where nombre_usuario = ?; ");
            sql.setString(1, username);
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            if (rs.next()) {
                resultado = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    /**
    * Lee los datos del archivo de consumos de los clientes
    */
    public ArrayList<String> leerDeArchivoConsumo(String ruta){
        
         ArrayList<String> lecturaLinea = new ArrayList<>();
       
        try{
            BufferedReader bf = new BufferedReader(new FileReader(ruta));
            String read;
            while((read = bf.readLine()) != null){
                StringTokenizer tokens=new StringTokenizer(read);
                while(tokens.hasMoreTokens()){
                    
                 lecturaLinea.add(tokens.nextToken());                 
                 
                } 
            }
             
        }catch(Exception e){
            System.out.print("No se encontro archivo");
        }
        return lecturaLinea;
    }
    
     /**
    * Carga el consumo maxivo de los clientes a la base de datos
    */
    public void updateConsumoClientes(String ruta) {

        String numero;
        String minutosUsados;
        String megasUsadas;
        String mensajesEnviados;

        ArrayList<String> datos = new ArrayList<>();
        datos = leerDeArchivoConsumo(ruta);

        int numeroL=0;
        int minutos=1;
        int megas=2;
        int mensajes=3;
        int iteracciones = datos.size()/4;

        for(int i = 0; i<iteracciones; i++){



           numero = datos.get(numeroL);
           minutosUsados = datos.get(minutos);
           megasUsadas = datos.get(megas);
           mensajesEnviados = datos.get(mensajes);
           //System.out.println(numero +"\n");
           //System.out.println(+"\n");
            try {
            PreparedStatement sql = conexion.prepareStatement("UPDATE lineas SET minutos_usados = ?::int, megas_usadas = ?::int, mensajes_enviados = ?::int WHERE numero = ?::bpchar");
            sql.setString(1, minutosUsados );
            sql.setString(2, megasUsadas );
            sql.setString(3, mensajesEnviados);
            sql.setString(4, numero);

            int rs = sql.executeUpdate();  // ejecutar la sentencia.
            System.out.println("Se han actualizado: "+rs+" filas.\n");
            if (rs == 0) { // no se afecto ninguna fila.
                //return false; // se debe mostrar un mensaje de error o algo.
             }

            } catch (SQLException ex) {
            System.out.println("no funciona upDate");
            }
         numeroL=numeroL+4;
         minutos=minutos+4;
         megas=megas+4;
         mensajes=mensajes+4;
       } 
   }
    
    /**
     * Metodo utilizado para obtener los datos de todos los planes que se encuentra en el sistema.
     * @param id
     * @return ArrayList, un arreglo que contiene los planes con sus datos.
     */
    public ArrayList<Integer> facturasFechaLimitePagada() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Date fecha =  formato.parse(fechaActual);
        Date fechaSQL = new java.sql.Date(fecha.getTime());

        ArrayList<Integer> cuentas = new ArrayList();      
        try {
            PreparedStatement sql = conexion.prepareStatement("select distinct numero_cuenta from facturas f, cuentas c where c.estado = 'activo' and c.identificador = f.numero_cuenta and f.fecha_limite_pago <= ? and f.valor_pagado is not Null");
            sql.setDate(1, (java.sql.Date) fechaSQL);
            
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            while (rs.next()) {
                cuentas.add(rs.getInt(1));               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cuentas;
    }
    
    
    
    /**
     * Metodo utilizado para obtener los datos de todos los planes que se encuentra en el sistema.
     * @param id
     * @return ArrayList, un arreglo que contiene los planes con sus datos.
     */
    public ArrayList<Integer> facturasFechaLimiteNoPagada() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Date fecha =  formato.parse(fechaActual);
        Date fechaSQL = new java.sql.Date(fecha.getTime());

        ArrayList<Integer> cuentas = new ArrayList();      
        try {
            PreparedStatement sql = conexion.prepareStatement("select distinct numero_cuenta from facturas f, cuentas c where c.estado = 'activo' and c.identificador = f.numero_cuenta and f.fecha_limite_pago <= ? and f.valor_pagado is Null");
            sql.setDate(1, (java.sql.Date) fechaSQL);
            
            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
            while (rs.next()) {
                cuentas.add(rs.getInt(1));               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cuentas;
    }
    
        /**
     * metodo, consulta el valor de un pago realizado, en la base de datos
     * @param numeroFactura
     * @return Integer, retorna el valor que se pagó, en que caso de no encontrar un pago, retorna 0
     */
    public Integer consultarPrecioPlan(Integer numeroCuenta) { 
        int filasAfectadas = 0;
        
         try {

            PreparedStatement sql = conexion.prepareStatement("select precio from planes where identificador = (select plan from lineas where numero = (select numero from cuentas where identificador = ?))");

            sql.setInt(1, numeroCuenta);
 

            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("No funciona");
            }
        
        return 0;
    }
    
    
    public Integer consultarMinutosUsados(Integer numeroCuenta) { 
        int filasAfectadas = 0;
        
         try {

            PreparedStatement sql = conexion.prepareStatement("select minutos_usados from lineas where numero = (select numero from cuentas where identificador = ?)");

            sql.setInt(1, numeroCuenta);
 

            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("No funciona");
            }
        
        return 0;
    }
    
    
    public Integer consultarMinutosPlan(Integer numeroCuenta) { 
        int filasAfectadas = 0;
        
         try {

            PreparedStatement sql = conexion.prepareStatement("select precio from planes where identificador = (select plan from lineas where numero = (select numero from cuentas where identificador = ?))");

            sql.setInt(1, numeroCuenta);
 

            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("No funciona");
            }
        
        return 0;
    }
    
    
    public boolean registrarFactura(Integer numeroCuenta, Integer ValorPagar) throws ParseException{
        int filasAfectadas = 0;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPago = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Date fecha =  formato.parse(fechaPago);
        Date fechaSQL = new java.sql.Date(fecha.getTime());
            try {

            PreparedStatement sql = conexion.prepareStatement("INSERT INTO public.facturas(numero_cuenta, valor_a_pagar, fecha_generada, fecha_limite_pago) VALUES (?, ?, ?, '2022-04-16')");

            sql.setInt(1, numeroCuenta);
            sql.setInt(2, ValorPagar);
            sql.setDate(3, (java.sql.Date) fechaSQL);

            filasAfectadas = sql.executeUpdate();  // ejecutar la sentencia.
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        
        return filasAfectadas != 0;
    }
    
    
    public Integer consultarDeudaAnterior(Integer numeroCuenta) throws ParseException { 
        int filasAfectadas = 0;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPago = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Date fecha =  formato.parse(fechaPago);
        Date fechaSQL = new java.sql.Date(fecha.getTime());
        
         try {

            PreparedStatement sql = conexion.prepareStatement("select distinct valor_a_pagar from facturas f, cuentas c where numero_cuenta = ? and c.estado = 'activo' and c.identificador = f.numero_cuenta and f.fecha_limite_pago <= ? and f.valor_pagado is Null");

            sql.setInt(1, numeroCuenta);
            sql.setDate(2, (java.sql.Date) fechaSQL);
 

            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("No funciona");
            }
        
        return 0;
    }
    
    public Integer consultarFacturaAnterior(Integer numeroCuenta) throws ParseException { 
        int filasAfectadas = 0;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPago = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Date fecha =  formato.parse(fechaPago);
        Date fechaSQL = new java.sql.Date(fecha.getTime());
        
         try {

            PreparedStatement sql = conexion.prepareStatement("select distinct numero_factura from facturas f, cuentas c where numero_cuenta = ? and c.estado = 'activo' and c.identificador = f.numero_cuenta and f.fecha_limite_pago <= ? and f.valor_pagado is Null");

            sql.setInt(1, numeroCuenta);
            sql.setDate(2, (java.sql.Date) fechaSQL);
 

            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("No funciona");
            }
        
        return 0;
    }
    
    
    public Integer consultarFacturaCuenta(Integer numeroCuenta) throws ParseException { 
        int filasAfectadas = 0;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPago = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Date fecha =  formato.parse(fechaPago);
        Date fechaSQL = new java.sql.Date(fecha.getTime());
        
         try {

            PreparedStatement sql = conexion.prepareStatement("select distinct numero_factura from facturas f, cuentas c where numero_cuenta = ? and c.estado = 'activo' and c.identificador = f.numero_cuenta and f.fecha_limite_pago <= ? and f.valor_pagado is not Null");

            sql.setInt(1, numeroCuenta);
            sql.setDate(2, (java.sql.Date) fechaSQL);
 

            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("No funciona");
            }
        
        return 0;
    }
    
    
    public Integer consultarUltimaFactura(Integer numeroCuenta) { 
        int filasAfectadas = 0;

        
         try {

            PreparedStatement sql = conexion.prepareStatement("select numero_factura from facturas where numero_factura = (select max(numero_factura) from facturas where numero_cuenta = ?)");

            sql.setInt(1, numeroCuenta);
 

            ResultSet rs = sql.executeQuery();  // ejecutar la sentencia.
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("No funciona");
            }
        
        return 0;
    }
    
    
}
