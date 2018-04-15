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
@Table(name = "estudiante")
@Entity
@NamedQueries({
    @NamedQuery(name = "Estudiante.Todos", query = "SELECT a FROM Estudiante a"),
    @NamedQuery(name = "Actividad.Grupos", query = "SELECT a FROM Estudiante a WHERE a.grupo = :grupo")
})
public class Estudiante implements Serializable {
    public static String TODOS = "Estudiante.Todos";
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column    
    private String documento;
    @Column
    private String nombres;
    @Column
    private String grupo;

    public Estudiante() {
    }

    public Estudiante(String documento, String nombres, String grupo) {        
        this.documento = documento;
        this.nombres = nombres;
        this.grupo = grupo;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }        
}
