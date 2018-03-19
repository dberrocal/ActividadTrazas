/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.entity;

/**
 *
 * @author Daniel
 */
public class RespuestaActividad {
    
    private Long pregunta;
    private String respuesta;
    private Boolean valido;
    
    public void setPregunta(Long pregunta) {
        this.pregunta = pregunta;
    }

    public Long getPregunta() {
        return pregunta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setValido(Boolean valido) {
        this.valido = valido;
    }

    public Boolean getValido() {
        return valido;
    }               
}
