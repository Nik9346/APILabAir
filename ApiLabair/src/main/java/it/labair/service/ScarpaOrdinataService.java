package it.labair.service;

import it.labair.helper.Risposta;
import it.labair.model.Carrello;
import it.labair.model.Ordine;

public interface ScarpaOrdinataService {
	Risposta aggiuntaScarpaOrdinata(Ordine ordine, Carrello carrello);
}
