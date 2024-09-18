package it.labair.dao;

import org.springframework.data.repository.CrudRepository;

import it.labair.model.Utente;
import java.util.List;
import it.labair.model.Indirizzo;


public interface UtenteDao extends CrudRepository<Utente, Integer> {

	Utente findByProfiloUsername(String userName);
	Utente findByProfiloUsernameAndProfiloPassword(String usernameString, String password);
	Utente findByProfiloToken(String token);
	List<Utente> findByIndirizzi(List<Indirizzo> indirizzi);
}
