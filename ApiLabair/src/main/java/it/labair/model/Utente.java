package it.labair.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "utenti")
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String nome;
	@Column
	private String cognome;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pk_profilo",referencedColumnName = "id")
	private Profilo profilo;
	
	@OneToMany(mappedBy = "utente")
	private List<Ordine> ordini;
	
	
	//forse cambiare in uno ad uno un utente può avere un indirizzo
	@OneToMany(mappedBy = "utente", cascade = CascadeType.PERSIST)
	private List<Indirizzo>indirizzi = new ArrayList<>(); 
	
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "utente")
	@JsonManagedReference
	private Carrello carrello;

	public Carrello getCarrello() {
		return carrello;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

	public List<Indirizzo> getIndirizzi() {
		return indirizzi;
	}

	public void setIndirizzi(List<Indirizzo> indirizzi) {
		this.indirizzi = indirizzi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Profilo getProfilo() {
		return profilo;
	}

	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}
}
