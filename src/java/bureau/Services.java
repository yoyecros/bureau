/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bureau;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nicolas Singer <Nicolas.Singer@gmail.com>
 */
public class Services {
    
    EntityManagerFactory fact;
    EntityManager em;

    public Services(EntityManagerFactory fact) {
        this.fact = fact;
        this.em = fact.createEntityManager();
    }
    
    
    public void close() {
        em.close();
    }
    public void flush() {
        em.getTransaction( ).begin( );
        em.flush();
        em.getTransaction().commit();
    }
    
    public void newPatient(Patient p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }
    
    public void newVenue(Venue v) {
        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();
    }

    public void newMouvement(Mouvement m) {
        em.getTransaction().begin();
        em.persist(m);
        if(m.isEntree())
            this.setLitForMouvement(m);
        else
            this.unsetLitForMouvement(m);
        em.getTransaction().commit();
    }
    
    public void newService(Service s) {
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
    }
    
    public void newChambre(Chambre c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }
    public void newLit(Lit l) {
        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
    }
    
    public Patient newPatient(String ipp, String nom, String prenom, List<Venue> venues) {
        Patient p =  new Patient();
        p.setIpp(ipp);
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setVenues(venues);
        
        em.getTransaction( ).begin( );
        em.persist(p);
        em.getTransaction().commit();
      
        return p;
    }
    
    public Venue newVenue(String iep, List<Mouvement> mouvements) {
        Venue v = new Venue();
        v.setIep(iep);
        v.setMouvements(mouvements);
        
        em.getTransaction( ).begin( );
        em.persist(v);
        em.getTransaction().commit();
      
        return v;
    }
    
    public Mouvement newMouvement(Timestamp date, boolean entree, Service service) {
        Mouvement m = new Mouvement();
        m.setDate(date);
        m.setEntree(entree);
        m.setService(service);
        
        em.getTransaction( ).begin( );
        em.persist(m);
        em.getTransaction().commit();
      
        return m;
    }
    
    public Service newService(String numero, String libelle, List<Chambre> chambres) {
        Service s = new Service();
        s.setNumero(numero);
        s.setLibelle(libelle);
        s.setChambres(chambres);
        
        em.getTransaction( ).begin( );
        em.persist(s);
        em.getTransaction().commit();
      
        return s;
    }
    
    public Chambre newChambre(String numero, List<Lit> lits) {
        Chambre c = new Chambre();
        c.setNumero(numero);
        c.setLits(lits);
        
        em.getTransaction( ).begin( );
        em.persist(c);
        em.getTransaction().commit();
      
        return c;
    }
    
    public Lit newLit(String numero, Mouvement mouvement, boolean occupe) {
        Lit l = new Lit();
        l.setNumero(numero);
        l.setMouvement(mouvement);
        l.setOccupe(occupe);
        
        em.getTransaction( ).begin( );
        em.persist(l);
        em.getTransaction().commit();
      
        return l;
    }
    
    public void removePatient(long id) {
        Patient p = em.find(Patient.class, id);
        em.getTransaction( ).begin( );
        em.remove(p);
        em.getTransaction().commit();
    }
    
    public void removeVenue(long id) {
        Venue v = em.find(Venue.class, id);
        em.getTransaction().begin();
        em.remove(v);
        em.getTransaction().commit();
    }
    
    public void removeMouvement(long id) {
        Mouvement m = em.find(Mouvement.class, id);
        if(m.isEntree() && m.getLit().getMouvement() == m) {
            m.getLit().setOccupe(false);
            m.getLit().setMouvement(null);
        }
        em.getTransaction().begin();
        em.remove(m);
        em.getTransaction().commit();
    }
    
    public void removeService(long id) {
        Service s = em.find(Service.class, id);
        em.getTransaction().begin();
        em.remove(s);
        em.getTransaction().commit();
    }
    
    public void removeChambre(long id) {
        Chambre c = em.find(Chambre.class, id);
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }
    
    public void removeLit(long id) {
        Lit l = em.find(Lit.class, id);
        em.getTransaction().begin();
        em.remove(l);
        em.getTransaction().commit();
    }
    
    public void editPatient(Patient p) {
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
    }
    
    public void editVenue(Venue v) {
        em.getTransaction().begin();
        em.merge(v);
        em.getTransaction().commit();
    }
    
    public void editMouvement(Mouvement m) {
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
    }
    
    public void editService(Service s) {
        em.getTransaction().begin();
        em.merge(s);
        em.getTransaction().commit();
    }
    
    public void editChambre(Chambre c) {
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }
    
    public void editLit(Lit l) {
        em.getTransaction().begin();
        em.merge(l);
        em.getTransaction().commit();
    }
    
    public Patient getPatientById(long id) {
        Patient p = em.find(Patient.class, id);
        
        return p;
    }
    
    public Venue getVenueById(long id) {
        Venue v = em.find(Venue.class, id);
        
        return v;
    }
    
    public Mouvement getMouvementById(long id) {
        Mouvement m = em.find(Mouvement.class, id);
        
        return m;
    }
    
    public Service getServiceById(long id) {
        Service s = em.find(Service.class, id);
        
        return s;
    }
    
    public Chambre getChambreById(long id) {
        Chambre c = em.find(Chambre.class, id);
        
        return c;
    }
    
    public Lit getLitById(long id) {
        Lit l = em.find(Lit.class, id);
        
        return l;
    }
    
    public List<Patient> getAllPatients() {
        TypedQuery<Patient> query = em.createQuery("Select p FROM Patient p", Patient.class);
        List<Patient> p = query.getResultList();
        return p;
    }
    
    public List<Venue> getAllVenues() {
        TypedQuery<Venue> query = em.createQuery("Select v FROM Venue v", Venue.class);
        List<Venue> v = query.getResultList();
        return v;
    }
    
    public List<Mouvement> getAllMouvements() {
        TypedQuery<Mouvement> query = em.createQuery("Select m FROM Mouvement m", Mouvement.class);
        List<Mouvement> m = query.getResultList();
        return m;
    }
    
    public List<Service> getAllServices() {
        TypedQuery<Service> query = em.createQuery("Select s FROM Service s", Service.class);
        List<Service> s = query.getResultList();
        return s;
    }
    
    public List<Chambre> getAllChambres() {
        TypedQuery<Chambre> query = em.createQuery("Select c FROM Chambre c", Chambre.class);
        List<Chambre> c = query.getResultList();
        return c;
    }
    
    public List<Lit> getAllLits() {
        TypedQuery<Lit> query = em.createQuery("Select l FROM Lit l", Lit.class);
        List<Lit> l = query.getResultList();
        return l;
    }
    
    public Patient getPatientByIPP(String ipp) {
        TypedQuery<Patient> query = em.createQuery("SELECT p FROM Patient p WHERE p.ipp = :ipp", Patient.class)
                .setParameter("ipp",ipp);
        Patient res = query.getSingleResult();
     
        return res;
    }
    
    public Patient getPatientByVenue(String iep) {
        TypedQuery<Patient> query = em.createQuery("SELECT p FROM Patient as p left join p.venues WHERE iep = :iep)", Patient.class)
                .setParameter("iep",iep);
        Patient res = query.getSingleResult();
     
        return res;
    }
    
    public Patient getPatientByMouvement(Long id) {
        TypedQuery<Patient> query = em.createQuery("SELECT p FROM Patient as p left join p.venues WHERE iep = (SELECT v.iep FROM Venue as v left join v.mouvements m WHERE m.id = :id)", Patient.class)
                .setParameter("id",id);
        Patient res = query.getSingleResult();
     
        return res;
    }
    
    public Venue getVenueByIEP(String iep) {
        TypedQuery<Venue> query = em.createQuery("SELECT v FROM Venue v WHERE v.iep = :iep", Venue.class)
                .setParameter("iep",iep);
        Venue res = query.getSingleResult();
     
        return res;
    }
    
    public Venue getVenueByMouvement(long id) {
        TypedQuery<Venue> query = em.createQuery("SELECT v FROM Venue as v left join v.mouvements m WHERE m.id = :id", Venue.class)
                .setParameter("id",id);
        Venue res = query.getSingleResult();
     
        return res;
    }
    
    public Service getServiceByNumero(String numero) {
        TypedQuery<Service> query = em.createQuery("SELECT s FROM Service s WHERE s.numero = :numero", Service.class)
                .setParameter("numero",numero);
        Service res = query.getSingleResult();
     
        return res;
    }
    
    public Lit setLitForMouvement(Mouvement mouvement) {
        if(mouvement.getLit() ==  null) {
            Service s = getServiceByNumero(mouvement.getService().getNumero());
            for(Chambre c : s.getChambres()) {
                for(Lit l : c.getLits()) {
                    if(!l.isOccupe()) {
                        mouvement.setChambre(c);
                        mouvement.setLit(l);
                        l.setMouvement(mouvement);
                        l.setOccupe(true);
                        return l;
                    }
                }
            }
            return null;
        }
        else {
            Lit lit = mouvement.getLit();
            if(!lit.isOccupe()) {
                lit.setMouvement(mouvement);
                lit.setOccupe(true);
                System.out.println(lit.isOccupe());
                System.out.println(lit.getMouvement().getId());
                em.merge(lit);
                return lit;
            }
            return null;
        }            
    }
    
    public Lit setLitForMouvement(Mouvement mouvement, Lit lit) {
        if(!lit.isOccupe()) {
            lit.setMouvement(mouvement);
            lit.setOccupe(true);
            em.merge(lit);
            return lit;
        }
        return null;
    }
    
    public Lit unsetLitForMouvement(Mouvement mouvement) {
        Lit lit = mouvement.getLit();
        lit.setOccupe(false);
        lit.setMouvement(null);
        em.merge(lit);
        return lit;
    }
    
    public void addMouvementToVenue(Mouvement m, Venue v) {
        em.getTransaction( ).begin( );
        v.getMouvements().add(m);
        em.persist(v);
        em.getTransaction().commit();
    }
    
    public void deleteAllPatients() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Patient").executeUpdate();
        em.getTransaction().commit();
    }
    
    public void deleteAllVenues() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Venue").executeUpdate();
        em.getTransaction().commit();
    }
    
    public void deleteAllMouvements() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Mouvement").executeUpdate();
        em.getTransaction().commit();
    }
    
    public void deleteAllServices() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Service").executeUpdate();
        em.getTransaction().commit();
    }
    
    public void deleteAllChambres() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Chambre").executeUpdate();
        em.getTransaction().commit();
    }
    
    public void deleteAllLits() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Lit").executeUpdate();
        em.getTransaction().commit();
    }
}