package it.labair.service;

import it.labair.helper.Risposta;
import it.labair.model.Scarpa;

public interface ScarpaService {
	Risposta registraScarpa(Scarpa scarpa);
	Object elencoScarpe();
	Object getScarpaById(Integer id);
	Object getScarpaByColore(String nomeColore);
	Object getScarpeByNuovoArrivo(boolean nuovoArrivo);
	Object getScarpeByCategoria(String categoria);
	Risposta cancellaScarpa(Integer id);
	Object getScarpaByIdForCart(Integer id);
	

}
