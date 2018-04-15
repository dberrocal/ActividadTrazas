/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.beans;

import com.mycompany.actividadtrazas.entity.RespuestaActividad;
import com.mycompany.actividadtrazas.entity.Traza;
import com.mycompany.actividadtrazas.entity.TrazaError;
import com.mycompany.actividadtrazas.entity.TrazaTipo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import java.util.stream.Stream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Daniel
 */
@Named
@RequestScoped
public class TrazaBean {

    @Inject
    private ActividadBeans ac;

    @PersistenceUnit(name = "DB")
    private EntityManagerFactory emf;
    private long sumMinutos = 0L;

    public JsonArray getTrazas() {

        JsonArrayBuilder jsonrespuesta = Json.createArrayBuilder();
        EntityManager em = emf.createEntityManager();
        List<Traza> traza = em.createNamedQuery(Traza.TODOS).getResultList();

        Map<TareaTupla, List<Traza>> estdianteActividades = traza.stream()
                .collect(groupingBy((t) -> {
                    return new TareaTupla(t.getDocumento(), t.getSesion(), t.getSecuencianombre());
                }));

        Set<TareaTupla> keySet = estdianteActividades.keySet();

        keySet.forEach((documento) -> {
            List<Traza> lista = estdianteActividades.get(documento);

            Map<String, List<Traza>> trazaactaividad = traza.stream().collect(groupingBy(Traza::getActividadnombre));

            Set<String> keyActividad = trazaactaividad.keySet();
            
            JsonArrayBuilder jsontarea = Json.createArrayBuilder();            
            
            keyActividad.forEach((tarea) -> {
                List<Traza> tmp = trazaactaividad.get(tarea);
                Traza t01 = tmp.get(0);
                Traza t02 = tmp.get(tmp.size()-1);
                
                LocalDateTime ini = t01.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime fin = t02.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                
                Duration duration = Duration.between(ini, fin);                
                
                //jsontarea.add(Json.createObjectBuilder().add("nombre", t01.getActividadnombre()).add("fecha", t01.getFecha().toString()).add("duracion", duration.toMinutes()));
                jsonrespuesta.add(Json.createObjectBuilder()
                        .add("estudiante", documento.getNombre())
                        .add("actividad", documento.getActividad())
                        .add("nivel",Optional.ofNullable(documento.getNivel()).orElse("---"))
                        .add("nombre", t01.getActividadnombre())
                        .add("fecha", new SimpleDateFormat("YYYY/MM/dd").format(t01.getFecha()))
                        .add("duracion", duration.toMinutes()));
                sumMinutos += duration.toMinutes();
            });
            
            JsonObjectBuilder json = Json.createObjectBuilder();
            json.add("estudiante", documento.getNombre())
                    .add("actividad", documento.getActividad())
                    .add("nivel",Optional.ofNullable(documento.getNivel()).orElse("---"))
                    .add("duracion", sumMinutos)
                    .add("tareas", jsontarea.build());
            sumMinutos = 0L;
            //jsonrespuesta.add(json);
        });

        return jsonrespuesta.build();
    }

    public JsonArray getTrazasReporteTiempo(String grupo, Date fechaInicial, Date fechaFinal) {
        EntityManager em = emf.createEntityManager();
        List<Traza> traza = em.createNamedQuery(Traza.REPORTE)
                //.setParameter("grupo", grupo)
                .setParameter("fi", fechaInicial)
                .setParameter("ff", fechaFinal)
                .getResultList();

        JsonArrayBuilder jsonrespuesta = Json.createArrayBuilder();
        
        Map<TareaTupla, List<Traza>> estdianteActividades = traza.stream()
                .collect(groupingBy((t) -> {
                    return new TareaTupla(t.getDocumento(), t.getSesion(), t.getSecuencianombre());
                }));

        Set<TareaTupla> keySet = estdianteActividades.keySet();

        keySet.forEach((documento) -> {
            List<Traza> lista = estdianteActividades.get(documento);

            Map<String, List<Traza>> trazaactaividad = traza.stream().collect(groupingBy(Traza::getActividadnombre));

            Set<String> keyActividad = trazaactaividad.keySet();
            
            JsonArrayBuilder jsontarea = Json.createArrayBuilder();            
            
            keyActividad.forEach((tarea) -> {
                List<Traza> tmp = trazaactaividad.get(tarea);
                Traza t01 = tmp.get(0);
                Traza t02 = tmp.get(tmp.size()-1);
                
                LocalDateTime ini = t01.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime fin = t02.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                
                Duration duration = Duration.between(ini, fin);                
                
                //jsontarea.add(Json.createObjectBuilder().add("nombre", t01.getActividadnombre()).add("fecha", t01.getFecha().toString()).add("duracion", duration.toMinutes()));
                jsonrespuesta.add(Json.createObjectBuilder()
                        .add("estudiante", documento.getNombre())
                        .add("actividad", documento.getActividad())
                        .add("nivel",Optional.ofNullable(documento.getNivel()).orElse("---"))
                        .add("nombre", t01.getActividadnombre())
                        .add("fecha", new SimpleDateFormat("YYYY/MM/DD").format(t01.getFecha()))
                        .add("duracion", duration.toMinutes()));
                sumMinutos += duration.toMinutes();
            });
            
            JsonObjectBuilder json = Json.createObjectBuilder();
            json.add("estudiante", documento.getNombre())
                    .add("actividad", documento.getActividad())
                    .add("nivel",Optional.ofNullable(documento.getNivel()).orElse("---"))
                    .add("duracion", sumMinutos)
                    .add("tareas", jsontarea.build());
            sumMinutos = 0L;
            //jsonrespuesta.add(json);
        });
        return jsonrespuesta.build();
    }

    public void TrazaIntento(String documento, Long actividadId, List<RespuestaActividad> lista) {
        EntityManager em = emf.createEntityManager();

        Boolean valida = lista.stream().allMatch(RespuestaActividad::getValido);
        //Traza acierto
        if (valida) {
            Traza tz = new Traza("", documento, actividadId, TrazaTipo.ACIERTO.toString());
            em.getTransaction().begin();
            em.persist(tz);
            em.getTransaction().commit();
        } else {
            Traza tz = new Traza("", documento, actividadId, TrazaTipo.INTENTO.toString());
            lista.stream().filter((RespuestaActividad e) -> !e.getValido()).forEach((e) -> {
                tz.pushError(new TrazaError(tz, e.getPregunta(), e.getRespuesta()));
            });
            em.getTransaction().begin();
            em.persist(tz);
            em.getTransaction().commit();
        }
    }
}

class ReporteTupla {

    String estudiante;
    String actividad;
    Date duracion;
    Date fecha;
    List<TareaTupla> tareas;
}

class TareaTupla {

    String nombre;
    String actividad;
    String nivel;
    String sesion;

    public TareaTupla(String nombre, String sesion, String actividad) {
        this.nombre = nombre;
        this.sesion = sesion;
        this.actividad = actividad;
    }

    public String getActividad() {
        return actividad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNivel() {
        return nivel;
    }        
}
