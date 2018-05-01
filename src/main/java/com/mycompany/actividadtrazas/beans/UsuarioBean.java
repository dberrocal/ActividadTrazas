/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.actividadtrazas.beans;

import com.mycompany.actividadtrazas.entity.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Daniel
 */
@Named
@RequestScoped
public class UsuarioBean {    
    
    @Inject
    @PostgresqlSQLDatabase
    private EntityManager em;
    
    @Resource
    private UserTransaction tx;
    
    @Inject
    private UsuarioSession us;
    
    @Inject
    private HttpServletRequest httpRequest;
    
    public Boolean validarDocumento(String documento){
        return true;
    }
    
    public String inicio(String documento,String password){
        try{
            Usuario tmp = this.getUsuariopoDocumento(documento);                
            us.setData(tmp.getRol(), tmp.getNombres(), tmp.getDocumento(), tmp.getCurso());
            if(tmp.getRol().equals("estudiante"))
                return "{\"inicio\":true,\"rol\":\"e\"}";
            else
                return "{\"inicio\":true,\"rol\":\"p\"}";
        }catch(NoResultException e){
            System.out.println(e.getMessage());
        }
        return "{\"inicio\":false}";
    }
    
    public Usuario getUsuariopoDocumento(String documento){
        Usuario usuario =  em.createNamedQuery(Usuario.DOCUMENTO,Usuario.class).setParameter("doc", documento).getSingleResult();
        return usuario;
    }
    
    public JsonObject getUsuario(String documento){
        JsonObjectBuilder json = Json.createObjectBuilder();
        try{
            Usuario ususrio =  em.createNamedQuery(Usuario.DOCUMENTO,Usuario.class).setParameter("doc", documento).getSingleResult();
            json.add("documento", ususrio.getDocumento()).add("nombre", ususrio.getNombres()).add("rol", ususrio.getRol());
        }catch(NoResultException e){}
        return json.build();
    }
    
    public void Persist(Usuario usuario){
        try {
            tx.begin();
            em.persist(usuario);
            tx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
