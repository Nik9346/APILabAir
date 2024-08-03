package it.labair.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "colori")
public class Colore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String colore;
	
	@ManyToMany(mappedBy = "colori")
	private List<Scarpa> scarpe;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public List<Scarpa> getScarpe() {
		return scarpe;
	}

	public void setScarpe(List<Scarpa> scarpe) {
		this.scarpe = scarpe;
	}

	@Override
	public String toString() {
		return colore;
	}
	
	
	

}
