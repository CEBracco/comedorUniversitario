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
    private Set<Compra> compras;
    private Date fecha;
    private Double monto;
    @ManyToOne
    private Comensal comensal;
    @ManyToOne
    private Sede sede;
    private Boolean activo;

    public Ticket() {
    	this.setMonto(0.0);
    	this.setCompras(new HashSet<Compra>());
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

	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
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
		for (Compra compra : this.getCompras()) {
			if(compra.getActivo()){
				acumuladorPrecio=compra.getMonto();
			}
		}
		this.setMonto(acumuladorPrecio);
	}
	
	public void addCompra(Compra compra){
		this.getCompras().add(compra);
		if(compra.getActivo()){
			this.setMonto(this.getMonto() + compra.getMonto());
		}
	}
	
	public void removeCompra(Compra compra){
		//hacerrlo con contains
		for (Compra compraActual : this.getCompras()) {
			if(compraActual.getId()==compra.getId()){
				compra.setActivo(false);
				this.setMonto(this.getMonto() - compra.getMonto());
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