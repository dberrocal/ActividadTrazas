/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.beans;

import com.mycompany.actividadtrazas.entity.Actividad;
import com.mycompany.actividadtrazas.entity.Pregunta;
import com.mycompany.actividadtrazas.entity.RespuestaActividad;
import com.mycompany.actividadtrazas.entity.TrazaTipo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel
 */
@Named
@ApplicationScoped
public class ActividadBeans implements Serializable{
    
    //@PersistenceUnit(name = "DB")
    //@PersistenceUnit(name = "DBRemoto")   
    //private EntityManagerFactory emf;
    @PersistenceContext(name = "DBRemoto")
    private EntityManager em;
    
    @Inject
    private TrazaBean tzean;        
    
    //Peguntas de actividad
    public JsonArray getPreguntas(Long actividadId, String documento, String session){
        
        //EntityManager em = emf.createEntityManager();        
        Actividad actividad = em.find(Actividad.class, actividadId);
        JsonArrayBuilder jarray = Json.createArrayBuilder();
        actividad.getPregunta().forEach((p)->{
            JsonObjectBuilder json_actividad = Json.createObjectBuilder();
            json_actividad.add("id", p.getId());
            json_actividad.add("descripcion", p.getDescripcion());
            json_actividad.add("respuesta", p.getRespuesta());
            jarray.add(json_actividad);
        });
        
        //Insertamos traza de inicio actividad
        tzean.TrazaTiempo(documento, session, actividadId, TrazaTipo.A10INITIEMPO);
        
        return jarray.build();
    }
        
    public JsonObject getActividades(String nv){
        
        //EntityManager em = emf.createEntityManager();        
        List<Actividad> actividad = null;
        if(nv == null)
            actividad = em.createNamedQuery(Actividad.TODOS,Actividad.class).getResultList();                        
        else
            actividad = em.createNamedQuery(Actividad.NIVELES,Actividad.class).setParameter("nivel", nv).getResultList();                        
        
        //List<String> listado = actividad.stream().map(Actividad::getNivel).distinct().collect(toList());
        JsonArrayBuilder jarray = Json.createArrayBuilder();
        actividad.stream().map(Actividad::getNivel).distinct().forEach((nivel)->{
            jarray.add(Json.createObjectBuilder().add("NivelID", nivel).add("Nivel", nivel));
        });
        
        //Map<String,List<Actividad>> listado = actividad.stream().collect(groupingBy(Actividad::getNivel));
        JsonObjectBuilder json = Json.createObjectBuilder();
                        
        json.add("nivel", jarray);
        
        JsonArrayBuilder j_array = Json.createArrayBuilder();
        actividad.forEach((a)->{
            JsonObjectBuilder json_actividad = Json.createObjectBuilder();
            json_actividad.add("ActividadID", a.getId());
            json_actividad.add("Descripcion", a.getDescripcion());
            json_actividad.add("Nivel", a.getNivel());
            j_array.add(json_actividad);
        });
        json.add("actividad", j_array);
        /*
        listado.forEach((a,b)->{
            json.add("nivel", a);            
            JsonArrayBuilder jsonarray = Json.createArrayBuilder();
            b.forEach(c->{
                JsonObjectBuilder json_actividad = Json.createObjectBuilder();
                json_actividad.add("id", c.getId());
                json_actividad.add("descripcion", c.getDescripcion());
                jsonarray.add(json_actividad);
            });
            json.add("actividad",jsonarray);
        });*/
        //actividad.stream().collect(Collectors.groupingBy(Actividad::getNivel,Collectors.counting())).
        return json.build();
    }
    
    public List<RespuestaActividad> Validar(String documento,String session,Long actividadId,List<RespuestaActividad> listado){
        //EntityManager em = emf.createEntityManager();                        
        for(RespuestaActividad r : listado){
            Pregunta p = em.find(Pregunta.class, r.getPregunta());
            r.setValido(p.getRespuesta().equalsIgnoreCase(r.getRespuesta()));                        
        }                
        tzean.TrazaIntento(documento, session, actividadId, listado);
        
        return listado;
    }           
}
