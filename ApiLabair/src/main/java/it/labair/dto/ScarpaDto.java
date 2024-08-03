package it.labair.dto;

import java.util.List;


public class ScarpaDto {
	

	private int id;
	private String nome;
	private double prezzo;
	private String descrizione;
	private String immagine;
	private List<String> taglie;
	private List<String> colori;
	private String categoria;
	private Boolean nuovoArrivi;
	private int bestSeller;
	
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
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	
	public List<String> getTaglie() {
		return taglie;
	}
	public void setTaglie(List<String> taglie) {
		this.taglie = taglie;
	}
	public List<String> getColori() {
		return colori;
	}
	public void setColori(List<String> colori) {
		this.colori = colori;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Boolean getNuovoArrivi() {
		return nuovoArrivi;
	}
	public void setNuovoArrivi(Boolean nuovoArrivi) {
		this.nuovoArrivi = nuovoArrivi;
	}
	public int getBestSeller() {
		return bestSeller;
	}
	public void setBestSeller(int bestSeller) {
		this.bestSeller = bestSeller;
	}
	
	
	

}
