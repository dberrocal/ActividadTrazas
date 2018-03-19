/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.beans;

import com.mycompany.actividadtrazas.entity.Usuario;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Daniel
 */
@Named
@RequestScoped
public class UsuarioBean {
    
    public Boolean validarDocumento(String documento){
        return true;
    }
    
    public void Persist(Usuario usuario){
    }
}
