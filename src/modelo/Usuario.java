package modelo;

/**
 *
 * @author pc
 */
public class Usuario {
    
    private String cedula;
    private String nombre;
    private String telefono;
    private String direccion;
    private String nombre_usuario;
    private String passwordd;
    private String estado;
    private String rol;
    private String status_login;

    public Usuario(String cedula, String nombre, String telefono, String direccion, String nombre_usuario, String passwordd, String estado, String rol, String status_login) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nombre_usuario = nombre_usuario;
        this.passwordd = passwordd;
        this.estado = estado;
        this.rol = rol;
        this.status_login = status_login;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPasswordd() {
        return passwordd;
    }

    public void setPasswordd(String passwordd) {
        this.passwordd = passwordd;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getStatus_login() {
        return status_login;
    }

    public void setStatus_login(String status_login) {
        this.status_login = status_login;
    }
}
