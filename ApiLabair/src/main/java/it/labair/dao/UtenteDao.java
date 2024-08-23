package it.labair.dao;

import org.springframework.data.repository.CrudRepository;

import it.labair.model.Utente;

public interface UtenteDao extends CrudRepository<Utente, Integer> {

	Utente findByProfiloUsername(String userName);
	Utente findByProfiloUsernameAndProfiloPassword(String usernameString, String password);
}
