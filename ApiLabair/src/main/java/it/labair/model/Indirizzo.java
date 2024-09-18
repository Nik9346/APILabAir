package it.labair.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Indirizzi")
public class Indirizzo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String indirizzo;

	@Column
	private String civico;

	@Column
	private String cap;

	@Column
	private String citta;

	@Column
	private String provincia;

	@Column
	private String paese;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="p_utente", referencedColumnName = "id")
	private Utente utente;
	
	@OneToOne(mappedBy = "indirizzo", cascade = CascadeType.REFRESH)
	Ordine ordine;

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	
	
}
