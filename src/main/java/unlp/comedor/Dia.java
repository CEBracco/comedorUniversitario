package unlp.comedor;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @ManyToOne
    private Cartilla cartilla;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Menu> menus;

    public Dia() {
    	this.setMenu(new HashSet<Menu>());
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