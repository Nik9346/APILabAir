package it.labair.service;

import it.labair.helper.Risposta;
import it.labair.model.Scarpa;

public interface ScarpaService {
	Risposta registraScarpa(Scarpa scarpa);
	Object elencoScarpe();
	Object getScarpaById(int id);
	Object getScarpaByColore(String nomeColore);
	Risposta cancellaScarpa(int id);
	Object getScarpaByIdForCart(int id);
	

}
