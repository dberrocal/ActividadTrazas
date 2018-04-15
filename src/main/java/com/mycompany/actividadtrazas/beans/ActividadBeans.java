/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.beans;

import com.mycompany.actividadtrazas.entity.Actividad;
import com.mycompany.actividadtrazas.entity.Estudiante;
import com.mycompany.actividadtrazas.entity.Pregunta;
import com.mycompany.actividadtrazas.entity.RespuestaActividad;
import com.mycompany.actividadtrazas.entity.Secuencia;
import com.mycompany.actividadtrazas.entity.Traza;
import com.mycompany.actividadtrazas.entity.TrazaTipo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Daniel
 */
@Named
@ApplicationScoped
public class ActividadBeans implements Serializable{
    
    @PersistenceUnit(name = "DB")    
    private EntityManagerFactory emf;
    
    @Inject
    private TrazaBean tzean;
    
    @PostConstruct
    public void init(){
        System.out.println("INIT");
        
        Secuencia sc = new Secuencia();
        sc.setDescripcion("Actividad 001");
        sc.setNivel("A1");
        sc.setNumero(2);                
        
        EntityManager em = emf.createEntityManager();        
        List<Pregunta> lista = new ArrayList<>();
        em.getTransaction().begin();
        for(Pregunta p : Arrays.asList(
                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A","") ,
                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A",""),
                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A",""),
                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A",""))){            
            //em.persist(p);
            lista.add(p);
        }
        
        List<Actividad> activiades = new ArrayList<>();
        
        Actividad actividad = new Actividad();
        actividad.setDescripcion("Tarea-01");
        actividad.setNivel("B1");
        actividad.setPregunta(lista);
        actividad.setSequencia(sc);
        activiades.add(actividad);
        //em.persist(actividad);
        
        Actividad actividad2 = new Actividad();
        actividad2.setDescripcion("Tarea-02");
        actividad2.setNivel("B2");
        actividad2.setPregunta(lista);
        actividad2.setSequencia(sc);
        activiades.add(actividad2);
        
        sc.setActividades(activiades);
        
        em.persist(sc);
        
        em.getTransaction().commit();
        
        InsertarEstudiantes();
        InsertarTrazas();
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
        
    public JsonObject getActividades(String nv){
        
        EntityManager em = emf.createEntityManager();        
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
    
    public List<RespuestaActividad> Validar(Long id,List<RespuestaActividad> listado){
        EntityManager em = emf.createEntityManager();                        
        for(RespuestaActividad r : listado){
            Pregunta p = em.find(Pregunta.class, r.getPregunta());
            r.setValido(p.getRespuesta().equalsIgnoreCase(r.getRespuesta()));                        
        }        
        
        tzean.TrazaIntento("10000", 1L, listado);
        
        return listado;
    }
    
    private void InsertarEstudiantes(){
        EntityManager em = emf.createEntityManager();                        
        List<Estudiante> lista = Arrays.asList(
                new Estudiante("A101", "Estudiante A101", "A1"),
                new Estudiante("A102", "Estudiante A102", "A1"),
                new Estudiante("A103", "Estudiante A103", "A1"),
                new Estudiante("A104", "Estudiante A104", "A1"),
                new Estudiante("A105", "Estudiante A105", "A1"),
                new Estudiante("A106", "Estudiante A106", "A1"),
                new Estudiante("A107", "Estudiante A107", "A1"),
                new Estudiante("B101", "Estudiante B101", "B1"),
                new Estudiante("B102", "Estudiante B102", "B1"),
                new Estudiante("B103", "Estudiante B103", "B1")
        );
                
        em.getTransaction().begin();
        for(Estudiante s: lista){
            em.persist(s);
        }
        
        em.getTransaction().commit();
        
    }
    
    private void InsertarTrazas(){
                
        EntityManager em = emf.createEntityManager();
        
        List<Secuencia> secuencias = em.createNamedQuery(Secuencia.TODOS).getResultList();
        List<Estudiante> estudiantes = em.createNamedQuery(Estudiante.TODOS).getResultList();
        
        
        em.getTransaction().begin();
        for(Estudiante estudiante:estudiantes){
            for(Secuencia secuencia:secuencias){
                for(Actividad actividad:secuencia.getActividades()){
                    Calendar fecha = Calendar.getInstance();
                    System.out.println(estudiante.getDocumento());
                    Long tx = new Random().nextLong();
                    
                    Traza trx = new Traza(tx.toString(),estudiante.getDocumento(), actividad.getId(), TrazaTipo.A01INIACT.toString());
                    fecha.add(Calendar.MINUTE, -10);
                    trx.setFecha(fecha.getTime());
                    
                    em.persist(trx);            
                    /*try {
                        Thread.sleep(new Random().nextInt(5000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ActividadBeans.class.getName()).log(Level.SEVERE, null, ex);
                    }                */        
                    Traza trx01 = new Traza(tx.toString(),estudiante.getDocumento(), actividad.getId(), TrazaTipo.A02INTACT.toString());
                    fecha.add(Calendar.MINUTE, 1);
                    trx01.setFecha(fecha.getTime());
                    em.persist(trx01);
                    /*try {
                        Thread.sleep(new Random().nextInt(5000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ActividadBeans.class.getName()).log(Level.SEVERE, null, ex);
                    }*/                       
                    Traza trx02 = new Traza(tx.toString(),estudiante.getDocumento(), actividad.getId(), TrazaTipo.A02INTACT.toString());
                    fecha.add(Calendar.MINUTE, 1);
                    trx02.setFecha(fecha.getTime());
                    em.persist(trx02);
                    /*try {
                        Thread.sleep(new Random().nextInt(5000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ActividadBeans.class.getName()).log(Level.SEVERE, null, ex);
                    }*/                        
                    Traza trx03 = new Traza(tx.toString(),estudiante.getDocumento(), actividad.getId(), TrazaTipo.A03OKACT.toString());
                    fecha.add(Calendar.MINUTE, 20);
                    trx03.setFecha(fecha.getTime());                    
                    em.persist(trx03);
            
                    
                }
            }            
        }
        em.getTransaction().commit();                                
    }
}
