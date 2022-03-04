/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Manuel
 */
public class Planes {
    private String identificador;
    private String nombre;
    private String descripcion;
    private String precio;
    private String minutos;
    private String megas;
    private String mensajes;
    
    public Planes(String identificador, String nombre, String descripcion, String precio, String minutos, String megas, String mensajes) {
        this.descripcion= identificador;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.minutos = minutos;
        this.megas = megas;
        this.mensajes = mensajes;  
    }
    

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
     public String getMinutos() {
        return minutos;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    public String getMegas() {
        return megas;
    }

    public void setMegas(String megas) {
        this.megas = megas;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }
    
}
