package it.labair.dto;

public class ScarpaCarrelloDto {

	private int id;

	private ScarpaDto scarpa;
	
	private String colore;
	
	private int taglia;

	private int quantita;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ScarpaDto getScarpa() {
		return scarpa;
	}

	public void setScarpa(ScarpaDto scarpa) {
		this.scarpa = scarpa;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public int getTaglia() {
		return taglia;
	}

	public void setTaglia(int taglia) {
		this.taglia = taglia;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
}
