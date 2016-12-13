/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bureau;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

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
    public void newCrayon(Crayon cr) {
	em.getTransaction( ).begin( );
        em.persist(cr);
        em.getTransaction().commit();
    }

    public Crayon newCrayon(String couleur) {
        Crayon p = new Crayon();
        p.setCouleur(couleur);
     
	em.getTransaction( ).begin( );
        em.persist(p);
        em.getTransaction().commit();
      
        return p;
    }
    
    public void removeCrayon(int id) {
       
        Crayon cr = em.find( Crayon.class, id );
	em.getTransaction( ).begin( );
        em.remove(cr);
        em.getTransaction().commit();
       
    }
    
    public void deleteBoite(int id) {
       
        Boite b = em.find( Boite.class, id );
	em.getTransaction( ).begin( );
        em.remove(b);
        em.getTransaction().commit();
       
    }
  
    public void editCrayon(Crayon cr) {
      
	em.getTransaction( ).begin( );
        em.merge(cr);
        em.getTransaction().commit();
     
    }
    
    public Crayon getCrayonsById(int id) {
       
	Crayon res = em.find( Crayon.class, id );
      
        return res;
    }
    
    public List<Crayon> getAllCrayons() {
	TypedQuery<Crayon> query = em.createQuery("SELECT c FROM Crayon c", Crayon.class);
        List<Crayon> res = query.getResultList();
        return res;
    }
    
     public List<Crayon> getAllCrayonsSansBoite() {
        // va chercher la liste des crayons qui ne sont pas affectés à une boite, cad les crayons pour lesquels
        // il n'existe pas de boite les contenant. D'où la requête.
	TypedQuery<Crayon> query = em.createQuery("SELECT c FROM Crayon c WHERE  NOT EXISTS (SELECT b FROM Boite b, IN (b.crayons) cr WHERE cr = c)", Crayon.class);
        List<Crayon> res = query.getResultList();
        return res;
    }
    
    public List<Boite> getAllBoites() {
      
	TypedQuery<Boite> query = em.createQuery("SELECT b FROM Boite b", Boite.class);
        List<Boite> res = query.getResultList();
      
        return res;
    }
    
    public List<Crayon> getCrayonsByCouleurId(String couleur) {
     
        TypedQuery<Crayon> query = em.createQuery("SELECT c FROM Crayon c WHERE c.couleur = :couleur", Crayon.class)
                .setParameter("couleur",couleur);
        List<Crayon> res = query.getResultList();
     
        return res;
    }
    
    public Boite newBoite(List<Crayon> crayons) {
        Boite b = new Boite();
	em.getTransaction( ).begin( );
        b.setCrayons(crayons);
        em.persist(b);
        em.getTransaction().commit();
        return b;
    }
    
    public Boite newBoite(Boite b) {
	em.getTransaction( ).begin( );
        em.persist(b);
        em.getTransaction().commit();
        return b;
    }
    
    public void updateBoite(Boite b) {
        em.getTransaction( ).begin( );
        em.merge(b);
        em.getTransaction().commit();
    }
    
    public Boite getBoiteById(int id) {
        
	Boite res = em.find( Boite.class, id );
       
        return res;
    }
    
    public List<Boite> getBoitesByCouleurDeCrayon(String couleur) {
       
        TypedQuery<Boite> query = em.createQuery("SELECT b FROM Boite b JOIN b.crayons c WHERE c.couleur = :couleur", Boite.class)
                .setParameter("couleur",couleur);
        List<Boite> res =  query.getResultList();
       
        return res;
    }
    
    public void deleteAllBoites() {
      
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Boite").executeUpdate();
        em.getTransaction().commit();
        
    }
    
    public void deleteAllCrayons() {
      
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Crayon").executeUpdate();
        em.getTransaction().commit();
        
    }
}