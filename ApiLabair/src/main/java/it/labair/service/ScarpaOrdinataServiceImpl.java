package it.labair.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.internal.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.labair.dao.ScarpaOrdinataDao;
import it.labair.helper.ControlloCookie;
import it.labair.helper.Risposta;
import it.labair.model.Carrello;
import it.labair.model.Ordine;
import it.labair.model.ScarpaCarrello;
import it.labair.model.ScarpaOrdinata;
import it.labair.model.Utente;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ScarpaOrdinataServiceImpl implements ScarpaOrdinataService {

	@Autowired
	ScarpaOrdinataDao scarpaOrdinataDao;

	@Override
	public Risposta aggiuntaScarpaOrdinata(Ordine ordine, Carrello carrello) {
		if (ordine != null && carrello != null && !carrello.getCarrelloItem().isEmpty()) {
			try {
				List<ScarpaOrdinata> scarpeOrdinate = new ArrayList<>();

				for (ScarpaCarrello scarpaCarrello : carrello.getCarrelloItem()) {
					ScarpaOrdinata scarpaOrdinata = new ScarpaOrdinata();
					scarpaOrdinata.setOrdine(ordine);
					scarpaOrdinata.setColore(scarpaCarrello.getColore());
					scarpaOrdinata.setScarpa(scarpaCarrello.getScarpa());
					scarpaOrdinata.setQuantita(scarpaCarrello.getQuantita());
					scarpaOrdinata.setTaglia(scarpaCarrello.getTaglia());
					scarpaOrdinataDao.save(scarpaOrdinata);
					scarpeOrdinate.add(scarpaOrdinata);
				}
				ordine.setScarpeOrdinate(scarpeOrdinate);
				return new Risposta(200, "Scarpe aggiunte correttamente all'ordine");
			} catch (Exception e) {
				return new Risposta(400, "errore in fase di aggiunta lista scarpe all'ordine " + e.getMessage());
			}
		}
		return new Risposta(400, "Errore in fase di richiesta ordine o carrello vuoti");
	}

}
