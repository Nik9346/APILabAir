package it.labair.service;

import it.labair.helper.Risposta;
import it.labair.model.Ordine;
import jakarta.servlet.http.HttpServletRequest;

public interface OrdineService {
	
	Risposta addOrder(Ordine ordine, HttpServletRequest request);

}
