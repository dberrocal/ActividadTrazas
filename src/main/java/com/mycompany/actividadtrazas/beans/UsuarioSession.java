/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Daniel
 */
@Named
@SessionScoped
public class UsuarioSession implements Serializable{
    
    private String rol;
    private String nombre;
    private String documento;
    private String grupo;

    public UsuarioSession() {
    }   
    
    public void setData(String rol, String nombre, String documento, String grupo) {
        this.rol = rol;
        this.nombre = nombre;
        this.documento = documento;
        this.grupo = grupo;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getRol() {
        return rol;
    }        
}