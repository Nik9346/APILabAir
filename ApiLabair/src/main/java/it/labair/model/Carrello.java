package it.labair.model;



import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrello")
public class Carrello {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="p_utente",referencedColumnName = "id")
	@JsonBackReference
	private Utente utente;
	
	
	@OneToMany(mappedBy = "carrello",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
	private List<ScarpaCarrello> carrelloItem = new ArrayList<>();
	public List<ScarpaCarrello> getCarrelloItem() {
		return carrelloItem;
	}

	public void setCarrelloItem(List<ScarpaCarrello> carrelloItem) {
		this.carrelloItem = carrelloItem;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	@Column
	private Double importo;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}
	
}