package it.labair.dto;


public class TagliaDto {
	
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int taglia;

	public int getTaglia() {
		return taglia;
	}

	public void setTaglia(int taglia) {
		this.taglia = taglia;
	}
	
	@Override
	public String toString() {
		return Integer.toString(taglia);
	}
	




}
