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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Daniel
 */
@Table(name = "secuencia")
@Entity
@NamedQueries({
    @NamedQuery(name = "Secuencia.Todos", query = "SELECT a FROM Secuencia a"),
    @NamedQuery(name = "Secuencia.Niveles", query = "SELECT a FROM Secuencia a WHERE a.nivel = :nivel")
})
public class Secuencia implements Serializable {    
    public static String TODOS = "Secuencia.Todos";
    public static String NIVEL = "Secuencia.Niveles";
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String descripcion;
    @Column
    private String nivel;
    @NotNull
    @Column(nullable = false)
    private Integer numero;
    @OneToMany(mappedBy = "sequencia",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List<Actividad> actividades;

    public Secuencia() {
    }        

    public Secuencia(String descripcion, String nivel, List<Actividad> actividades) {
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.numero = actividades.size();
        this.actividades = actividades;
        actividades.stream().forEach((tem)->{
            tem.setSequencia(this);
        });                    
    }        
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }   

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero() {
        return numero;
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
}
