package it.labair.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordini")
public class Ordine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private LocalDate data;
	
	@Column
	private double speseSpedizione;
	
	@Column
	private double importo;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "p_utente",referencedColumnName = "id")
	private Utente utente;
	
	@OneToMany(
			mappedBy = "ordine",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<ScarpaOrdinata> scarpeOrdinate = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "p_indirizzo", referencedColumnName = "id")
	private Indirizzo indirizzo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "p_pagamento", referencedColumnName = "id")
	private Pagamento pagamento;

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo, double speseSpedizione) {
		this.importo = importo + speseSpedizione;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<ScarpaOrdinata> getScarpeOrdinate() {
		return scarpeOrdinate;
	}

	public void setScarpeOrdinate(List<ScarpaOrdinata> scarpeOrdinate) {
		this.scarpeOrdinate = scarpeOrdinate;
	}

	public double getSpeseSpedizione() {
		return speseSpedizione;
	}

	public void setSpeseSpedizione(double speseSpedizione) {
		this.speseSpedizione = speseSpedizione;
	}
	
	
}
