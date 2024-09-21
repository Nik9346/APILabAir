package it.labair.service;

import it.labair.helper.Risposta;
import it.labair.model.Utente;
import jakarta.servlet.http.HttpSession;

public interface UtenteService {
	
	Object utenteById(int id);
	Object utenteByUsername(String userName);
	
	boolean controlloUsername(String userName);
	boolean controlloLogin(String userName, String password, HttpSession session);
	
	Risposta registraUtente(Utente utente);
	Risposta cancellaUtente(int id);

	Risposta loginUtente(String userName, String password);
	Risposta logoutUtente(String token);
	
	Object getUtenteByToken(String token);
}
