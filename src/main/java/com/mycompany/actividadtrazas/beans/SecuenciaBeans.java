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

    @PostConstruct
    private void init() {
        EntityManager em = emf.createEntityManager();
        
        List<Secuencia> seq = Arrays.asList(
                new Secuencia("Secuencia 01", "A1",
                        Arrays.asList(
                                new Actividad("actividad 101",
                                        Arrays.asList(
                                                new Pregunta("p0110101", "R0110101"),
                                                new Pregunta("p0110102", "R0110102"),
                                                new Pregunta("p0110103", "R0110103"),
                                                new Pregunta("p0110104", "R0110104"),
                                                new Pregunta("p0110105", "R0110105"),
                                                new Pregunta("p0110106", "R0110106"))),
                                new Actividad("actividad 102",
                                        Arrays.asList(
                                                new Pregunta("p0110201", "R0110201"),
                                                new Pregunta("p0110202", "R0110202"),
                                                new Pregunta("p0110203", "R0110203")
                                        )),
                                new Actividad("actividad 103",
                                        Arrays.asList(
                                                new Pregunta("p0110301", "R0110301"),
                                                new Pregunta("p0110302", "R0110302"),
                                                new Pregunta("p0110303", "R0110303"),
                                                new Pregunta("p0110304", "R0110304"),
                                                new Pregunta("p0110305", "R0110305")
                                        )),
                                new Actividad("actividad 104",
                                        Arrays.asList(
                                                new Pregunta("p0110401", "R0110401"),
                                                new Pregunta("p0110402", "R0110402"),
                                                new Pregunta("p0110403", "R0110403"),
                                                new Pregunta("p0110404", "R0110404"),
                                                new Pregunta("p0110405", "R0110405")
                                        ))
                        )),
                new Secuencia("Secuencia 02", "A1",
                        Arrays.asList(
                                new Actividad("actividad 201",
                                        Arrays.asList(
                                                new Pregunta("p0210101", "R0210101"),
                                                new Pregunta("p0210102", "R0210102")
                                )),
                                new Actividad("actividad 202",
                                        Arrays.asList(
                                                new Pregunta("p0120201", "R0110201"),
                                                new Pregunta("p0120202", "R0110202")                                                
                                        )),
                                new Actividad("actividad 203",
                                        Arrays.asList(
                                                new Pregunta("p0120301", "R0110301"),
                                                new Pregunta("p0120302", "R0110302"),
                                                new Pregunta("p0120303", "R0110303")
                                        )),
                                new Actividad("actividad 204",
                                        Arrays.asList(
                                                new Pregunta("p0120401", "R0110401"),
                                                new Pregunta("p0120402", "R0110402"),
                                                new Pregunta("p0120403", "R0110403"),
                                                new Pregunta("p0120404", "R0110404"),
                                                new Pregunta("p0120405", "R0110405")
                                        ))
                        )),
                new Secuencia("Secuencia 03", "A2",
                        Arrays.asList(
                                new Actividad("actividad 301",
                                        Arrays.asList(
                                                new Pregunta("p0230101", "R0230101"),
                                                new Pregunta("p0230102", "R0230102")
                                )),
                                new Actividad("actividad 302",
                                        Arrays.asList(
                                                new Pregunta("p0130201", "R0130201"),
                                                new Pregunta("p0130202", "R0130202")                                                
                                        )),
                                new Actividad("actividad 303",
                                        Arrays.asList(
                                                new Pregunta("p0130301", "R0130301"),
                                                new Pregunta("p0130302", "R0130302"),
                                                new Pregunta("p0130303", "R0130303")
                                        )),
                                new Actividad("actividad 304",
                                        Arrays.asList(
                                                new Pregunta("p0130401", "R0130401"),
                                                new Pregunta("p0130402", "R0130402")                                                
                                        ))
                        )),
                new Secuencia("Secuencia 04", "A2",
                        Arrays.asList(
                                new Actividad("actividad 401",
                                        Arrays.asList(
                                                new Pregunta("p0240101", "R0240101"),
                                                new Pregunta("p0240102", "R0240102")
                                ))
                        )),
                new Secuencia("Secuencia 05", "B1",
                        Arrays.asList(
                                new Actividad("actividad 501",
                                        Arrays.asList(
                                                new Pregunta("p0250101", "R0240101"),
                                                new Pregunta("p0250102", "R0240102")
                                )),
                                new Actividad("actividad 502",
                                        Arrays.asList(
                                                new Pregunta("p0250201", "R0240101"),
                                                new Pregunta("p0250202", "R0240102")
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

        EntityManager em = emf.createEntityManager();

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

        EntityManager em = emf.createEntityManager();

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
        EntityManager em = emf.createEntityManager();

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
