package unlp.comedor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 */
@Entity
@DiscriminatorValue("Administrador")
public class Administrador extends Responsable {

    public Administrador() {
    }

}