package it.labair.service;

import java.util.List;
import it.labair.helper.Risposta;
import it.labair.model.Colore;

public interface ColoreService {
	
	Risposta registraColore(Colore colore);
	Object elencocolori();
	Risposta registraColori(List<Colore> colori);
	Risposta cancellaColore(int id);
	Object getColoreByNomeColore(String nomeColore);
	Object getColoreById(Integer idColore);

}
