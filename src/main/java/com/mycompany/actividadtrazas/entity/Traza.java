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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Daniel
 */
@Table(name = "traza")
@Entity
@NamedQueries({
    @NamedQuery(name = "Traza.Todos", query = "SELECT a FROM Traza a")
})
public class Traza implements Serializable {
    public static final String TODOS = "Traza.Todos";
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Column
    private String documento;
    //@ManyToOne
    @Column
    private Long actividadId;    
    //private Actividad actividad;
    //@Enumerated(EnumType.STRING)
    //private TrazaTipo tipotraza;
    @Column
    private String tipotraza;
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy="traza")
    List<TrazaError> error;
    
    public Traza() {
    }

    @PrePersist
    public void init(){
        this.fecha = Calendar.getInstance().getTime();
    }
    
    public Traza(String documento, Long actividad, String tipotraza) {                
        this.documento = documento;
        this.actividadId = actividad;
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
}
