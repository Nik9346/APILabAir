package it.labair.service;

import java.util.List;


import it.labair.helper.Risposta;
import it.labair.model.Taglia;

public interface TagliaService {
	Risposta registraTaglia(Taglia taglia);
	Risposta registraTaglie(List<Taglia> taglie);
	Object elencoTaglie();
	Risposta cancellaTaglia(int id);
	Object getTagliaByNumero(int numeroTaglia);

}
