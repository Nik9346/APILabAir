package it.labair.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.labair.model.Indirizzo;
import it.labair.model.Utente;


public interface IndirizzoDao extends CrudRepository<Indirizzo, Integer>{
	List<Indirizzo> findByUtente(Utente utente);
}
