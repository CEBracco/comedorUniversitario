package unlp.comedor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name="Usuario")
@Inheritance
@DiscriminatorColumn(name="tipo")
public class Usuario {

	@Id @GeneratedValue
	@Column(name="id")
    private Long id;
    private String password;
    private Integer dni;
    private String nombre;
    private String apellido;
    private String foto;
    private String rol;
   

	@OneToMany
    private Set<Sugerencia> sugerencias;
    private Boolean activo;

    public Usuario() {
    	this.setSugerencias(new HashSet<Sugerencia>());
    	this.setActivo(true);
    }

	public String getPassword() {
		return password;
	}
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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

	public Set<Sugerencia> getSugerencias() {
		return sugerencias;
	}

	public void setSugerencias(Set<Sugerencia> sugerencias) {
		this.sugerencias = sugerencias;
	}


}