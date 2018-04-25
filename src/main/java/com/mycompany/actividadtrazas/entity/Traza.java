/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Daniel
 */
@Table(name = "traza")
@Entity
@NamedQueries({
    @NamedQuery(name = "Traza.Todos", query = "SELECT a FROM Traza a JOIN a.actividad b JOIN b.sequencia c ORDER BY a.fecha"),
    @NamedQuery(name = "Traza.JOIN", query = "SELECT NEW com.mycompany.actividadtrazas.entity.Traza(a.id,a.fecha,a.sesion,a.documento,a.grupo,a.tipotraza,b.descripcion,c.descripcion,c.nivel) FROM Traza a JOIN a.actividad b JOIN b.sequencia c ORDER BY a.sesion,a.documento,a.fecha"),
    @NamedQuery(name = "Traza.Fecha", query = "SELECT NEW com.mycompany.actividadtrazas.entity.Traza(a.id,a.fecha,a.sesion,a.documento,a.grupo,a.tipotraza,b.descripcion,c.descripcion,c.nivel) FROM Traza a JOIN a.actividad b JOIN b.sequencia c WHERE a.fecha BETWEEN :fi AND :ff ORDER BY a.sesion,a.documento,a.fecha"),
    @NamedQuery(name = "Traza.TIEMPOFECHA", query = "SELECT NEW com.mycompany.actividadtrazas.entity.Traza(a.id,a.fecha,a.sesion,a.documento,a.grupo,a.tipotraza,b.descripcion,c.descripcion,c.nivel) FROM Traza a JOIN a.actividad b JOIN b.sequencia c WHERE a.fecha BETWEEN :fi AND :ff AND (a.tipotraza = :t01 OR a.tipotraza = :t02) ORDER BY a.sesion,a.documento,a.fecha")
})
public class Traza implements Serializable {
    
    public static final String TODOS = "Traza.Todos";
    public static final String REPORTE = "Traza.Fecha";
    public static final String TIPO = "Traza.TIEMPOFECHA";

    public Traza(){}
    
    public Traza(Long id, Date fecha, String sesion, String documento, String grupo, String tipotraza, String actividadnombre, String secuencianombre,String nivel) {
        this.id = id;
        this.fecha = fecha;
        this.sesion = sesion;
        this.documento = documento;
        this.grupo = grupo;        
        this.tipotraza = tipotraza;                
        this.actividadnombre = actividadnombre;
        this.secuencianombre = secuencianombre;
        this.nivel = nivel;
    }        
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;    
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    @Column
    private String sesion;
    @Column
    private String documento;
    @Column
    private String grupo;
    //@ManyToOne
    @Transient
    private Long actividadId;    
    //private Actividad actividad;
    //@Enumerated(EnumType.STRING)
    //private TrazaTipo tipotraza;
    @Column
    private String tipotraza;
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy="traza")
    List<TrazaError> error;    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ACTIVIDADID", unique=false, nullable=false, updatable=false,insertable = true)
    Actividad actividad;
    
    @Transient    
    public String actividadnombre;
    @Transient
    public String secuencianombre;        
    @Transient
    public String nivel;

    @PrePersist
    public void init(){
        this.fecha = Calendar.getInstance().getTime();
    }
    
    public Traza(String documento,String sesion, String tipotraza) {                
        this.sesion = sesion;
        this.documento = documento;        
        this.tipotraza = tipotraza;
    }
    
    public Traza(String documento,String sesion, Long actividad, String tipotraza) {                
        this.sesion = sesion;
        this.documento = documento;
        this.actividad = new Actividad(actividad);
        this.tipotraza = tipotraza;
    }        

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }   

    public String getTipotraza() {
        return tipotraza;
    }

    public void setTipotraza(String tipotraza) {
        this.tipotraza = tipotraza;
    }   

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }        

    public Long getActividadId() {
        return actividadId;
    }

    public void setError(List<TrazaError> error) {
        this.error = error;
    }

    public List<TrazaError> getError() {
        return error;
    }        
    
    public void pushError(TrazaError error){
        if(this.error == null)
            this.error = new ArrayList<>();
        this.error.add(error);
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public String getSesion() {
        return sesion;
    }        

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getActividadnombre() {
        return actividadnombre;
    }

    public void setActividadnombre(String actividadnombre) {
        this.actividadnombre = actividadnombre;
    }

    public String getSecuencianombre() {
        return secuencianombre;
    }

    public void setSecuencianombre(String secuencianombre) {
        this.secuencianombre = secuencianombre;
    }

    public String getNivel() {
        return nivel;
    }        
}
