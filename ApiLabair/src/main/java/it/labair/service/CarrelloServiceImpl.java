package it.labair.service;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.labair.dao.CarrelloDao;
import it.labair.dto.CarrelloDto;
import it.labair.helper.ControlloCookie;
import it.labair.helper.Risposta;
import it.labair.model.Carrello;
import it.labair.model.ScarpaCarrello;
import it.labair.model.Utente;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CarrelloServiceImpl implements CarrelloService {

	@Autowired
	CarrelloDao carrelloDao;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	UtenteService utenteService;
	
	@Autowired
	ControlloCookie controlloCookie;
	
	
	//Funzione utilizzata per ottenere il carrello relativo all'idUtente passato, verificando la validità del token
	@Override
	public Object getCarrello(Integer idUtente, HttpServletRequest request) {
		if(idUtente != null && request != null) {
			String token = controlloCookie.getSessionId(request);
			if(token != null) {
				Utente utente = (Utente) utenteService.getUtenteByToken(token);
				if(utente != null && utente.getId() == idUtente) {					
					try {
						Carrello carrello = carrelloDao.findByUtenteId(idUtente);
						return carrello != null ? carrello : null;
					} catch (Exception e) {
						return new Risposta(400, "errore in fase di richiesta del carrello" + e.getMessage());
					}
				}
				return new Risposta(400, "Errore in fase di controllo utente"); 
			}
			return new Risposta(400, "Non autorizzato, Token non valido");
		}
		return new Risposta(400, "Errore in fase di richiesta, idUtente o token non validi");
	}

	//Funzione utilizzata per la creazione del carrello per il relativo utente
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

	//Funzione utilizzata per cancellare un determinato carrello
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

	//Funzione utilizzata per aggiornare il carrello
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
	
	//Funzione utilizzata per calcolare l'importo totale del carrello
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

	//Funzione utilizzata per svuotare il carrello
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
	
	//Funzione utilizzata per verificare l'integrità dei dati del carrello in fase di richiesta ordine
	public Object getCarrelloForVerify(Integer idUtente, HttpServletRequest request) {
		if(idUtente != null && request != null) {
			String token = controlloCookie.getSessionId(request);
			if(token != null) {
				Utente utente = (Utente) utenteService.getUtenteByToken(token);
				if(utente != null && utente.getId() == idUtente) {					
					try {
						Carrello carrello = carrelloDao.findByUtenteId(idUtente);
						CarrelloDto carrelloDto = mapper.map(carrello, CarrelloDto.class);
						return carrelloDto != null ? carrelloDto : null;
					} catch (Exception e) {
						return new Risposta(400, "errore in fase di richiesta del carrello" + e.getMessage());
					}
				}
				return new Risposta(400, "Errore in fase di controllo utente"); 
			}
			return new Risposta(400, "Non autorizzato, Token non valido");
		}
		return new Risposta(400, "Errore in fase di richiesta, idUtente o token non validi");
	}

}
