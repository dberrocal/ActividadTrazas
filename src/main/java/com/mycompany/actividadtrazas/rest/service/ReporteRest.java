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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daniel
 */
@Path("reporte")
public class ReporteRest {
    
    @Inject
    private TrazaBean bean;
    
    @GET
    @Path("tiempo")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getResporteTiempo(@QueryParam("grupo") String grupo,
            @QueryParam("fi")String fechainicio,
            @QueryParam("ff")String fechafin){
        return Response.ok(bean.getTrazas(), MediaType.APPLICATION_JSON).build();
    }
}
