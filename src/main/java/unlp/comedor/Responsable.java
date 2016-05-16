package unlp.comedor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

/**
 * 
 */
@Entity
@DiscriminatorValue("Responsable")
public class Responsable extends Usuario {

	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Sede> sedes;
	
    public Responsable() {
    	this.setSedes(new HashSet<Sede>());
    }

	public Set<Sede> getSedes() {
		return sedes;
	}
	public void setSedes(Set<Sede> sedes){
		this.sedes= sedes;
	}

	public void setSede(Sede sede) {
		this.sedes.add(sede);
	}
    
    
}