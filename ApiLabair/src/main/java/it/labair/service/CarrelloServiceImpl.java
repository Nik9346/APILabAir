package it.labair.service;


import java.util.List;
import java.util.Optional;

import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.labair.dao.CarrelloDao;
import it.labair.helper.Risposta;
import it.labair.model.Carrello;
import it.labair.model.ScarpaCarrello;
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
	public Object createCarrello(Utente utente, Carrello carrello) {
		if (utente != null) {
			try {
				if (carrelloDao.findByUtenteId(utente.getId()) == null) {
					carrelloDao.save(carrello);
//					return new Risposta(200, "carrello creato con successo");
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
		if (carrello != null) {
			try {
				carrelloDao.save(carrello);
				carrello.setImporto(calcoloImporto(carrello));
				return new Risposta(200, "carrello svuotato correttamente");
			} catch (Exception e) {
				return new Risposta(400, "errore in fase di svuotamento del carrello: " + e.getMessage());
			}
		}
		return new Risposta(400, "non è presente nessun oggetto carrello in richiesta");
	}

	@Override
	public Risposta updateCarrello(Carrello carrello) {
		Optional<Carrello> carrelloEsistente = carrelloDao.findById(carrello.getId());
		if (carrelloEsistente.isPresent()) {	
		try {
				Carrello carrelloUpdate = carrelloEsistente.get();
				Double importoTotale = calcoloImporto(carrello);
				carrelloUpdate.setImporto(importoTotale);
				carrelloDao.save(carrelloUpdate);
				return new Risposta(200, "carrello salvato correttamente");
			} catch (Exception e) {
				return new Risposta(400, "Errore in fase di salvataggio carrello" + e.getMessage());
			}
		}
		return new Risposta(400, "carrello non trovato");
	}

	@Override
	public Double calcoloImporto(Carrello carrello) {
		Double importoCarrello = 0.0;
		for (ScarpaCarrello item : carrello.getCarrelloItem()) {
			Double prezzoScarpa = item.getScarpa().getPrezzo();
			int quantità = item.getQuantita();
			Double subTotal = prezzoScarpa * quantità;
			importoCarrello += subTotal;
		}
		return importoCarrello;
	}

	@Override
	public Risposta clearCart(Carrello carrello) {
		if(!carrello.getCarrelloItem().isEmpty()) {
			try {
				carrello.getCarrelloItem().clear();
				carrello.setImporto(null);
				carrelloDao.save(carrello);
				return new Risposta(200, "Carrello svuotato correttamente");
			} catch (Exception e) {
				return new Risposta(400, "Errore in fase di svuotamento carrello" + e.getMessage());
			}
		}
		return new Risposta(400, "Errore, carrello non trovato");
	}

}
