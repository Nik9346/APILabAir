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
@Table(name = "taglie")
public class Taglia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private Integer taglia;
	
	@ManyToMany(mappedBy = "taglie")
	private List<Scarpa> scarpe;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getTaglia() {
		return taglia;
	}

	public void setTaglia(Integer taglia) {
		this.taglia = taglia;
	}

	public List<Scarpa> getScarpe() {
		return scarpe;
	}

	public void setScarpe(List<Scarpa> scarpe) {
		this.scarpe = scarpe;
	}

	@Override
	public String toString() {
		return Integer.toString(taglia);
	}
}
