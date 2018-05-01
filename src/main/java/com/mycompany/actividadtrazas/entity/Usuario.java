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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Table(name = "usuario")
@Entity
@NamedQueries({
    @NamedQuery(name = "Usuario.Todos", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.Documento", query = "SELECT u FROM Usuario u WHERE u.documento = :doc")
})
@XmlRootElement
public class Usuario implements Serializable {
    public static String TODOS = "Usuario.Todos";
    public static String DOCUMENTO = "Usuario.Documento";
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    private String documento;
    @Column
    private String nombres;
    @Column
    private String curso;
    @Column
    private String rol;

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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }        
}
