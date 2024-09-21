package it.labair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.labair.dao.ScarpaOrdinataDao;
import it.labair.helper.ControlloCookie;
import it.labair.helper.Risposta;
import it.labair.model.Ordine;
import it.labair.model.ScarpaCarrello;
import it.labair.model.ScarpaOrdinata;

@Service
public class ScarpaOrdinataServiceImpl implements ScarpaOrdinataService {

	@Autowired
	ScarpaOrdinataDao scarpaOrdinataDao;

	@Autowired
	ControlloCookie controlloCookie;

	@Autowired
	UtenteService utenteService;

	@Override
	public Risposta aggiuntaScarpaOrdinata(ScarpaCarrello scarpaCarrello, Ordine ordine) {
		if (scarpaCarrello != null && ordine != null) {
			try {
				ScarpaOrdinata scarpaOrd = new ScarpaOrdinata();
				scarpaOrd.setOrdine(ordine);
				scarpaOrd.setColore(scarpaCarrello.getColore());
				scarpaOrd.setScarpa(scarpaCarrello.getScarpa());
				scarpaOrd.setQuantita(scarpaCarrello.getQuantita());
				scarpaOrd.setTaglia(scarpaCarrello.getTaglia());
				scarpaOrdinataDao.save(scarpaOrd);
				return new Risposta(200, "Scarpa aggiunta con successo");

			} catch (Exception e) {
				return new Risposta(400, "Errore in fase di registrazione scarpa ordinata" + e.getMessage());
			}
		}
		return new Risposta(400, "Errore in fase di richiesta");
	}

}
