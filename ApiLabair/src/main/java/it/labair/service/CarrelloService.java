package it.labair.service;

import it.labair.helper.Risposta;
import it.labair.model.Carrello;
import it.labair.model.Utente;
import jakarta.servlet.http.HttpServletRequest;

public interface CarrelloService {
	
	Object getCarrello(Integer idUtente, HttpServletRequest request);
	Object getCarrelloForVerify(Integer idUtente, HttpServletRequest request);
	Object createCarrello(Utente utente, Carrello carrello);
	Risposta deleteCarrello(Carrello carrello);
	Risposta updateCarrello(Carrello carrello);
	Risposta clearCart(Carrello carrello);
	Double calcoloImporto(Carrello carrello);
}
