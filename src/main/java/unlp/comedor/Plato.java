package unlp.comedor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name="Plato")
public class Plato {

	@Id @GeneratedValue
	@Column(name="id")
	private Long id;
    private String nombre;
    private Boolean vegetariano;
    private Boolean celiaco;
    private Boolean hipertenso;
    private Boolean intolerante;
    private Boolean diabetico;
    private Boolean activo;

    public Plato() {
    	this.setCeliaco(false);
    	this.setDiabetico(false);
    	this.setHipertenso(false);
    	this.setIntolerante(false);
    	this.setVegetariano(false);
    	this.setActivo(true);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getVegetariano() {
		return vegetariano;
	}

	public void setVegetariano(Boolean vegetariano) {
		this.vegetariano = vegetariano;
	}

	public Boolean getCeliaco() {
		return celiaco;
	}

	public void setCeliaco(Boolean celiaco) {
		this.celiaco = celiaco;
	}

	public Boolean getHipertenso() {
		return hipertenso;
	}

	public void setHipertenso(Boolean hipertenso) {
		this.hipertenso = hipertenso;
	}

	public Boolean getIntolerante() {
		return intolerante;
	}

	public void setIntolerante(Boolean intolerante) {
		this.intolerante = intolerante;
	}

	public Boolean getDiabetico() {
		return diabetico;
	}

	public void setDiabetico(Boolean diabetico) {
		this.diabetico = diabetico;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}