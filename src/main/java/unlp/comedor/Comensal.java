package unlp.comedor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

//import java.util.*;

/**
 * 
 */
@Entity
@DiscriminatorValue("Comensal")
public class Comensal extends Usuario {

    private Boolean vegetariano;
    private Boolean celiaco;
    private Boolean hipertenso;
    private Boolean intolerante;
    private Boolean diabetico;
    private Boolean regular;
    private Double saldo;
    private String mail;
    private String domicilio;
    private String cargo;
    @OneToMany(mappedBy="comensal", fetch = FetchType.EAGER)
    private Set<Ticket> tickets;
    @OneToMany(mappedBy="usuario", fetch = FetchType.EAGER)
    private Set<Sugerencia> sugerencias;

    /**
	 * @return the sugerencias
	 */
	public Set<Sugerencia> getSugerencias() {
		return sugerencias;
	}

	/**
	 * @param sugerencias the sugerencias to set
	 */
	public void setSugerencias(Sugerencia sugerencias) {
		this.sugerencias.add(sugerencias);
	}

	public Comensal() {
    	this.setTickets(new HashSet<Ticket>());
    	this.celiaco=false;
    	this.diabetico=false;
    	this.hipertenso=false;
    	this.intolerante=false;
    	this.vegetariano=false;
    	this.sugerencias=new HashSet<Sugerencia>();
    	this.setSaldo(0.0);
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


	public Boolean getRegular() {
		return regular;
	}


	public void setRegular(Boolean regular) {
		this.regular = regular;
	}


	public Double getSaldo() {
		return saldo;
	}


	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}


	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}