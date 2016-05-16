package unlp.comedor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Reserva")
public class Reserva {

	@Id @GeneratedValue
	@Column(name="id")
    private Long id;
    private Boolean vianda;
    private Boolean retirado;
    @ManyToOne
    private Dia dia;
    @ManyToOne
    private Menu menu;
    private Boolean activo;

    public Reserva() {
    	this.setVianda(false);
    	this.setRetirado(false);
    	this.setActivo(true);
    }
    

	public Menu getMenu() {
		return menu;
	}


	public void setMenu(Menu menu) {
		this.menu = menu;
	}


	public Boolean getVianda() {
		return vianda;
	}


	public void setVianda(Boolean vianda) {
		this.vianda = vianda;
	}


	public Boolean getRetirado() {
		return retirado;
	}


	public void setRetirado(Boolean retirado) {
		this.retirado = retirado;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	public Dia getDia() {
		return dia;
	}


	public void setDia(Dia dia) {
		this.dia = dia;
	}
	
	public Double getMonto(){
		return this.getDia().getCartilla().getPrecio();
	}

}