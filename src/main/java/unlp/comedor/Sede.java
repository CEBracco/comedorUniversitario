package unlp.comedor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Sede")
public class Sede {

	@Id @GeneratedValue
	@Column(name="id")
    private Long id;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String mail;
    @ManyToMany
    private Set<Cartilla> cartillas;
    
    @ManyToMany(mappedBy="sede", fetch = FetchType.EAGER)
    private Set<Sugerencia> sugerencias;
    
    @ManyToMany(mappedBy="sedes", fetch = FetchType.EAGER)
    private Set<Responsable> responsables;
   

	private Boolean activo;
    
    public Sede() {
    	this.setResponsables(new HashSet<Responsable>());
    	this.setCartillas(new HashSet<Cartilla>());
    	this.setActivo(true);
    	this.sugerencias = new HashSet<Sugerencia>();
    }
    /**
   	 * @return the sugerencias
   	 */
   	public Set<Sugerencia> getSugerencias() {
   		return sugerencias;
   	}


   	/**
   	 * @param sugerencias the sugerencias to set
   	 */
   	
	public void setSugerencia(Sugerencia sugerencia) {
   		this.sugerencias.add(sugerencia);
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

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Set<Cartilla> getCartillas() {
		return cartillas;
	}

	public void setCartillas(Set<Cartilla> cartillas) {
		this.cartillas = cartillas;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Set<Responsable> getResponsables() {
		return responsables;
	}

	public void setResponsables(Set<Responsable> responsables) {
		this.responsables = responsables;
	}



}