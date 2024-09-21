package it.labair.dto;


import java.util.List;




public class CarrelloDto {

	
	private int id;
//	@JsonManagedReference
	private List<ScarpaCarrelloDto> carrelloItem;
	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	private double importo;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<ScarpaCarrelloDto> getCarrelloItem() {
		return carrelloItem;
	}

	public void setCarrelloItem(List<ScarpaCarrelloDto> carrelloItem) {
		this.carrelloItem = carrelloItem;
	}
	

	
	
}
