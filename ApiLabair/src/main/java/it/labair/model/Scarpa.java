package it.labair.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Scarpe")
public class Scarpa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String nome;
	@Column
	private double prezzo;
	@Column
	private String descrizione;
	@Column
	private String immagine;
	@Column
	private Boolean nuovoArrivi;
	@Column
	private int bestSeller;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
				name = "scarpe_taglie",
				joinColumns = @JoinColumn(name="id_scarpa",referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name="id_taglia",referencedColumnName = "id")
				)
	private List<Taglia> taglie;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
				name = "scarpe_colori",
				joinColumns = @JoinColumn(name="id_scarpa",referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name="id_colore",referencedColumnName = "id")
				)
	private List<Colore> colori;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="pk_categoria",referencedColumnName = "id")
	private Categoria categoria;

	//Getter e Setter
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


	public List<Taglia> getTaglie() {
		return taglie;
	}

	public void setTaglie(List<Taglia> taglie) {
		this.taglie = taglie;
	}

	public List<Colore> getColori() {
		return colori;
	}

	public void setColori(List<Colore> colori) {
		this.colori = colori;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
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
