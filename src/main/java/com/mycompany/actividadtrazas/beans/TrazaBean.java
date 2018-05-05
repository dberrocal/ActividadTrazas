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
import com.mycompany.actividadtrazas.entity.vista.Numintentos;
import com.mycompany.actividadtrazas.entity.vista.Tiempoactividad;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Daniel
 */
@Named
@ApplicationScoped
public class TrazaBean {

    @Inject
    private ActividadBeans ac;

    @Inject
    @PostgresqlSQLDatabase
    private EntityManager em;
    
    @Resource
    private UserTransaction tx;
    
    private long sumMinutos = 0L;                    

    /**
     * Reporte de actividad, tiempo de actividad
     */
    public JsonArray getTrazasReporteTiempo(String grupo, Date fechaInicial, Date fechaFinal) {
        
        List<Tiempoactividad> traza = em.createNamedQuery(Tiempoactividad.TIEMPO)
                .setParameter("grupo", grupo)
                .setParameter("inicio", fechaInicial)
                .setParameter("fin", fechaFinal)
                .getResultList();

        JsonArrayBuilder jsonrespuesta = Json.createArrayBuilder();
        
        traza.forEach((tz)->{
            jsonrespuesta.add(Json.createObjectBuilder()
                        .add("estudiante", tz.getDocumento())
                        .add("actividad", tz.getActividad())
                        .add("nivel",tz.getGrupo())
                        .add("nombre", tz.getTarea())
                        .add("fecha", new SimpleDateFormat("YYYY/MM/DD").format(tz.getFecha()))
                        .add("duracion", String.format("%s:%s:%s", tz.getHora(),tz.getMinuto(),tz.getSegundo()) ));
            
        });                
        return jsonrespuesta.build();
    }
    
    /**
     * Reporte de actividad, numero de errores por pregunta y actividad
     */
    public JsonArray getTrazasReporteNumErrores(String grupo, Date fechaInicial, Date fechaFinal) {
        
        List<Numintentos> traza = em.createNamedQuery(Numintentos.GRUPO)
                .setParameter("grupo", grupo)
                .setParameter("inicio", fechaInicial)
                .setParameter("fin", fechaFinal)
                .getResultList();

        JsonArrayBuilder jsonrespuesta = Json.createArrayBuilder();
        
        traza.forEach((tz)->{
            jsonrespuesta.add(Json.createObjectBuilder()
                        .add("estudiante", tz.getDocumento())
                        .add("actividad", tz.getSecuencia())
                        .add("nivel",tz.getGrupo())
                        .add("nombre", tz.getActividad())
                        .add("fecha", new SimpleDateFormat("YYYY/MM/DD").format(tz.getFecha()))
                        .add("intentos", tz.getNumintentos()));
            
        });                
        return jsonrespuesta.build();
    }
    
    
    
    /**
     * Insertar traza de tiempo - inicio fin de actividad
     */
    public void TrazaTiempo(String documento,String session,String grupo,Long actividadId,TrazaTipo tipo){        
        
        try {
            Traza tz = new Traza(documento, session,grupo, actividadId,tipo.toString());
            //em.getTransaction().begin();
            tx.begin();
            em.persist(tz);
            tx.commit();
            //em.getTransaction().commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TrazaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /***
     * IInserta traza de intento
     */
    public void TrazaIntento(String documento, String session,String grupo,Long actividadId, List<RespuestaActividad> lista) {                

        try {
            Boolean valida = lista.stream().allMatch(RespuestaActividad::getValido);
            //Traza acierto
            //em.getTransaction().begin();
            tx.begin();
            if (valida) {
                Traza tz01 = new Traza(documento, session,grupo, actividadId, TrazaTipo.ACIERTO.toString());
                Traza tz02 = new Traza(documento, session,grupo, actividadId, TrazaTipo.A10FINTIEMPO.toString());
                em.persist(tz01);
                em.persist(tz02);
            } else {
                Traza tz = new Traza(documento, session,grupo, actividadId, TrazaTipo.INTENTO.toString());
                lista.stream().filter((RespuestaActividad e) -> !e.getValido()).forEach((e) -> {
                    tz.pushError(new TrazaError(tz, e.getPregunta(), e.getRespuesta()));
                });            
                em.persist(tz);
            }
            //em.getTransaction().commit();
            tx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TrazaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }            
}