package it.labair.service;

import it.labair.helper.Risposta;
import it.labair.model.Indirizzo;
import it.labair.model.Utente;
import jakarta.servlet.http.HttpServletRequest;

public interface IndirizzoService {
	
	Risposta registraIndirizzo(Indirizzo indirizzo, HttpServletRequest request);
	Object elencoIndirizzi();
	Risposta cancellaIndirizzo(Indirizzo indirizzo, HttpServletRequest request);
	Risposta modificaIndirizzo(Indirizzo indirizzo, HttpServletRequest request);
	Object getIndirizziByUtente(Utente utente, HttpServletRequest request);
}
