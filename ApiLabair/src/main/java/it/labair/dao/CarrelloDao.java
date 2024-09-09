package it.labair.dao;

import org.springframework.data.repository.CrudRepository;


import it.labair.model.Carrello;
import it.labair.model.Utente;


public interface CarrelloDao extends CrudRepository<Carrello, Integer> {

	Carrello findByUtenteId(Integer idUtente);
}
