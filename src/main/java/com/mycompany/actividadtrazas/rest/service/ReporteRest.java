/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.rest.service;

import com.mycompany.actividadtrazas.beans.TrazaBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date fi = null,ff = null;
        
        try {
            fi = sdf.parse(fechainicio);
            ff = sdf.parse(fechafin);
        } catch (ParseException ex) {
            Logger.getLogger(ReporteRest.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return Response.ok(bean.getTrazasReporteTiempo(grupo,fi,ff), MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("numeroerrores")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getResporteNumeroError(@QueryParam("grupo") String grupo,
            @QueryParam("fi")String fechainicio,
            @QueryParam("ff")String fechafin){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date fi = null,ff = null;
        
        try {
            fi = sdf.parse(fechainicio);
            ff = sdf.parse(fechafin);
        } catch (ParseException ex) {
            Logger.getLogger(ReporteRest.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return Response.ok(bean.getTrazasReporteNumErrores(grupo,fi,ff), MediaType.APPLICATION_JSON).build();
    }
}
