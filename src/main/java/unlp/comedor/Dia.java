package unlp.comedor;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Dia")
public class Dia {

	@Id @GeneratedValue
	@Column(name="id")
    private Long id;
    private String nombre;
    @ManyToOne
    private Cartilla cartilla;
    @ManyToMany
    private Set<Menu> menus;

    public Dia() {
    	this.setMenu(new HashSet<Menu>());
    }

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenu(Set<Menu> menus) {
		this.menus = menus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cartilla getCartilla() {
		return cartilla;
	}

	public void setCartilla(Cartilla cartilla) {
		this.cartilla = cartilla;
	}


}