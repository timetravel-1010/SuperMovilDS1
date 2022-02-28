/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Arman
 */
public class Linea {
    
    private String numero;
    private int plan;
    private int minutos_usados;
    private int megas_usadas;
    private int mensajes_enviados;
    
    public Linea(String numero, int plan, int minutos_usados, int megas_usadas, int mensajes_enviados){
        this.numero = numero;
        this.plan = plan;
        this.minutos_usados = minutos_usados;
        this.megas_usadas = megas_usadas;
        this.mensajes_enviados = mensajes_enviados;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public int getMinutos_usados() {
        return minutos_usados;
    }

    public void setMinutos_usados(int minutos_usados) {
        this.minutos_usados = minutos_usados;
    }

    public int getMegas_usadas() {
        return megas_usadas;
    }

    public void setMegas_usadas(int megas_usadas) {
        this.megas_usadas = megas_usadas;
    }

    public int getMensajes_enviados() {
        return mensajes_enviados;
    }

    public void setMensajes_enviados(int mensajes_enviados) {
        this.mensajes_enviados = mensajes_enviados;
    }
    
    
}
