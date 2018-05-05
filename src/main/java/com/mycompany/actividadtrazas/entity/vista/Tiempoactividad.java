/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.entity.vista;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "tiempoactividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiempoactividad.findAll", query = "SELECT t FROM Tiempoactividad t")
    , @NamedQuery(name = "Tiempoactividad.findById", query = "SELECT t FROM Tiempoactividad t WHERE t.id = :id")
    , @NamedQuery(name = "Tiempoactividad.findByDocumento", query = "SELECT t FROM Tiempoactividad t WHERE t.documento = :documento")
    , @NamedQuery(name = "Tiempoactividad.findByGrupo", query = "SELECT t FROM Tiempoactividad t WHERE t.grupo = :grupo AND t.fecha BETWEEN :inicio AND :fin")
    , @NamedQuery(name = "Tiempoactividad.findByTarea", query = "SELECT t FROM Tiempoactividad t WHERE t.tarea = :tarea")
    , @NamedQuery(name = "Tiempoactividad.findByActividad", query = "SELECT t FROM Tiempoactividad t WHERE t.actividad = :actividad")
    , @NamedQuery(name = "Tiempoactividad.findByHora", query = "SELECT t FROM Tiempoactividad t WHERE t.hora = :hora")
    , @NamedQuery(name = "Tiempoactividad.findByMinuto", query = "SELECT t FROM Tiempoactividad t WHERE t.minuto = :minuto")
    , @NamedQuery(name = "Tiempoactividad.findBySegundo", query = "SELECT t FROM Tiempoactividad t WHERE t.segundo = :segundo")})
public class Tiempoactividad implements Serializable {
    public static String TIEMPO = "Tiempoactividad.findByGrupo";
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "documento")
    private String documento;
    @Size(max = 255)
    @Column(name = "grupo")
    private String grupo;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;    
    @Size(max = 255)
    @Column(name = "tarea")
    private String tarea;
    @Size(max = 255)
    @Column(name = "actividad")
    private String actividad;
    @Size(max = 2147483647)
    @Column(name = "hora")
    private String hora;
    @Size(max = 2147483647)
    @Column(name = "minuto")
    private String minuto;
    @Size(max = 2147483647)
    @Column(name = "segundo")
    private String segundo;

    public Tiempoactividad() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
