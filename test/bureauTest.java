/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bureau.Boite;
import bureau.Crayon;
import bureau.DatabaseUtils;
import bureau.Services;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicolas Singer <Nicolas.Singer@gmail.com>
 */
public class bureauTest {
    
    static EntityManagerFactory fact;
    
    public bureauTest() {
       
    }
    
    @BeforeClass
    public static void setUpClass() {
         //fact = Persistence.createEntityManagerFactory("bureauPU");
    }
    
    @AfterClass
    public static void tearDownClass() {
        //fact.close();
    }
    
    @Before
    public void setUp() {
       
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    @Test
    public void clean() {
        Services serv = new Services(DatabaseUtils.fact());
        serv.deleteAllBoites();
        serv.deleteAllCrayons();
        List<Crayon> res = serv.getAllCrayons();
        assert(res.isEmpty());
    }
    
        
    @Test
    public void crayon() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        Crayon cr = serv.newCrayon("vert");
        assertNotNull(cr); 
        cr = serv.newCrayon("jaune");
        assertNotNull(cr);
        cr = serv.newCrayon("vert");
        assertNotNull(cr);
        List<Crayon> res = serv.getCrayonsByCouleurId("vert");
        assert(!res.isEmpty());
        assert(res.size() == 2);
      
        res = serv.getAllCrayons();
        assert(!res.isEmpty());
        assert(res.size() == 3);
        
    }
    
    @Test
    public void boite() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        List<Crayon> liste = new ArrayList<>();
        String[] couleurs = { "rouge", "jaune", "vert" };
        for (String c : couleurs) {
            Crayon cr = new Crayon();
            cr.setCouleur(c);
            liste.add(cr);
        }
        Boite b = serv.newBoite(liste);
        assertNotNull(b);
        
        List<Boite> boites = serv.getBoitesByCouleurDeCrayon("vert");
        assert(!boites.isEmpty());
        assert(!boites.get(0).getCrayons().isEmpty());
        System.out.println(boites.get(0).getCrayons().get(2).getCouleur());
        assert(boites.get(0).getCrayons().size() == 3);
        
    }
    
    @Test
    public void boite2() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        
        List<Crayon> liste = new ArrayList<>();
        String[] couleurs = { "rouge", "jaune", "vert" };
        for (String c : couleurs) {
            Crayon cr = new Crayon();
            cr.setCouleur(c);
            liste.add(cr);
        }
        Boite b = serv.newBoite(liste);
        assertNotNull(b);
        
        Crayon cr = new Crayon();
        cr.setCouleur("vert");
        
        b.getCrayons().add(cr);
        
        
        List<Boite> boites = serv.getBoitesByCouleurDeCrayon("vert");
        assert(!boites.isEmpty());
        assert(!boites.get(0).getCrayons().isEmpty());
        assert(boites.get(0).getCrayons().size() == 4);
        
        serv.updateBoite(b);
        
        
    }
}
