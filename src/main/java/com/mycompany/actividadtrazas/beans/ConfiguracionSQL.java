/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel
 */
@Named
@ApplicationScoped
public class ConfiguracionSQL {
       
    @Produces
    @PostgresqlSQLDatabase
    @PersistenceContext(unitName = "DBRemoto")    
    private EntityManager entityFactory;
    
}
