/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.rest.service;

import com.mycompany.actividadtrazas.beans.ActividadBeans;
import com.mycompany.actividadtrazas.entity.RespuestaActividad;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author Daniel
 */
@Path("actividad")
public class ActividadRest {
    
    @Inject
    private ActividadBeans bean;        
    
    @GET
    @Path("nivel")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getNivelListado(){
        return Response.ok(bean.getActividades(null).getJsonArray("nivel"), MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("actividad")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getActividadListado(@QueryParam("Nivel") String nivel){
        return Response.ok(bean.getActividades(nivel).getJsonArray("actividad"), MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("actividadByID/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getActividadPreguntaListado(@PathParam("id") Long id){
        return Response.ok(bean.getPreguntas(id), MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("validar/{actividad}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getActividadValidar(@PathParam("actividad") Long actividad, List<RespuestaActividad> listado){    
        GenericEntity<List<RespuestaActividad>> entity = new GenericEntity<List<RespuestaActividad>>(bean.Validar(actividad, listado)) {};
        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }
    
    
}
