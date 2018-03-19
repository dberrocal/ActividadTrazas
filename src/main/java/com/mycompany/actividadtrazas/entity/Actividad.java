/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Daniel
 */
@Table(name = "actividad")
@Entity
@NamedQueries({
    @NamedQuery(name = "Actividad.Niveles", query = "SELECT a FROM Actividad a")
})
public class Actividad implements Serializable {
    public static final String NIVELES = "Actividad.Niveles";
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    private String descripcion;
    @Column
    private String nivel;
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(
            name = "actividad_pregunta",
            joinColumns = @JoinColumn(name = "actividad_id"),
            inverseJoinColumns = {@JoinColumn(name = "pregunta_id")}
    )
    private List<Pregunta> pregunta;

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

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNivel() {
        return nivel;
    }

    public void setPregunta(List<Pregunta> pregunta) {
        this.pregunta = pregunta;
    }

    public List<Pregunta> getPregunta() {
        return pregunta;
    }
    
}
