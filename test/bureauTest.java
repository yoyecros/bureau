/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bureau.Chambre;
import bureau.DatabaseUtils;
import bureau.Lit;
import bureau.Mouvement;
import bureau.Patient;
import bureau.Service;
import bureau.Services;
import bureau.Venue;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
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
    
    public void clean() {
        Services serv = new Services(DatabaseUtils.fact());
        serv.deleteAllPatients();
        serv.deleteAllVenues();
        serv.deleteAllMouvements();
        serv.deleteAllServices();
        serv.deleteAllChambres();
        serv.deleteAllLits();
    }
    
    @Test
    public void patient() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        List<Venue> venues = new ArrayList<>();
        Patient p = serv.newPatient("123456789", "Cros", "Yohann", venues);
        assertNotNull(p);
        p = serv.newPatient("234567891", "Hernandez", "Patrick", venues);
        assertNotNull(p);
        p = serv.newPatient("345678912", "Juno", "François", venues);
        assertNotNull(p);
        Patient p2 = serv.getPatientByIPP("123456789");
        assertNotNull(p2);
        
        List<Patient> res = serv.getAllPatients();
        assert(!res.isEmpty());
        assert(res.size() == 3);
    }
    
    @Test
    public void venue() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        List<Mouvement> mouvements = new ArrayList<>();
        Venue v = serv.newVenue("123456789", mouvements);
        assertNotNull(v);
        v = serv.newVenue("234567891", mouvements);
        assertNotNull(v);
        v = serv.newVenue("345678912", mouvements);
        assertNotNull(v);
        Venue v2 = serv.getVenueByIEP("123456789");
        assertNotNull(v2);
        
        List<Venue> res = serv.getAllVenues();
        assert(!res.isEmpty());
        assert(res.size() == 3);
    }
    
    @Test
    public void mouvement() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        Timestamp date = new Timestamp(116, 11, 16, 15, 24, 0, 0);
        List<Chambre> chambres = new ArrayList<>();
        Service s = serv.newService("1", "Chirurgie", chambres);
        assertNotNull(s);
        Mouvement m = serv.newMouvement(date, true, s);
        assertNotNull(m);
        date = new Timestamp(116, 11, 17, 12, 25, 0, 0);
        m = serv.newMouvement(date, false, s);
        date = new Timestamp(116, 11, 17, 18, 43, 0, 0);
        m = serv.newMouvement(date, true, s);
        
        List<Mouvement> res = serv.getAllMouvements();
        assert(!res.isEmpty());
        assert(res.size() == 3);
    }
    
    @Test
    public void service() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        List<Lit> lits1 = new ArrayList<>();
        lits1.add(serv.newLit("1", null, false));
        lits1.add(serv.newLit("2", null, false));
        lits1.add(serv.newLit("3", null, false));
        assertNotNull(lits1);
        List<Lit> lits2 = new ArrayList<>();
        lits2.add(serv.newLit("1", null, false));
        lits2.add(serv.newLit("2", null, false));
        lits2.add(serv.newLit("3", null, false));
        assertNotNull(lits2);
        List<Lit> lits3 = new ArrayList<>();
        lits3.add(serv.newLit("1", null, false));
        lits3.add(serv.newLit("2", null, false));
        lits3.add(serv.newLit("3", null, false));
        assertNotNull(lits1);
        List<Chambre> chambres1 = new ArrayList<>();
        chambres1.add(serv.newChambre("A", lits1));
        chambres1.add(serv.newChambre("B", lits2));
        chambres1.add(serv.newChambre("C", lits3));
        assertNotNull(chambres1);
        lits1 = new ArrayList<>();
        lits1.add(serv.newLit("1", null, false));
        lits1.add(serv.newLit("2", null, false));
        lits1.add(serv.newLit("3", null, false));
        assertNotNull(lits1);
        lits2 = new ArrayList<>();
        lits2.add(serv.newLit("1", null, false));
        lits2.add(serv.newLit("2", null, false));
        lits2.add(serv.newLit("3", null, false));
        assertNotNull(lits2);
        lits3 = new ArrayList<>();
        lits3.add(serv.newLit("1", null, false));
        lits3.add(serv.newLit("2", null, false));
        lits3.add(serv.newLit("3", null, false));
        assertNotNull(lits1);
        List<Chambre> chambres2 = new ArrayList<>();
        chambres2.add(serv.newChambre("A", lits1));
        chambres2.add(serv.newChambre("B", lits2));
        chambres2.add(serv.newChambre("C", lits3));
        assertNotNull(chambres2);
        lits1 = new ArrayList<>();
        lits1.add(serv.newLit("1", null, false));
        lits1.add(serv.newLit("2", null, false));
        lits1.add(serv.newLit("3", null, false));
        assertNotNull(lits1);
        lits2 = new ArrayList<>();
        lits2.add(serv.newLit("1", null, false));
        lits2.add(serv.newLit("2", null, false));
        lits2.add(serv.newLit("3", null, false));
        assertNotNull(lits2);
        lits3 = new ArrayList<>();
        lits3.add(serv.newLit("1", null, false));
        lits3.add(serv.newLit("2", null, false));
        lits3.add(serv.newLit("3", null, false));
        assertNotNull(lits1);
        List<Chambre> chambres3 = new ArrayList<>();
        chambres3.add(serv.newChambre("A", lits1));
        chambres3.add(serv.newChambre("B", lits2));
        chambres3.add(serv.newChambre("C", lits3));
        assertNotNull(chambres3);
        Service s = serv.newService("1", "Chirurgie", chambres1);
        assertNotNull(s);
        s = serv.newService("2", "Radiologie", chambres2);
        assertNotNull(s);
        s = serv.newService("3", "Urgences", chambres3);
        assertNotNull(s);
        
        List<Service> res = serv.getAllServices();
        assert(!res.isEmpty());
        assert(res.size() == 3);
        for(int i = 0; i < res.size(); i++) {
            assert(!res.get(i).getChambres().isEmpty());
            assert(res.get(i).getChambres().size() == 3);
            for(int j = 0; j < res.get(i).getChambres().size(); j++) {
                assert(!res.get(i).getChambres().get(j).getLits().isEmpty());
                assert(res.get(i).getChambres().get(j).getLits().size() == 3);
            }
        }
    }
    
    @Test
    public void parcours() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        List<Lit> lits1 = new ArrayList<>();
        lits1.add(serv.newLit("1", null, false));
        lits1.add(serv.newLit("2", null, false));
        lits1.add(serv.newLit("3", null, false));
        assertNotNull(lits1);
        List<Lit> lits2 = new ArrayList<>();
        lits2.add(serv.newLit("1", null, false));
        lits2.add(serv.newLit("2", null, false));
        lits2.add(serv.newLit("3", null, false));
        assertNotNull(lits2);
        List<Lit> lits3 = new ArrayList<>();
        lits3.add(serv.newLit("1", null, false));
        lits3.add(serv.newLit("2", null, false));
        lits3.add(serv.newLit("3", null, false));
        assertNotNull(lits1);
        List<Chambre> chambres1 = new ArrayList<>();
        chambres1.add(serv.newChambre("A", lits1));
        chambres1.add(serv.newChambre("B", lits2));
        chambres1.add(serv.newChambre("C", lits3));
        assertNotNull(chambres1);
        for(Lit lit: lits1)
            lit.setChambre(chambres1.get(0));
        for(Lit lit: lits2)
            lit.setChambre(chambres1.get(1));
        for(Lit lit: lits3)
            lit.setChambre(chambres1.get(2));
        lits1 = new ArrayList<>();
        lits1.add(serv.newLit("1", null, false));
        lits1.add(serv.newLit("2", null, false));
        lits1.add(serv.newLit("3", null, false));
        assertNotNull(lits1);
        lits2 = new ArrayList<>();
        lits2.add(serv.newLit("1", null, false));
        lits2.add(serv.newLit("2", null, false));
        lits2.add(serv.newLit("3", null, false));
        assertNotNull(lits2);
        lits3 = new ArrayList<>();
        lits3.add(serv.newLit("1", null, false));
        lits3.add(serv.newLit("2", null, false));
        lits3.add(serv.newLit("3", null, false));
        assertNotNull(lits1);
        List<Chambre> chambres2 = new ArrayList<>();
        chambres2.add(serv.newChambre("A", lits1));
        chambres2.add(serv.newChambre("B", lits2));
        chambres2.add(serv.newChambre("C", lits3));
        assertNotNull(chambres2);
        for(Lit lit: lits1)
            lit.setChambre(chambres2.get(0));
        for(Lit lit: lits2)
            lit.setChambre(chambres2.get(1));
        for(Lit lit: lits3)
            lit.setChambre(chambres2.get(2));
        lits1 = new ArrayList<>();
        lits1.add(serv.newLit("1", null, false));
        lits1.add(serv.newLit("2", null, false));
        lits1.add(serv.newLit("3", null, false));
        assertNotNull(lits1);
        lits2 = new ArrayList<>();
        lits2.add(serv.newLit("1", null, false));
        lits2.add(serv.newLit("2", null, false));
        lits2.add(serv.newLit("3", null, false));
        assertNotNull(lits2);
        lits3 = new ArrayList<>();
        lits3.add(serv.newLit("1", null, false));
        lits3.add(serv.newLit("2", null, false));
        lits3.add(serv.newLit("3", null, false));
        assertNotNull(lits1);
        List<Chambre> chambres3 = new ArrayList<>();
        chambres3.add(serv.newChambre("A", lits1));
        chambres3.add(serv.newChambre("B", lits2));
        chambres3.add(serv.newChambre("C", lits3));
        assertNotNull(chambres3);
         for(Lit lit: lits1)
            lit.setChambre(chambres3.get(0));
        for(Lit lit: lits2)
            lit.setChambre(chambres3.get(1));
        for(Lit lit: lits3)
            lit.setChambre(chambres3.get(2));
        Service s = serv.newService("1", "Chirurgie", chambres1);
        assertNotNull(s);
        s = serv.newService("2", "Radiologie", chambres2);
        assertNotNull(s);
        s = serv.newService("3", "Urgences", chambres3);
        assertNotNull(s);
        
        List<Mouvement> mouvements = new ArrayList<>();
        Venue v = serv.newVenue("123456789", mouvements);
        assertNotNull(v);
        List<Venue> venues = new ArrayList<>();
        Patient p = serv.newPatient("123456789", "Cros", "Yohann", venues);
        assertNotNull(p);
        
        serv.getPatientByIPP("123456789").getVenues().add(v);
        assertNotNull(serv.getPatientByIPP("123456789").getVenues().get(0));
        
        Timestamp date = new Timestamp(116, 11, 16, 15, 24, 0, 0);
        Mouvement m = serv.newMouvement(date, true, serv.getServiceByNumero("1"));
        assertNotNull(m);
        serv.getPatientByIPP("123456789").getVenues().get(0).getMouvements().add(m);
        Lit lit = serv.setLitForMouvement(m);
        assert(lit.isOccupe() == true);
        assert(lit.getMouvement() == m);
        
        System.out.println("------------------ test 1");
        System.out.println("Service " + serv.getServiceByNumero("1").getLibelle());
        for(int i = 0 ; i < serv.getServiceByNumero("1").getChambres().size() ; i++) {
            System.out.println("Chambre numéro" + serv.getServiceByNumero("1").getChambres().get(i).getNumero());
            for(int j = 0 ; j < serv.getServiceByNumero("1").getChambres().get(i).getLits().size() ; j++) {
                if(serv.getServiceByNumero("1").getChambres().get(i).getLits().get(j).isOccupe())
                    System.out.println("Lit n°"+serv.getServiceByNumero("1").getChambres().get(i).getLits().get(j).getNumero() + " occupé");
                else
                    System.out.println("Lit n°"+serv.getServiceByNumero("1").getChambres().get(i).getLits().get(j).getNumero() + " libre");
            }
        }
        
        lit = serv.unsetLitForMouvement(m);
        assert(lit.isOccupe() == false);
        assertNull(lit.getMouvement());
        date = new Timestamp(116, 11, 17, 12, 25, 0, 0);
        m = serv.newMouvement(date, false, serv.getServiceByNumero("1"));
        m.setChambre(lit.getChambre());
        m.setLit(lit);
        serv.getPatientByIPP("123456789").getVenues().get(0).getMouvements().add(m);
        
        System.out.println("------------------ test 2");
        System.out.println("Service " + serv.getServiceByNumero("1").getLibelle());
        for(int i = 0 ; i < serv.getServiceByNumero("1").getChambres().size() ; i++) {
            System.out.println("Chambre numéro" + serv.getServiceByNumero("1").getChambres().get(i).getNumero());
            for(int j = 0 ; j < serv.getServiceByNumero("1").getChambres().get(i).getLits().size() ; j++) {
                if(serv.getServiceByNumero("1").getChambres().get(i).getLits().get(j).isOccupe())
                    System.out.println("Lit n°"+serv.getServiceByNumero("1").getChambres().get(i).getLits().get(j).getNumero() + " occupé");
                else
                    System.out.println("Lit n°"+serv.getServiceByNumero("1").getChambres().get(i).getLits().get(j).getNumero() + " libre");
            }
        }
        
        date = new Timestamp(116, 11, 17, 18, 43, 0, 0);
        m = serv.newMouvement(date, true, serv.getServiceByNumero("3"));
        serv.getPatientByIPP("123456789").getVenues().get(0).getMouvements().add(m);
        assert(serv.getPatientByIPP("123456789").getVenues().get(0).getMouvements().size() == 3);
        lit = serv.setLitForMouvement(m, serv.getLitById(51));
        assert(lit.isOccupe() == true);
        assert(lit.getMouvement() == m);
        
        assertNotNull(serv.getVenueByIEP("123456789").getMouvements());
        assert(serv.getVenueByIEP("123456789").getMouvements().size() == 3);

        System.out.println("------------------ test 3");
        System.out.println("Service " + serv.getServiceByNumero("3").getLibelle());
        for(int i = 0 ; i < serv.getServiceByNumero("3").getChambres().size() ; i++) {
            System.out.println("Chambre numéro" + serv.getServiceByNumero("3").getChambres().get(i).getNumero());
            for(int j = 0 ; j < serv.getServiceByNumero("3").getChambres().get(i).getLits().size() ; j++) {
                if(serv.getServiceByNumero("3").getChambres().get(i).getLits().get(j).isOccupe())
                    System.out.println("Lit n°"+serv.getServiceByNumero("3").getChambres().get(i).getLits().get(j).getNumero() + " occupé");
                else
                    System.out.println("Lit n°"+serv.getServiceByNumero("3").getChambres().get(i).getLits().get(j).getNumero() + " libre");
            }
        }
        
        /*lit = serv.unsetLitForMouvement(m);
        m = serv.newMouvement(date, false, serv.getServiceByNumero("3"));
        m.setLit(lit);
        m.setChambre(lit.getChambre());*/
        
        serv.flush();
        serv.close();
        DatabaseUtils.close();
        
        
    }
    
}
