package it.labair.service;
import it.labair.helper.Risposta;
import it.labair.model.Indirizzo;

public interface IndirizzoService {
	
	Risposta registraIndirizzo(Indirizzo indirizzo);
	Object elencoIndirizzi();
	Risposta cancellaIndirizzo(int id);
	Risposta modificaIndirizzo(Indirizzo indirizzo);
}
