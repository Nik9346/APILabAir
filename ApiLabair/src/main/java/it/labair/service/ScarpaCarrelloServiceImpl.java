package it.labair.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.labair.dao.ScarpaCarrelloDao;
import it.labair.dto.ScarpaCarrelloDto;
import it.labair.helper.ControlloCookie;
import it.labair.helper.Risposta;
import it.labair.model.Carrello;
import it.labair.model.Colore;
import it.labair.model.Scarpa;
import it.labair.model.ScarpaCarrello;
import it.labair.model.Taglia;
import it.labair.model.Utente;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ScarpaCarrelloServiceImpl implements ScarpaCarrelloService {

	@Autowired
	ScarpaService scarpaService;

	@Autowired
	ColoreService coloreService;

	@Autowired
	TagliaService tagliaService;

	@Autowired
	UtenteService utenteService;

	@Autowired
	ScarpaCarrelloDao scarpaCarrelloDao;

	@Autowired
	CarrelloService carrelloService;

	@Autowired
	ControlloCookie controlloCookie;

	@Autowired
	ModelMapper mapper;

	@Override
	public Risposta aggiuntaScarpa(ScarpaCarrello scarpa, HttpServletRequest request) {
		if (scarpa.getScarpa() == null) {
			return new Risposta(400, "Scarpa non può essere null");
		}

		if (scarpa != null && request != null) {
			Scarpa scarpaOg = (Scarpa) scarpaService.getScarpaByIdForCart(scarpa.getScarpa().getId());
			Taglia taglia = (Taglia) tagliaService.getTagliaById(scarpa.getTaglia().getId());
			Colore colore = (Colore) coloreService.getColoreById(scarpa.getColore().getId());

			if (controlloColore(colore, scarpaOg) && controlloTaglia(taglia, scarpaOg)) {
				String token = controlloCookie.getSessionId(request);
				if (token != null) {
					Utente utente = (Utente) utenteService.getUtenteByToken(token);
					if (utente != null) {
						try {
							ScarpaCarrello scarpaCarrello = new ScarpaCarrello();
							Carrello carrello = utente.getCarrello();
							scarpaCarrello.setScarpa(scarpaOg);
							scarpaCarrello.setColore(colore);
							scarpaCarrello.setQuantita(scarpa.getQuantita());
							scarpaCarrello.setTaglia(taglia);
							scarpaCarrello.setCarrello(carrello);
							carrello.getCarrelloItem().add(scarpaCarrello);
							scarpaCarrelloDao.save(scarpaCarrello);
							carrelloService.updateCarrello(carrello);
							return new Risposta(200, "scarpa registrata nel carrello");
						} catch (Exception e) {
							return new Risposta(400,
									"Errore durante l'aggiunta del prodotto al carrello" + e.getMessage());
						}
					}
					return new Risposta(400, "Non autorizzato, utente non trovato");
				}
				return new Risposta(400, "Non autorizzato, token non valido");
			}
			return new Risposta(400, "colore o taglia non disponibile per la scarpa selezionata");
		}
		return new Risposta(400, "Errore in fase di richiesta");
	}

	@Override
	public Risposta rimozioneScarpa(Integer idScarpa, HttpServletRequest request) {
		String token = controlloCookie.getSessionId(request);
		if (token != null) {
			Utente utente = (Utente) utenteService.getUtenteByToken(token);
			if (utente != null) {
				Carrello carrello = (Carrello) carrelloService.getCarrello(utente.getId(), request);
				Optional<ScarpaCarrello> scarpaCarrello = scarpaCarrelloDao.findById(idScarpa);
				if (scarpaCarrello.isPresent()) {
					ScarpaCarrello scarpaCarrelloOg = scarpaCarrello.get();
					try {
						carrello.getCarrelloItem().remove(scarpaCarrelloOg);
						carrello.setImporto(carrello.getImporto() - (scarpaCarrelloOg.getScarpa().getPrezzo()*scarpaCarrelloOg.getQuantita()));
//						carrelloService.clearCart(carrello);
						scarpaCarrelloDao.delete(scarpaCarrelloOg);
						return new Risposta(200, "scarpa rimossa dal carrello");
					} catch (Exception e) {
						return new Risposta(400,
								"errore in fase di rimozione scarpa dal carrello con id: " + scarpaCarrelloOg.getId());
					}
				}
				return new Risposta(400, "Errore in fase di ricerca scarpa nel carrello");
			}
			return new Risposta(400, "errore in fase di ricerca utente");
		}
		return new Risposta(400, "Non autorizzato");
	}

	@Override
	public Risposta modificaScarpa(ScarpaCarrello scarpaCarrello, HttpServletRequest request) {
		if (request != null) {
			String token = controlloCookie.getSessionId(request);
			if (token != null) {
				Utente utente = (Utente) utenteService.getUtenteByToken(token);
				if (utente != null) {
					
					Carrello carrello = (Carrello) carrelloService.getCarrello(utente.getId(), request);
					Optional<ScarpaCarrello> scarpaCarrelloOpt = scarpaCarrelloDao.findById(scarpaCarrello.getId());
					
					if (!scarpaCarrelloOpt.isPresent()) {
						return new Risposta(400, "scarpa non trovata nel carrello");
					}
					
					ScarpaCarrello scarpaCart = scarpaCarrelloOpt.get();

					if (scarpaCarrello.getTaglia() != null) {
						List<Taglia> taglieDisponibili = scarpaCart.getScarpa().getTaglie();
						Taglia nuovaTaglia = (Taglia) tagliaService.getTagliaById(scarpaCarrello.getTaglia().getId());
						if (taglieDisponibili.contains(nuovaTaglia))
							scarpaCart.setTaglia(nuovaTaglia);
					}
					if (scarpaCarrello.getColore() != null) {
						List<Colore> coloriDisponibili = scarpaCart.getScarpa().getColori();
						Colore nuovoColore = (Colore) coloreService.getColoreById(scarpaCarrello.getColore().getId());
						if (coloriDisponibili.contains(nuovoColore))
							scarpaCart.setColore(nuovoColore);
					}
					if (scarpaCarrello.getQuantita() != scarpaCart.getQuantita() && scarpaCart.getQuantita() > 0) {
						scarpaCart.setQuantita(scarpaCarrello.getQuantita());
					}
					try {
						scarpaCarrelloDao.save(scarpaCart);
						carrelloService.updateCarrello(carrello);
						return new Risposta(200, "Scarpa nel carrello aggiornata con successo");
					} catch (Exception e) {
						return new Risposta(400, "Errore in fase di modifica scarpa nel carrello " + e.getMessage());
					}
				}
				return new Risposta(400, "Non autorizzato, utente non trovato");
			}
			return new Risposta(400, "Non autorizzato, Token non valido");
		}
		return new Risposta(400, "Errore in fase di richiesta");
	}

	@Override
	public Object getScarpe(HttpServletRequest request) {
		if (request != null) {
			String token = controlloCookie.getSessionId(request);
			if (token != null) {
				Utente utente = (Utente) utenteService.getUtenteByToken(token);
				if (utente != null) {
					List<ScarpaCarrello> scarpeCarrello = utente.getCarrello().getCarrelloItem();
					if (scarpeCarrello != null) {
						try {
							List<ScarpaCarrelloDto> scarpeCarrelloDto = scarpeCarrello.stream()
									.map(i -> mapper.map(i, ScarpaCarrelloDto.class)).collect(Collectors.toList());
							return scarpeCarrelloDto;
						} catch (Exception e) {
							new Risposta(400, "Errore in fase di mappatura scarpe nel carrello" + e.getMessage());
						}
					}
					return new Risposta(400, "Non è stata trovata nessuna lista delle scarpe");
				}
				return new Risposta(400, "Non autorizzato, utente non trovato");
			}
			return new Risposta(400, "Errore in fase di richiesta");
		}
		List<ScarpaCarrello> scarpeCarrello = (List<ScarpaCarrello>) scarpaCarrelloDao.findAll();
		List<ScarpaCarrelloDto> scarpeDaPresentare = scarpeCarrello.stream()
				.map(i -> mapper.map(i, ScarpaCarrelloDto.class)).collect(Collectors.toList());
		return scarpeDaPresentare;
	}

	private boolean controlloColore(Colore colore, Scarpa scarpa) {
		for (Colore c : scarpa.getColori()) {
			if (c.equals(colore))
				return true;
		}
		return false;
	}

	private boolean controlloTaglia(Taglia taglia, Scarpa scarpa) {
		for (Taglia t : scarpa.getTaglie()) {
			if (t.equals(taglia))
				return true;
		}
		return false;
	}

	@Override
	public Object getScarpaCarrelloNotLogged(Integer idScarpa, Integer idColore, Integer idTaglia) {
		if (idScarpa != null && idColore != null && idTaglia != null) {
			Scarpa scarpa = (Scarpa) scarpaService.getScarpaByIdForCart(idScarpa);
			Taglia taglia = (Taglia) tagliaService.getTagliaById(idTaglia);
			Colore colore = (Colore) coloreService.getColoreById(idColore);
			if (scarpa != null && colore != null && taglia != null) {
				try {
					ScarpaCarrello scarpaCarrello = new ScarpaCarrello();
					scarpaCarrello.setColore(colore);
					scarpaCarrello.setTaglia(taglia);
					scarpaCarrello.setScarpa(scarpa);
					scarpaCarrello.setQuantita(1);
					ScarpaCarrelloDto scarpaCarrelloDto = mapper.map(scarpaCarrello, ScarpaCarrelloDto.class);
					return scarpaCarrelloDto;
				} catch (Exception e) {
					return new Risposta(400, "Errore in fase di compilazione oggetto scarpa" + e.getMessage());
				}
			}
			return new Risposta(400, "Errore in fase di ricerca scarpa, taglia o colore");
		}
		return new Risposta(400, "parametri mancanti nella richiesta");
	}

}
