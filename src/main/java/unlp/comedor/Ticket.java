package unlp.comedor;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Ticket")
public class Ticket {

	@Id @GeneratedValue
	@Column(name="id")
    private Long id;
    @OneToMany
    private Set<Reserva> reservas;
    private Date fecha;
    private Double monto;
    @ManyToOne
    private Comensal comensal;
    @ManyToOne
    private Sede sede;
    private Boolean activo;

    public Ticket() {
    	this.setMonto(0.0);
    	this.setReservas(new HashSet<Reserva>());
    	this.setActivo(true);
    }

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
		this.calcularMonto();
	}

	public Comensal getComensal() {
		return comensal;
	}

	public void setComensal(Comensal comensal) {
		this.comensal = comensal;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void calcularMonto(){
		Double acumuladorPrecio= 0.0;
		for (Reserva reserva : this.getReservas()) {
			if(reserva.getActivo()){
				acumuladorPrecio=reserva.getMonto();
			}
		}
		this.setMonto(acumuladorPrecio);
	}
	
	public void addReserva(Reserva reserva){
		this.getReservas().add(reserva);
		if(reserva.getActivo()){
			this.setMonto(this.getMonto() + reserva.getMonto());
		}
	}
	
	public void removeReserva(Reserva reserva){
		//hacerrlo con contains
		for (Reserva reservaActual : this.getReservas()) {
			if(reservaActual.getId()==reserva.getId()){
				reserva.setActivo(false);
				this.setMonto(this.getMonto() - reserva.getMonto());
			}
		}
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
}