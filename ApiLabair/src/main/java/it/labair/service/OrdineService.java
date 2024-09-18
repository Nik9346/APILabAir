package it.labair.service;

import it.labair.helper.Risposta;
import it.labair.model.Carrello;
import jakarta.servlet.http.HttpServletRequest;

public interface OrdineService {
	
	Risposta aggiungiOrdine(Carrello carrello, HttpServletRequest request);

}
