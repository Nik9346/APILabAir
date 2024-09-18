package it.labair.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.labair.dao.OrdineDao;
import it.labair.helper.ControlloCookie;
import it.labair.helper.Risposta;
import it.labair.model.Carrello;
import it.labair.model.Ordine;
import it.labair.model.ScarpaCarrello;
import it.labair.model.Utente;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class OrdineServiceImpl implements OrdineService {

	@Autowired
	OrdineDao ordineDao;

	@Autowired
	ScarpaOrdinataService scarpaOrdinataService;
	
	@Autowired
	CarrelloService carrelloService;
	
	@Autowired
	ScarpaCarrelloService scarpaCarrelloService;

	@Autowired
	ControlloCookie controlloCookie;

	@Autowired
	UtenteService utenteService;

	@Override
	public Risposta aggiungiOrdine(Carrello carrello, HttpServletRequest request) {

		if (carrello != null && request != null) {
			String token = controlloCookie.getSessionId(request);
			if (token != null) {
				Utente utenteEsistente = (Utente) utenteService.getUtenteByToken(token);
				if (utenteEsistente != null) {
					Carrello carrelloUtente = utenteEsistente.getCarrello();
					if(carrello != null && carrelloUtente.equals(carrello)) {
						try {
							Ordine ordine = new Ordine();
							ordine.setData(LocalDate.now());
							ordine.setImporto(carrello.getImporto());
							ordine.setUtente(utenteEsistente);
							ordine.setIndirizzo(utenteEsistente.getIndirizzi().get(0));
							ordineDao.save(ordine);
							if(ordineDao.save(ordine) != null) {
								scarpaOrdinataService.aggiuntaScarpaOrdinata(ordine, carrello);
								List<ScarpaCarrello> scarpeNelCarrello = carrello.getCarrelloItem();
								for(ScarpaCarrello scarpaCarrello : scarpeNelCarrello) {
									scarpaCarrelloService.rimozioneScarpaCarrello(scarpaCarrello);
								}
								carrelloService.clearCart(carrello);
								//non funziona, trovare altro metodo
							}
							return new Risposta(200, "Ordine confermato con successo");
						} catch (Exception e) {
							return new Risposta(400, "Errore durante la conferma dell'ordine: " + e.getMessage());
						}
					}
					return new Risposta(400, "Errore in fase di controllo carrello, carrello non trovato");
				}
				return new Risposta(400, "Errore in fase di richiesta, utente non trovato");
			}
			return new Risposta(400, "Non autorizzato, token non valido");
		}
		return new Risposta(400, "Errore in fase di richiesta, carrello o cookie errati");
	}
}