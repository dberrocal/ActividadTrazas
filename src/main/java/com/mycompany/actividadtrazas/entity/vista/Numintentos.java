/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.entity.vista;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "numintentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Numintentos.findAll", query = "SELECT n FROM Numintentos n")
    , @NamedQuery(name = "Numintentos.findById", query = "SELECT n FROM Numintentos n WHERE n.id = :id")
    , @NamedQuery(name = "Numintentos.findByDocumento", query = "SELECT n FROM Numintentos n WHERE n.documento = :documento")
    , @NamedQuery(name = "Numintentos.findByFecha", query = "SELECT n FROM Numintentos n WHERE n.fecha = :fecha")
    , @NamedQuery(name = "Numintentos.findByGrupo", query = "SELECT n FROM Numintentos n WHERE n.grupo = :grupo AND n.fecha BETWEEN :inicio AND :fin")
    , @NamedQuery(name = "Numintentos.findBySecuencia", query = "SELECT n FROM Numintentos n WHERE n.secuencia = :secuencia")
    , @NamedQuery(name = "Numintentos.findByActividad", query = "SELECT n FROM Numintentos n WHERE n.actividad = :actividad")
    , @NamedQuery(name = "Numintentos.findByNumintentos", query = "SELECT n FROM Numintentos n WHERE n.numintentos = :numintentos")})
public class Numintentos implements Serializable {
    public static String GRUPO = "Numintentos.findByGrupo";
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "documento")
    private String documento;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 255)
    @Column(name = "grupo")
    private String grupo;
    @Size(max = 255)
    @Column(name = "secuencia")
    private String secuencia;
    @Size(max = 255)
    @Column(name = "actividad")
    private String actividad;
    @Column(name = "numintentos")
    private BigInteger numintentos;

    public Numintentos() {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public BigInteger getNumintentos() {
        return numintentos;
    }

    public void setNumintentos(BigInteger numintentos) {
        this.numintentos = numintentos;
    }
    
}
