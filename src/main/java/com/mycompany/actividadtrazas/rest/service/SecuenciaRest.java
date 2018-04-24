/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.rest.service;

import com.mycompany.actividadtrazas.beans.SecuenciaBeans;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daniel
 */
@Path("secuencia")
public class SecuenciaRest {
    
    @Inject
    private SecuenciaBeans bean;
    
    @GET
    @Path("niveles")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getNivelListado(){
        return Response.ok(bean.getSecuencias().getJsonArray("nivel"), MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("actividad")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getSecuenciasListado(@QueryParam("Nivel") String nivel){
        return Response.ok(bean.getSecuencias(nivel).getJsonArray("actividad"), MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("actividadbyid/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getActividadesListado(@PathParam("id") Long id){
        //Inicializa tiempo de actividad
        return Response.ok(bean.getActividades(id), MediaType.APPLICATION_JSON).build();
    }
}
