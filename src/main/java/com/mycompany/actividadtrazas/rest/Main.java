/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.rest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Daniel
 */
public class Main {
    public static void main(String args[]){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB");
        EntityManager em = emf.createEntityManager();
    }
}
