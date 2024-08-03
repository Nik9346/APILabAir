package it.labair.service;

import java.util.List;

import it.labair.dto.ScarpaDto;
import it.labair.model.Scarpa;

public interface ScarpaService {
	void registraScarpa(Scarpa scarpa);
	
	List<ScarpaDto> elencoScarpe();
	
	Scarpa getScarpaById(int id);
	
	Scarpa getScarpaByColore(String nomeColore);
	
	void cancellaScarpa(int id);
	

}
