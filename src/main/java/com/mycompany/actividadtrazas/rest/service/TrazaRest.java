/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.rest.service;

import com.mycompany.actividadtrazas.beans.TrazaBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daniel
 */
@Path("traza")
public class TrazaRest {
    
    @Inject
    private TrazaBean bean;
    
    @GET
    @Path("lista")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getTrazaListado(){
        //Inicializa tiempo de actividad
        return Response.ok(bean.getTrazas(), MediaType.APPLICATION_JSON).build();
    }
}
