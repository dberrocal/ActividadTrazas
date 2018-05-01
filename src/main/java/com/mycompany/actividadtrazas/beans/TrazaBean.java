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
import com.mycompany.actividadtrazas.entity.TrazaError;
import com.mycompany.actividadtrazas.entity.TrazaTipo;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import static java.util.stream.Collectors.groupingBy;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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
public class TrazaBean {

    @Inject
    private ActividadBeans ac;

    //@PersistenceUnit(name = "DB")
    @PersistenceUnit(name = "DBRemoto")   
    private EntityManagerFactory emf;
    private long sumMinutos = 0L;
        
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
                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A")
        )){            
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
    
    public void TrazaTiempo(String documento,String session,Long actividadId,TrazaTipo tipo){        
        EntityManager em = emf.createEntityManager();        
        Traza tz = new Traza(documento, session, actividadId,tipo.toString());
        em.getTransaction().begin();
        em.persist(tz);
        em.getTransaction().commit();
    }
    
    public void TrazaIntento(String documento, String session,Long actividadId, List<RespuestaActividad> lista) {
        
        EntityManager em = emf.createEntityManager();

        Boolean valida = lista.stream().allMatch(RespuestaActividad::getValido);
        //Traza acierto
        em.getTransaction().begin();
        if (valida) {
            Traza tz01 = new Traza(documento, session, actividadId, TrazaTipo.ACIERTO.toString());
            Traza tz02 = new Traza(documento, session, actividadId, TrazaTipo.A10FINTIEMPO.toString());            
            em.persist(tz01);
            em.persist(tz02);            
        } else {
            Traza tz = new Traza(documento, session, actividadId, TrazaTipo.INTENTO.toString());
            lista.stream().filter((RespuestaActividad e) -> !e.getValido()).forEach((e) -> {
                tz.pushError(new TrazaError(tz, e.getPregunta(), e.getRespuesta()));
            });            
            em.persist(tz);            
        }
        em.getTransaction().commit();
    }
    
    public void getTT(String grupo, Date fechaInicial, Date fechaFinal){
        EntityManager em = emf.createEntityManager();        
        
        List<Traza> traza = em.createNamedQuery(Traza.REPORTE)
                //.setParameter("grupo", grupo)
                .setParameter("fi", fechaInicial)
                .setParameter("ff", fechaFinal)
                .getResultList();
        
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
