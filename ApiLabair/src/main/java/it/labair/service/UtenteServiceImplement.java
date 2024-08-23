package it.labair.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import it.labair.dao.UtenteDao;
import it.labair.helper.GeneratoreToken;
import it.labair.helper.Risposta;
import it.labair.model.Utente;
import jakarta.servlet.http.HttpSession;

@Service
public class UtenteServiceImplement implements UtenteService {

	@Autowired
	UtenteDao utenteDao;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	GeneratoreToken generatoreToken;

	@Override
	public Risposta registraUtente(Utente utente) {
		if (utente != null) {
			String utenteControlloEsistenzaUsername = utente.getProfilo().getUsername();
			if (controlloUsername(utenteControlloEsistenzaUsername)) {
				try {
					utente.getProfilo().setPassword(encoder.encode(utente.getProfilo().getPassword()));
					utenteDao.save(utente);
					return new Risposta(200, "Utente registrato correttamente");
				} catch (Exception e) {
					return new Risposta(400, "errore durante la registrazione dell'utente" + e.getMessage());
				}
			} else {
				return new Risposta(409, "Username già in uso");
			}
		} else {
			return new Risposta(400, "nessun utente da registrare nella richiesta");
		}
	}

	@Override
	public boolean controlloUsername(String userName) {
		if (utenteDao.findByProfiloUsername(userName) == null)
			return true;
		return false;
	}

	@Override
	public boolean controlloLogin(String userName, String password, HttpSession session) {
		Utente utente = utenteDao.findByProfiloUsernameAndProfiloPassword(userName, password);
		if (utente != null) {
			session.setAttribute("utente", utente);
			return true;
		}
		return false;
	}

	@Override
	public Risposta cancellaUtente(int id) {
		Utente utente = utenteDao.findById(id).get();
		if (utente != null) {
			try {
				utenteDao.deleteById(id);
				return new Risposta(200, "Utente cancellato con successo");
			} catch (Exception e) {
				return new Risposta(400, "Utente con id: " + id + " non trovato " + e.getMessage());
			}
		}
		return new Risposta(400, "Errore durante la cancellazione dell'utente con id: " + id);
	}

	@Override
	public Object utenteById(int id) {
		try {
			Utente utente = utenteDao.findById(id).get();
			if (utente != null)
				return utente;
		} catch (Exception e) {
			if (e instanceof NoSuchElementException)
				return new Risposta(404, "utente non trovato");
		}
		return new Risposta(400, "errore durante la ricerca dell'utente con id: " + id);

	}

	@Override
	public Object utenteByUsername(String userName) {
		if(userName != null) {
			try {
				Utente utente = utenteDao.findByProfiloUsername(userName);
				if(utente !=null)
				return utente;
			return new Risposta(404, "Utente con Username: " + userName + " non trovato");
			} catch (Exception e) {
				return new Risposta(400, "Errore in fase di ricerca utente con UserName: " + userName + " non trovato");
			}
		}
		return new Risposta(400, "Errore in fase di richiesta utente con UserName: " + userName);
	}

	@Override
	public Risposta loginUtente(String userName, String password) {
		Utente utente = utenteDao.findByProfiloUsername(userName);
		if(utente != null && encoder.matches(password, utente.getProfilo().getPassword())) {
			utente.getProfilo().setToken(generatoreToken.generazioneToken(utente.getProfilo().getUsername()));
			utenteDao.save(utente);
			return new Risposta(200, utente.getProfilo().getToken());
		}
		return new Risposta(401, "Non autorizzato");
	}

	@Override
	public Risposta logoutUtente(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
