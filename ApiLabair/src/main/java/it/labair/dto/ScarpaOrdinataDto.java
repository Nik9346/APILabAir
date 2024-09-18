package it.labair.dto;


public class ScarpaOrdinataDto {
	
	private int id;
	private int quantita;
	private ScarpaDto scarpa;
	private String colore;
	private int taglia;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
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
}
