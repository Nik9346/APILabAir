package it.labair.service;

import it.labair.helper.Risposta;
import it.labair.model.Ordine;
import it.labair.model.ScarpaCarrello;

public interface ScarpaOrdinataService {
	Risposta aggiuntaScarpaOrdinata(ScarpaCarrello scarpaCarrello, Ordine ordine);
}
