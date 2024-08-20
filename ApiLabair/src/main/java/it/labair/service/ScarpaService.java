package it.labair.service;

import java.util.List;

import it.labair.dto.ScarpaDto;
import it.labair.model.Categoria;
import it.labair.model.Colore;
import it.labair.model.Scarpa;
import it.labair.model.Taglia;

public interface ScarpaService {
	void registraScarpa(Scarpa scarpa);
	
	List<ScarpaDto> elencoScarpe();
	
	ScarpaDto getScarpaById(int id);
	
	ScarpaDto getScarpaByColore(String nomeColore);
	
	void cancellaScarpa(int id);
	

}
