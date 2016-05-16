package unlp.comedor;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
    public String respuesta;
    
    @ManyToOne(optional = true)
    @JoinColumn(name="usuario_id")
    private Comensal usuario;
    
    @ManyToMany
    private Set<Sede> sede;
    
    
    private Boolean activo;

    public void setRespuesta(String respuesta){
    	this.respuesta=respuesta;
    }
    public String getRespuesta(){
    	return this.respuesta;
    }
     public Sugerencia() {
    	 this.setActivo(true);
    	 this.sede = new HashSet<Sede>();
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



	public Comensal getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario =(Comensal)usuario;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	public Set<Sede> getSede() {
		return sede;
	}


	public void setSede(Sede sede) {
		this.sede.add(sede);
	}

}