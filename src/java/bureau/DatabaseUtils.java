/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bureau;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nicolas Singer <Nicolas.Singer@gmail.com>
 */

public class DatabaseUtils {
    
    private static EntityManagerFactory fact = null;

    static public EntityManagerFactory fact() {
         if (fact == null) fact = Persistence.createEntityManagerFactory("bureauPU");
         return fact; 
   }
    
}
    
  
