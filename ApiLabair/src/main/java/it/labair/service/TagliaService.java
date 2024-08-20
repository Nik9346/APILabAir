package it.labair.service;

import java.util.List;

import it.labair.model.Taglia;

public interface TagliaService {
	
	String registraTaglia(Taglia taglia);
	String registraTaglie(List<Taglia> taglie);
	void cancellaTaglia(int id);
	Integer getTagliaByNumero(int numeroTaglia);

}
