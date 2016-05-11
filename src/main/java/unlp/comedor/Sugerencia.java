package unlp.comedor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name="Sugerencia")
public class Sugerencia {

	@Id @GeneratedValue
	@Column(name="id")
    private Long id;
    private String sugerencia;
    public String tipo;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Sede sede;
    private Boolean activo;

    
    public Sugerencia() {
    	 this.setActivo(true);
    }

    
    public Long getId() {
    	return id;
    }
    
   
    public void setId(Long id) {
    	this.id = id;
    }

	public String getSugerencia() {
		return sugerencia;
	}


	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	public Sede getSede() {
		return sede;
	}


	public void setSede(Sede sede) {
		this.sede = sede;
	}

}