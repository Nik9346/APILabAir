package it.labair.service;

import it.labair.helper.Risposta;
import it.labair.model.ScarpaCarrello;
import jakarta.servlet.http.HttpServletRequest;


public interface ScarpaCarrelloService {
	Object getScarpaCarrelloNotLogged(Integer idScarpa, Integer idColore, Integer idTaglia);
	Risposta aggiuntaScarpa(ScarpaCarrello scarpa, HttpServletRequest request);
	Risposta rimozioneScarpa(Integer idScarpa,HttpServletRequest request);
	Risposta modificaScarpa(ScarpaCarrello scarpaCarrello, HttpServletRequest request);
	Object getScarpe(HttpServletRequest request);
}
