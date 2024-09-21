package it.labair.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
	ScarpaCarrelloService scarpaCarrelloService;

	@Autowired
	ControlloCookie controlloCookie;

	@Autowired
	UtenteService utenteService;
	

	@Override
	public Risposta addOrder(Ordine ordine, HttpServletRequest request) {
		if(ordine != null && request !=null) {
			String token = controlloCookie.getSessionId(request);
			if(token != null) {
				Utente utente = (Utente) utenteService.getUtenteByToken(token);
				Carrello carrelloUtente = utente.getCarrello();
				if(utente != null && carrelloUtente != null) {
					try {
						ordine.setUtente(utente);
						ordine.setData(LocalDate.now());
						ordine.getPagamento().setDataPagamento(LocalDate.now());
						ordine.getPagamento().setUtente(utente);
						List<ScarpaCarrello> scarpeNelCarrello = carrelloUtente.getCarrelloItem();
						List<ScarpaCarrello> scarpeDaRimuovere = new ArrayList<>();
						for (ScarpaCarrello scarpaCarrello : scarpeNelCarrello) {
							scarpeDaRimuovere.add(scarpaCarrello);
						}
						scarpeDaRimuovere.forEach(s->{
							scarpaOrdinataService.aggiuntaScarpaOrdinata(s,ordine);
							scarpaCarrelloService.rimozioneScarpa(s.getId(), request);
						});
						ordineDao.save(ordine);
						
						return new Risposta(200, "ordine concluso con successo");						
					} catch (Exception e) {
						return new Risposta(400, "errore in fase di registrazione ordine " + e.getMessage());
					}
				}
				return new Risposta(400, "Errore in fase di ricerca utente, non trovato");
			}
			return new Risposta(400, "Non autorizzato, token non valido");
		}
		return new Risposta(400, "Errore in fase di richiesta, ordine o token non validi");
	}
}