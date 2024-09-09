package it.labair.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.labair.dao.CarrelloDao;
import it.labair.helper.Risposta;
import it.labair.model.Carrello;
import it.labair.model.Utente;

@Service
public class CarrelloServiceImpl implements CarrelloService {

	@Autowired
	CarrelloDao carrelloDao;

	@Override
	public Object getCarrello(Integer idUtente) {
		if (idUtente != null) {
			try {
				Carrello carrello = carrelloDao.findByUtenteId(idUtente);
				return carrello != null ? carrello : null;
			} catch (Exception e) {
				return new Risposta(400, "errore in fase di richiesta del carrello" + e.getMessage());
			}
		}
		return new Risposta(400, "non è presente nessun idUtente in richiesta");
	}

	@Override
	public Risposta createCarrello(Utente utente) {
		if (utente != null) {
			Carrello carrello;
			try {
				if (carrelloDao.findByUtenteId(utente.getId()) == null) {
					carrello = new Carrello();
					carrello.setUtente(utente);
					carrelloDao.save(carrello);
					return new Risposta(200, "carrello creato con successo");
				}
				return new Risposta(409, "l'utente ha già un carrello");
			} catch (Exception e) {
				return new Risposta(400, "errore in fase di creazione del carrello" + e.getMessage());
			}
		}
		return new Risposta(400, "non è presente nessun oggetto utente in richiesta");

	}

	@Override
	public Risposta deleteCarrello(Carrello carrello) {
		if(carrello != null) {
			try {
//				carrello.getCarrelloItem().clear();
				carrelloDao.save(carrello);
				return new Risposta(200, "carrello svuotato correttamente");				
			} catch (Exception e) {
				return new Risposta(400, "errore in fase di svuotamento del carrello: "+e.getMessage());
			}
		}
		return new Risposta(400, "non è presente nessun oggetto carrello in richiesta");
	}

	@Override
	public Risposta updateCarrello(Carrello carrello) {
		if(carrelloDao.findById(carrello.getId())!= null) {
			try {
				carrelloDao.save(carrello);
				return new Risposta(200, "carrello salvato correttamente");
			} catch (Exception e) {
				return new Risposta(400, "Errore in fase di salvataggio carrello" + e.getMessage());
			}
		}
		return new Risposta(400, "carrello non trovato");
	}

}
