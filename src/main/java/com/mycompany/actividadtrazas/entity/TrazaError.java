/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Daniel
 */
@Table(name = "trazaerror")
@Entity
public class TrazaError implements Serializable {
    @Id    
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne//@JoinColumn(name="TRAZA_ID")
    private Traza traza;
    @Column
    private Long preguntaId;    
    //private Pregunta pregunta;
    @Column
    private String error;

    public TrazaError() {
    }

    public TrazaError(Traza traza, Long preguntaId, String error) {
        this.traza = traza;
        this.preguntaId = preguntaId;
        this.error = error;
    }   
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public Long getPreguntaId() {
        return preguntaId;
    }        

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setTraza(Traza traza) {
        this.traza = traza;
    }

    public Traza getTraza() {
        return traza;
    }
    
    
}
