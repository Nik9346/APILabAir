package it.labair.dto;

import java.util.List;

import it.labair.model.Carrello;
import it.labair.model.Ordine;
import it.labair.model.Profilo;

public class UtenteDto {
	
	private int id;
	private String nome;
	private String cognome;
	private Profilo profilo;
	private List<OrdineDto> ordini;
	private List<IndirizzoDto>indirizzi;
	private CarrelloDto carrello;
	
	public CarrelloDto getCarrello() {
		return carrello;
	}
	public void setCarrello(CarrelloDto carrello) {
		this.carrello = carrello;
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
	
	public List<IndirizzoDto> getIndirizzi() {
		return indirizzi;
	}
	public void setIndirizzi(List<IndirizzoDto> indirizzi) {
		this.indirizzi = indirizzi;
	}
	public List<OrdineDto> getOrdini() {
		return ordini;
	}
	public void setOrdini(List<OrdineDto> ordini) {
		this.ordini = ordini;
	}
	
	
	
	
	


}
