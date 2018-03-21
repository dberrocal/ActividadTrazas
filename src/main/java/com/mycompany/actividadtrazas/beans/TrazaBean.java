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
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
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
@RequestScoped
public class TrazaBean {
    
    @PersistenceUnit(name = "DB")    
    private EntityManagerFactory emf;
    
    public JsonArray getTrazas(){
        EntityManager em = emf.createEntityManager();        
        List<Traza> traza = em.createNamedQuery(Traza.TODOS).getResultList();
        JsonArrayBuilder json = Json.createArrayBuilder();
        traza.forEach((a)->{
            json.add(Json.createObjectBuilder().add("id", a.getId()).add("fecha", a.getFecha().toString()).add("tipo", a.getTipotraza()));
        });
        return json.build();
    }
    
    public void TrazaIntento(String documento,Long actividadId,List<RespuestaActividad> lista){
        EntityManager em = emf.createEntityManager();        
        
        Boolean valida = lista.stream().allMatch(RespuestaActividad::getValido);
        //Traza acierto
        if(valida){            
            Traza tz = new Traza(documento,actividadId,TrazaTipo.ACIERTO.toString());            
            em.getTransaction().begin();
            em.persist(tz);
            em.getTransaction().commit();
        }else{
            Traza tz = new Traza(documento,actividadId,TrazaTipo.INTENTO.toString());
            lista.stream().filter((RespuestaActividad e)->!e.getValido()).forEach((e)->{
                tz.pushError(new TrazaError(tz, e.getPregunta(), e.getRespuesta()));
            });            
            em.getTransaction().begin();
            em.persist(tz);
            em.getTransaction().commit();
        }        
    }
}
