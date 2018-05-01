/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.beans;

import com.mycompany.actividadtrazas.entity.Actividad;
import com.mycompany.actividadtrazas.entity.Pregunta;
import com.mycompany.actividadtrazas.entity.Secuencia;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Daniel
 */
@Named
@ApplicationScoped
public class SecuenciaBeans implements Serializable {

    @PersistenceUnit(name = "DB")
    private EntityManagerFactory emf;
    
    @Inject
    @PostgresqlSQLDatabase
    private EntityManager em;

    
    private void init() {
        EntityManager em = emf.createEntityManager();
        
        List<Secuencia> seq = Arrays.asList(
                new Secuencia("Secuencia 01", "A1",
                        Arrays.asList(
                                new Actividad("actividad 101",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                                )),
                                new Actividad("actividad 102",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                        )),
                                new Actividad("actividad 103",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                        )),
                                new Actividad("actividad 104",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                        ))
                        )),
                new Secuencia("Secuencia 02", "A1",
                        Arrays.asList(
                                new Actividad("actividad 201",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                )),
                                new Actividad("actividad 202",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                        )),
                                new Actividad("actividad 203",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                        )),
                                new Actividad("actividad 204",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                        ))
                        )),
                new Secuencia("Secuencia 03", "A2",
                        Arrays.asList(
                                new Actividad("actividad 301",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                )),
                                new Actividad("actividad 302",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                        )),
                                new Actividad("actividad 303",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                        )),
                                new Actividad("actividad 304",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                        ))
                        )),
                new Secuencia("Secuencia 04", "A2",
                        Arrays.asList(
                                new Actividad("actividad 401",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                ))
                        )),
                new Secuencia("Secuencia 05", "B1",
                        Arrays.asList(
                                new Actividad("actividad 501",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                )),
                                new Actividad("actividad 502",
                                        Arrays.asList(
                                                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                                                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
                                ))
                        ))
        );
        
        em.getTransaction().begin();
        for(Secuencia secuencia: seq){
            em.persist(secuencia);
        }
        em.getTransaction().commit();
    }

    //Secuencias niveles
    public JsonObject getSecuencias() {

        //EntityManager em = emf.createEntityManager();

        List<Secuencia> secuencia = em.createNamedQuery(Secuencia.TODOS, Secuencia.class).getResultList();

        //List<String> listado = actividad.stream().map(Actividad::getNivel).distinct().collect(toList());
        JsonArrayBuilder jarray = Json.createArrayBuilder();
        secuencia.stream().map(Secuencia::getNivel).distinct().forEach((nivel) -> {            
            jarray.add(Json.createObjectBuilder().add("NivelID", nivel).add("Nivel", nivel));
        });

        //Map<String,List<Actividad>> listado = actividad.stream().collect(groupingBy(Actividad::getNivel));
        JsonObjectBuilder json = Json.createObjectBuilder();

        json.add("nivel", jarray);

        JsonArrayBuilder j_array = Json.createArrayBuilder();
        secuencia.forEach((a) -> {
            JsonObjectBuilder json_actividad = Json.createObjectBuilder();
            json_actividad.add("ActividadID", a.getId());
            json_actividad.add("Descripcion", a.getDescripcion());
            json_actividad.add("Nivel", a.getNivel());
            j_array.add(json_actividad);
        });
        json.add("actividad", j_array);
        return json.build();
    }

    //Secuencias por niveles
    public JsonObject getSecuencias(String nv) {

        //EntityManager em = emf.createEntityManager();

        List<Secuencia> secuencia = em.createNamedQuery(Secuencia.NIVEL, Secuencia.class).setParameter("nivel", nv).getResultList();

        //List<String> listado = actividad.stream().map(Actividad::getNivel).distinct().collect(toList());
        JsonArrayBuilder jarray = Json.createArrayBuilder();
        secuencia.stream().map(Secuencia::getNivel).distinct().forEach((nivel) -> {
            jarray.add(Json.createObjectBuilder().add("NivelID", nivel).add("Nivel", nivel));
        });

        //Map<String,List<Actividad>> listado = actividad.stream().collect(groupingBy(Actividad::getNivel));
        JsonObjectBuilder json = Json.createObjectBuilder();

        json.add("nivel", jarray);

        JsonArrayBuilder j_array = Json.createArrayBuilder();
        secuencia.forEach((a) -> {
            JsonObjectBuilder json_actividad = Json.createObjectBuilder();
            json_actividad.add("ActividadID", a.getId());
            json_actividad.add("Descripcion", a.getDescripcion());
            json_actividad.add("Nivel", a.getNivel());
            j_array.add(json_actividad);
        });
        json.add("actividad", j_array);
        return json.build();
    }

    public JsonObject getActividades(Long secuenciaId) {
        //EntityManager em = emf.createEntityManager();

        Secuencia secuencia = em.find(Secuencia.class, secuenciaId);

        List<Actividad> actividades = secuencia.getActividades();

        JsonObjectBuilder json = Json.createObjectBuilder();

        JsonArrayBuilder j_array = Json.createArrayBuilder();
        for (Actividad actividad : actividades) {
            JsonObjectBuilder json_actividad = Json.createObjectBuilder();
            json_actividad.add("actividad", actividad.getId());
            //json_actividad.add("Descripcion", a.getDescripcion());
            //json_actividad.add("Nivel", a.getNivel());
            j_array.add(json_actividad);
        }
        json.add("actividad", j_array);
        return json.build();
    }
}
