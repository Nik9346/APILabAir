package it.labair.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name="carrello_scarpe")
public class ScarpaCarrello {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "p_scarpa",referencedColumnName = "id")
	private Scarpa scarpa;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "p_carrello",referencedColumnName = "id")
	private Carrello carrello;
	
	@ManyToOne
	@JoinColumn(name = "p_colore",referencedColumnName = "id")
	private Colore colore;
	
	@ManyToOne
	@JoinColumn(name="p_taglia",referencedColumnName = "id")
	private Taglia taglia;
	
	@Column
	private int quantita;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Scarpa getScarpa() {
		return scarpa;
	}

	public void setScarpa(Scarpa scarpa) {
		this.scarpa = scarpa;
	}

	public Carrello getCarrello() {
		return carrello;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	public Colore getColore() {
		return colore;
	}

	public void setColore(Colore colore) {
		this.colore = colore;
	}

	public Taglia getTaglia() {
		return taglia;
	}

	public void setTaglia(Taglia taglia) {
		this.taglia = taglia;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

}
