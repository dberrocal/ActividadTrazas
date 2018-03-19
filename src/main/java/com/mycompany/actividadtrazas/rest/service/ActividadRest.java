/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.rest.service;

import com.mycompany.actividadtrazas.beans.ActividadBeans;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Daniel
 */
@Path("actividad")
public class ActividadRest {
    
    @Inject
    private ActividadBeans bean;
    
    @GET
    @Path("nombre")
    public String getNombre(){
        bean.getActividad();
        return "ok";
    }
}
