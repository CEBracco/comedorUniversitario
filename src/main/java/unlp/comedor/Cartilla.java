package unlp.comedor;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Cartilla")
public class Cartilla {

	@Id @GeneratedValue
	@Column(name="id")
	private Long id;
    private Double precio;   
    private Date inicio;
    private Date fin;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Dia> semana;
    private Boolean activo;
    
    public Cartilla() {
    	this.setSemana(new LinkedList<Dia>());
    	this.setActivo(true);
    }

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public List<Dia> getSemana() {
		return semana;
	}

	public void setSemana(List<Dia> semana) {
		this.semana = semana;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}