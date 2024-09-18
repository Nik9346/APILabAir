package it.labair.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.labair.dao.IndirizzoDao;
import it.labair.helper.ControlloCookie;
import it.labair.helper.Risposta;
import it.labair.model.Indirizzo;
import it.labair.model.Utente;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class IndirizzoServiceImpl implements IndirizzoService {

	@Autowired
	IndirizzoDao indirizzoDao;

	@Autowired
	UtenteService utenteService;

	@Autowired
	ModelMapper mapper;

	@Autowired
	ControlloCookie controlloCookie;

	@Override
	public Risposta registraIndirizzo(Indirizzo indirizzo, HttpServletRequest request) {
		if(indirizzo != null && request !=null) {
			String token = controlloCookie.getSessionId(request);
			if(token != null) {
				Utente utente = (Utente) utenteService.getUtenteByToken(token);
				if(utente != null) {
					try {
						indirizzoDao.save(indirizzo);
						return new Risposta(200, "indirizzo registrato correttamente");
					} catch (Exception e) {
						return new Risposta(400, "Errore nella registrazione dell'indirizzo: " + e.getMessage());
					}
				}
				return new Risposta(400, "Errore in fase di ricerca utente");
			}
			return new Risposta(400, "Non autorizzato, Token non valido");
		}
		return new Risposta(400, "Errore in fase di richiesta, indirizzo o utente non trovato");
	}

	@Override
	public Object elencoIndirizzi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Risposta cancellaIndirizzo(Indirizzo indirizzo, HttpServletRequest request) {
		String token = controlloCookie.getSessionId(request);
		if (token != null) {
			Utente utente = (Utente) utenteService.getUtenteByToken(token);
			if (utente != null) {
				try {
					List<Indirizzo> indirizziUtente = utente.getIndirizzi();
					for (Indirizzo i : indirizziUtente) {
						if (i.equals(indirizzo)) {
							indirizzoDao.delete(i);
							return new Risposta(200, "L'indirizzo è stato cancellato con successo");
						}
						return new Risposta(400, "Indirizzo non trovato");
					}
				} catch (Exception e) {
					return new Risposta(400,
							"Errore in fase di cancellazione indirizzo per utente con id: " + utente.getId());
				}
			}
			return new Risposta(400, "Errore utente non trovato");
		}
		return new Risposta(400, "Non autorizzato, token errato");
	}

	@Override
	public Risposta modificaIndirizzo(Indirizzo indirizzo, HttpServletRequest request) {
		String token = controlloCookie.getSessionId(request);
		if (token != null) {
			Utente utente = (Utente) utenteService.getUtenteByToken(token);
			if (utente != null) {
				try {
					List<Indirizzo> indirizziUtente = utente.getIndirizzi();
					for (Indirizzo i : indirizziUtente) {
						if (i.getId() == indirizzo.getId()) {
							BeanUtils.copyProperties(indirizzo, i, "id", "utente");
							indirizzoDao.save(i);
							return new Risposta(200, "indirizzo aggiornato con successo");
						}
					}
					return new Risposta(400, "Indirizzo non trovato");
				} catch (Exception e) {
					return new Risposta(400, "Errore in fase di modifica indirizzo" + e.getMessage());
				}
			}
			return new Risposta(400, "Errore utente non trovato");
		}
		return new Risposta(400, "Non autorizzato, token errato");
	}

	@Override
	public Object getIndirizziByUtente(Utente utente, HttpServletRequest request) {
		if (utente != null && request != null) {
			String token = controlloCookie.getSessionId(request);
			if (token != null) {
				Utente utenteRegistrato = (Utente) utenteService.getUtenteByToken(token);
				if (utenteRegistrato != null) {
					try {
						List<Indirizzo> elencoIndirizzi = indirizzoDao.findByUtente(utenteRegistrato);
						return elencoIndirizzi;
					} catch (Exception e) {
						return new Risposta(400,
								"errore in fase di ricerca indirizzi per utente con id: " + utente.getId());
					}
				}
				return new Risposta(400, "Errore in fase di ricerca utente");
			}
			return new Risposta(400, "Non autorizzato, Token non valido");
		}
		return new Risposta(400, "Errore in fase di richiesta, utente o token non trovati");
	}
}
