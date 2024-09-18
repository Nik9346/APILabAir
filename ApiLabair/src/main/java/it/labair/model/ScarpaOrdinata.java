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
@Table(name = "scarpe_ordinate")
public class ScarpaOrdinata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int quantita;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "pk_scarpa", referencedColumnName = "id")
	private Scarpa scarpa;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pk_ordine", referencedColumnName = "id")
	private Ordine ordine;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "p_colore",referencedColumnName = "id")
	private Colore colore;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "p_taglia",referencedColumnName = "id")
	private Taglia taglia;

	//Getter e Setter
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

	public Scarpa getScarpa() {
		return scarpa;
	}

	public void setScarpa(Scarpa scarpa) {
		this.scarpa = scarpa;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
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
	

}
