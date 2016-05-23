package unlp.comedor;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Recarga")
public class Recarga {
	
	@Id @GeneratedValue
	@Column(name="id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "comensal_id")
	Comensal comensal;
	Date fecha;
	@ManyToOne
	@JoinColumn(name = "sede_id")
	Sede sede;
	Double monto;
	
	public Recarga(){
		this.fecha=Calendar.getInstance().getTime();
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
	public Comensal getComensal() {
		return comensal;
	}
	public void setComensal(Comensal comensal) {
		this.comensal = comensal;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
}
