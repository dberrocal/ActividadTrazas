/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.beans;

import com.mycompany.actividadtrazas.entity.Actividad;
import com.mycompany.actividadtrazas.entity.Pregunta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Daniel
 */
@Named
@ApplicationScoped
public class ActividadBeans implements Serializable{
    
    @PersistenceUnit(name = "DB")
    //@PersistenceContext(name = "DB")
    private EntityManagerFactory emf;
    
    @PostConstruct
    public void init(){
        System.out.println("INIT");
        
        List<Actividad> activiades = new ArrayList<>();
        
        EntityManager em = emf.createEntityManager();        
        List<Pregunta> lista = new ArrayList<>();
        em.getTransaction().begin();
        for(Pregunta p : Arrays.asList(
                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","","") ,
                new Pregunta("Sandy ESPACIO (forget) her homework very often.","",""),
                new Pregunta("Sandy ESPACIO (forget) her homework very often.","",""),
                new Pregunta("Sandy ESPACIO (forget) her homework very often.","",""))){            
            //em.persist(p);
            lista.add(p);
        }
        Actividad actividad = new Actividad();
        actividad.setDescripcion("Act-01");
        actividad.setNivel("B1");
        actividad.setPregunta(lista);
        em.persist(actividad);
        
        Actividad actividad2 = new Actividad();
        actividad2.setDescripcion("Act-02");
        actividad2.setNivel("B2");
        actividad2.setPregunta(lista);
        em.persist(actividad2);
        em.getTransaction().commit();
    }
    
    public JsonArray getPreguntas(Long actividadId){
        EntityManager em = emf.createEntityManager();        
        Actividad actividad = em.find(Actividad.class, actividadId);
        JsonArrayBuilder jarray = Json.createArrayBuilder();
        actividad.getPregunta().forEach((p)->{
            JsonObjectBuilder json_actividad = Json.createObjectBuilder();
            json_actividad.add("id", p.getId());
            json_actividad.add("descripcion", p.getDescripcion());
            json_actividad.add("respuesta", p.getRespuesta());
            jarray.add(json_actividad);
        });
        return jarray.build();
    }
        
    public JsonObject getActividades(){
        
        EntityManager em = emf.createEntityManager();        
        List<Actividad> actividad = em.createNamedQuery(Actividad.NIVELES,Actividad.class).getResultList();
                
        System.out.println(actividad.size());
        
        List<String> listado = actividad.stream().map(Actividad::getNivel).distinct().collect(toList());
        
        //Map<String,List<Actividad>> listado = actividad.stream().collect(groupingBy(Actividad::getNivel));
        JsonObjectBuilder json = Json.createObjectBuilder();
        
        JsonArrayBuilder jarray = Json.createArrayBuilder();
        listado.forEach(jarray::add);
        json.add("nivel", jarray);
        
        JsonArrayBuilder j_array = Json.createArrayBuilder();
        actividad.forEach((a)->{
            JsonObjectBuilder json_actividad = Json.createObjectBuilder();
            json_actividad.add("id", a.getId());
            json_actividad.add("descripcion", a.getDescripcion());
            json_actividad.add("nivel", a.getNivel());
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
    
    public void Validar(Long actividad){
    }
}
