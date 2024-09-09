package it.labair.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;




public class CarrelloDto {

	
	private int id;
//	@JsonManagedReference
	private List<ScarpaCarrelloDto> carrelloItem;

	
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
