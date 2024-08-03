package it.labair.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String descrizione;
	
	@OneToMany(mappedBy = "categoria",fetch = FetchType.EAGER,orphanRemoval = true)
	private List<Scarpa> scarpe;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public List<Scarpa> getScarpe() {
		return scarpe;
	}
	public void setScarpe(List<Scarpa> scarpe) {
		this.scarpe = scarpe;
	}
	@Override
	public String toString() {
		return descrizione;
	}
	
	
	
}
