package it.labair.service;

import java.util.List;

import it.labair.dto.ColoreDto;
import it.labair.model.Colore;

public interface ColoreService {
	
	void registraColore(Colore colore);
	List<ColoreDto> elencocolori();
	String registraColori(List<Colore> colori);
	void cancellaColore(int id);
	String getColoreByNomeColore(String nomeColore); 

}
