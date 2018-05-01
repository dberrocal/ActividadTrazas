/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.actividadtrazas.entity.Actividad;
import com.mycompany.actividadtrazas.entity.Estudiante;
import com.mycompany.actividadtrazas.entity.Pregunta;
import com.mycompany.actividadtrazas.entity.Secuencia;
import com.mycompany.actividadtrazas.entity.Traza;
import com.mycompany.actividadtrazas.entity.TrazaTipo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import static java.util.stream.Collectors.groupingBy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

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
    // The methods must be annotated with annotation //@Test. For example:
    //
    //@Test
    public void testConexion() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBRemoto");
        Assert.assertTrue(emf.isOpen());
    }
    
    //@Test
    public void testInsertInto() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBRemoto");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        /*for(Pregunta p : Arrays.asList(new Pregunta("", "", ""),new Pregunta("", ""),new Pregunta("", ""),new Pregunta("", ""),new Pregunta("", ""))){
            em.persist(p);
            System.out.println(p.getId());
        }*/
        em.getTransaction().commit();
    }
        
    public void insertarT(){
        System.out.println("INIT");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBRemoto");
        EntityManager em = emf.createEntityManager();
        
        Secuencia sc = new Secuencia();
        sc.setDescripcion("Actividad 001");
        sc.setNivel("A1");
        sc.setNumero(2);                                
        
        List<Pregunta> lista = new ArrayList<>();
        em.getTransaction().begin();
        for(Pregunta p : Arrays.asList(
                new Pregunta("Alberto ESPACIO (drive) to school bus everyday","A") ,
                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"),
                new Pregunta("Sandy ESPACIO (forget) her homework very often.","A"))){            
            //em.persist(p);
            lista.add(p);
        }
        List<Actividad> activiades = new ArrayList<>();
        
        Actividad actividad = new Actividad();
        actividad.setDescripcion("Tarea-01");
        actividad.setNivel("B1");
        actividad.setPregunta(lista);
        activiades.add(actividad);
        //em.persist(actividad);
        
        Actividad actividad2 = new Actividad();
        actividad2.setDescripcion("Tarea-02");
        actividad2.setNivel("B2");
        actividad2.setPregunta(lista);
        activiades.add(actividad2);
        
        sc.setActividades(activiades);
        em.persist(sc);
        em.getTransaction().commit();
    }
        
    public void InsertarEstudianteTest(){
        
        System.out.println("ESTUDIANTE");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBRemoto");
        EntityManager em = emf.createEntityManager();        
        
        List<Estudiante> lista = Arrays.asList(
                new Estudiante("A101", "Estudiante A101", "A1"),
                new Estudiante("A102", "Estudiante A102", "A1"),
                new Estudiante("A103", "Estudiante A103", "A1"),
                new Estudiante("A104", "Estudiante A104", "A1"),
                new Estudiante("A105", "Estudiante A105", "A1"),
                new Estudiante("A106", "Estudiante A106", "A1"),
                new Estudiante("A107", "Estudiante A107", "A1"),
                new Estudiante("B101", "Estudiante B101", "B1"),
                new Estudiante("B102", "Estudiante B102", "B1"),
                new Estudiante("B103", "Estudiante B103", "B1")
        );
                
        em.getTransaction().begin();
        
        for(Estudiante s: lista)
            em.persist(s);
        
        em.getTransaction().commit();
    }
    
    ////@Test   
    public void Z99InsertarTramas(){
        insertarT();
        InsertarEstudianteTest();
        System.out.println("ASA");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBRemoto");
        EntityManager em = emf.createEntityManager();                
        
        List<Secuencia> secuencias = em.createNamedQuery(Secuencia.TODOS).getResultList();
        List<Estudiante> estudiantes = em.createNamedQuery(Estudiante.TODOS).getResultList();
        System.out.println(estudiantes.size());
        em.getTransaction().begin();
        for(Estudiante estudiante:estudiantes){
            for(Secuencia secuencia:secuencias){
                for(Actividad actividad:secuencia.getActividades()){
                    Calendar fecha = Calendar.getInstance();
                    System.out.println(estudiante.getDocumento());
                    Long tx = new Random().nextLong();
                    Traza trx = new Traza(tx.toString(),estudiante.getDocumento(), actividad.getId(), TrazaTipo.A01INIACT.toString());
                    fecha.add(Calendar.MINUTE, -3);
                    trx.setFecha(fecha.getTime());
                    em.persist(trx);            
                    /*try {
                        Thread.sleep(new Random().nextInt(5000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ActividadBeans.class.getName()).log(Level.SEVERE, null, ex);
                    }                */        
                    Traza trx01 = new Traza(tx.toString(),estudiante.getDocumento(), actividad.getId(), TrazaTipo.A02INTACT.toString());
                    trx01.setFecha(fecha.getTime());
                    em.persist(trx01);
                    /*try {
                        Thread.sleep(new Random().nextInt(5000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ActividadBeans.class.getName()).log(Level.SEVERE, null, ex);
                    }*/                       
                    Traza trx02 = new Traza(tx.toString(),estudiante.getDocumento(), actividad.getId(), TrazaTipo.A02INTACT.toString());
                    trx02.setFecha(fecha.getTime());
                    em.persist(trx02);
                    /*try {
                        Thread.sleep(new Random().nextInt(5000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ActividadBeans.class.getName()).log(Level.SEVERE, null, ex);
                    }*/                        
                    Traza trx03 = new Traza(tx.toString(),estudiante.getDocumento(), actividad.getId(), TrazaTipo.A03OKACT.toString());
                    trx03.setFecha(fecha.getTime());
                    em.persist(trx03);
            
                    System.out.println("----FIN----");
                }
            }
        }
        em.getTransaction().commit();
        
        
        List<Traza> traza = em.createNamedQuery(Traza.TODOS).getResultList();                
        
        Map<String,List<Traza>> estdianteActividades = traza.stream()
        .collect(groupingBy(Traza::getDocumento));
        
        Set<String> keySet = estdianteActividades.keySet();
        keySet.forEach((documento)->{
            List<Traza> lista = estdianteActividades.get(documento);
            System.out.println("-----");
            System.out.println(documento);
            lista.forEach((trazaf)->{
                System.out.println(trazaf.getSecuencianombre());
            });
            System.out.println("-----");
        });
    }
}

