package it.labair.dto;

import java.util.List;

import it.labair.model.Ordine;
import it.labair.model.Profilo;

public class UtenteDto {
	
	private int id;
	private String nome;
	private String cognome;
	private Profilo profilo;
	private List<Ordine> ordini;
	private List<IndirizzoDto>indirizzi;
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
	public List<Ordine> getOrdini() {
		return ordini;
	}
	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}
	public List<IndirizzoDto> getIndirizzi() {
		return indirizzi;
	}
	public void setIndirizzi(List<IndirizzoDto> indirizzi) {
		this.indirizzi = indirizzi;
	}
	
	


}
