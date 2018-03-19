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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
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
        List<Pregunta> lista = new ArrayList<>();
        lista.add(new Pregunta("Alberto ESPACIO (drive) to school bus everyday","",""));
        lista.add(new Pregunta("Sandy ESPACIO (forget) her homework very often.","",""));
        lista.add(new Pregunta("Sandy ESPACIO (forget) her homework very often.","",""));
        lista.add(new Pregunta("Sandy ESPACIO (forget) her homework very often.","",""));
        EntityManager em = emf.createEntityManager();
        for(Pregunta p : lista)
            em.persist(p);
    }
        
    public Actividad getActividad(){
        return new Actividad();
    }
}
