/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.actividadtrazas.entity.Pregunta;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Daniel
 */
public class ConecinJunitTest {
    
    public ConecinJunitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testConexion() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB");
        Assert.assertTrue(emf.isOpen());
    }
    
    @Test
    public void testInsertInto() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for(Pregunta p : Arrays.asList(new Pregunta("", "", ""),new Pregunta("", "", ""),new Pregunta("", "", ""),new Pregunta("", "", ""),new Pregunta("", "", ""))){
            em.persist(p);
            System.out.println(p.getId());
        }
        em.getTransaction().commit();
    }
    
    @Test
    public void insertarT(){
    }
}

