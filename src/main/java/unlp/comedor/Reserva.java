package unlp.comedor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    @ManyToOne
    private Menu menu;
    @ManyToOne
    private Dia dia;
    public Dia getDia() {
		return dia;
	}

	public void setDia(Dia dia) {
		this.dia = dia;
	}

	private Boolean activo;

    public Reserva() {
    	this.setVianda(false);
    	this.setRetirado(false);
    	this.setActivo(true);
    }
    
    public Reserva(Ticket ticket, Date fecha, Menu menu) {
    	this();
    	this.setFecha(fecha);
    	this.setTicket(ticket);
    	this.setMenu(menu);
    }

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
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
	
	public Double getMonto(){
		return this.getTicket().getCartilla().getPrecio();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}