package it.labair.service;

import it.labair.helper.Risposta;
import it.labair.model.Carrello;
import it.labair.model.Utente;

public interface CarrelloService {
	
	Object getCarrello(Integer idUtente);
	Risposta createCarrello(Utente utente);
	Risposta deleteCarrello(Carrello carrello);
	Risposta updateCarrello(Carrello carrello);
	
}
