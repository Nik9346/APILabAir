package it.labair.dao;

import org.springframework.data.repository.CrudRepository;

import it.labair.model.Ordine;
import it.labair.model.Utente;

import java.util.List;


public interface OrdineDao extends CrudRepository<Ordine, Integer> {
	
	List<Ordine> findByUtente(Utente Utente);
}
