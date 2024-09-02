package it.labair.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.labair.dao.IndirizzoDao;
import it.labair.helper.Risposta;
import it.labair.model.Indirizzo;

@Service
public class IndirizzoServiceImpl implements IndirizzoService {
	
	@Autowired
	IndirizzoDao indirizzoDao;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public Risposta registraIndirizzo(Indirizzo indirizzo) {
		if(indirizzo == null) {
			return new Risposta(400,"non è stato inserito nessun oggetto Indirizzo nella richiesta");
		}
		try {
			indirizzoDao.save(indirizzo);
			return new Risposta(200, "indirizzo registrato correttamente");
		} catch (Exception e) {
			return new Risposta(400, "Errore nella registrazione dell'indirizzo: " + e.getMessage());
		}
	}

	@Override
	public Object elencoIndirizzi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Risposta cancellaIndirizzo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Risposta modificaIndirizzo(Indirizzo indirizzo) {
		// TODO Auto-generated method stub
		return null;
	}

}
