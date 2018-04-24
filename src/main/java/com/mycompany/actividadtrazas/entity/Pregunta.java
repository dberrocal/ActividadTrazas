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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Daniel
 */
@Table(name = "pregunta")
@Entity
@NamedQueries({
    @NamedQuery(name = "Pregunta.Validar", query = "SELECT a FROM Pregunta a WHERE a.id = :id AND a.respuesta = :respuesta")
})
public class Pregunta implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    private String descripcion;
    @Column
    private String respuesta;    

    public Pregunta() {
    }

    public Pregunta(String descripcion, String respuesta) {
        this.descripcion = descripcion;
        this.respuesta = respuesta;        
    }
           
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }        
}
