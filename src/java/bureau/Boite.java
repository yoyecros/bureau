/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bureau;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Nicolas Singer <Nicolas.Singer@gmail.com>
 */
@Entity
public class Boite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    
    /* l'annotation cascade = CascadeType.MERGE garantit que la mise à jour de l'objet
    se fera en mettant aussi à jour ses sous-objets. Par contre l'effacement de l'objet
    n'effacera pas ses sous-objets. Si on souhaite cela il faut mettre cascade = CascadeType.ALL.
    */ 
    @Column
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    List<Crayon> crayons;

    public List<Crayon> getCrayons() {
        return crayons;
    }

    public void setCrayons(List<Crayon> crayons) {
        this.crayons = crayons;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boite)) {
            return false;
        }
        Boite other = (Boite) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bureau.Boite[ id=" + id + " ]";
    }
    
}
