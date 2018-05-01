/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.rest.service;

import com.mycompany.actividadtrazas.beans.UsuarioBean;
import com.mycompany.actividadtrazas.entity.Usuario;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daniel
 */
@Path("usuario")
public class UsuarioRest {
    
    @Inject
    private UsuarioBean bean;
    
    @POST
    @Path("usuarios")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response Guardarusuario(Usuario usuario){
        bean.Persist(usuario);
        //GenericEntity<Usuario> entity = new GenericEntity<Usuario>() {};
        return Response.ok("{\"usuario\":\"ok\"}", MediaType.APPLICATION_JSON).build();
    }        
    
    @POST
    @Path("validar")
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    //@Produces(value = MediaType.APPLICATION_JSON)
    public Response ValidarUsuario(@FormParam("documento") String documento){                
        return Response.ok(bean.getUsuario(documento), MediaType.APPLICATION_JSON).build();
    }        
    
    
}
