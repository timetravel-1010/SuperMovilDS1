/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Arman
 */
public class Cuenta {
    
    private int identificador;
    private String cedula_titular;
    private String numero;
    private String ultimo_pago;
    private String estado;
    
    public Cuenta (int identificador, String cedula_titular, String numero, String ultimo_pago, String estado){
        this.identificador = identificador;
        this.cedula_titular = cedula_titular;
        this.numero = numero;
        this.ultimo_pago = ultimo_pago;
        this.estado = estado;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getCedula_titular() {
        return cedula_titular;
    }

    public void setCedula_titular(String cedula_titular) {
        this.cedula_titular = cedula_titular;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUltimo_pago() {
        return ultimo_pago;
    }

    public void setUltimo_pago(String ultimo_pago) {
        this.ultimo_pago = ultimo_pago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
