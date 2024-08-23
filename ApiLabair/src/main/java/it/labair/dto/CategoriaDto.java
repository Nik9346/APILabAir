package it.labair.dto;



public class CategoriaDto {
		private int id;
		private String descrizione;
		
		//getter e setter modello di presentazione personalizzato
		public String getDescrizione() {
			return descrizione;
		}
		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		
		

}
