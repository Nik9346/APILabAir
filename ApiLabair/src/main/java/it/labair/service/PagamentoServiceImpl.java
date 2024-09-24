package it.labair.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.labair.dao.PagamentoDao;
import it.labair.helper.ControlloCookie;
import it.labair.helper.Risposta;
import it.labair.model.Pagamento;
import it.labair.model.Utente;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class PagamentoServiceImpl implements PagamentoService {
	
	@Autowired
	PagamentoDao pagamentoDao;
	
	@Autowired
	ControlloCookie controlloCookie;
	
	@Autowired
	UtenteService utenteService;
	
	//toDo: Aggiungere controllo sui dati della carta di credito, scadenza, cvv, numero carta
	
	
	//Funzione utilizzata per registrare un pagamento verificando la validità del token
	@Override
	public Risposta addPagamento(Pagamento pagamento, HttpServletRequest request) {
		
		if(pagamento != null && request != null) {
			String token = controlloCookie.getSessionId(request);
			if(token != null) {
				Utente utente = (Utente) utenteService.getUtenteByToken(token);
				if(utente != null) {
					pagamento.setUtente(utente);
					pagamento.setDataPagamento(LocalDate.now());
					pagamentoDao.save(pagamento);
					return new Risposta(200, "pagamento confermato con successo");
				}
				return new Risposta(400, "errore in fase di controllo utente, non trovato");
			}
			return new Risposta(400, "Non autorizzato, token non valido");
		}
		return new Risposta(400, "Errore in fase di richiesta, pagamento o token non valido");
	}

}
