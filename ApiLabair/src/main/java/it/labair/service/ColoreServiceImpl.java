package it.labair.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import it.labair.dao.ColoreDao;
import it.labair.dto.ColoreDto;
import it.labair.helper.Risposta;
import it.labair.model.Colore;

@Service
public class ColoreServiceImpl implements ColoreService {

	@Autowired
	ColoreDao coloreDao;

	@Autowired
	ModelMapper mapper;

	@Override
	public Risposta registraColore(Colore colore) {
		if (colore != null) {
			try {
				coloreDao.save(colore);
				return new Risposta(200, "Colore registrato correttamente");
			} catch (Exception e) {
				return new Risposta(400, "Errore in fase di registrazione colore" + e.getMessage());
			}
		} else {
			return new Risposta(400, "non è presente nessun colore da registrare nella richiesta");
		}
	}

	@Override
	public Risposta cancellaColore(int id) {
		try {
			Colore colore = coloreDao.findById(id).get();
			if (colore != null) {
				coloreDao.deleteById(id);
				return new Risposta(200, "Il colore è stato cancellato correttamente");
			} else {
				return new Risposta(400, "il colore con id " + id + " non è stato trovato");
			}
		} catch (Exception e) {
			return new Risposta(400, "Errore in fase di cancellazione colore: " + e.getMessage());
		}
	}

	@Override
	public Object getColoreByNomeColore(String nomeColore) {
		try {
			if (nomeColore != null) {
				Colore colore = coloreDao.findByColore(nomeColore);
				ColoreDto coloreDto = mapper.map(colore, ColoreDto.class);
				coloreDto.setColore(colore.getColore());
				coloreDto.setId(colore.getId());
				return coloreDto;
			} else {
				return new Risposta(400, "non è presente nessun parametro nome colore");
			}
		} catch (Exception e) {
			return new Risposta(400, "Errore in fase di richiesta colore tramite Nome Colore " + e.getMessage());
		}
	}

	@Override
	public Risposta registraColori(List<Colore> colori) {
		List<Colore> coloriNuovi = new ArrayList<>();
		List<Colore> coloriEsistenti = (List<Colore>) coloreDao.findAll();
		if (colori == null || colori.isEmpty()) {
			return new Risposta(400, "la lista dei colori è vuota");
		}
		try {
			for (Colore c : colori) {
				String nomeColore = c.getColore();
				boolean esistente = coloriEsistenti.stream().anyMatch(col -> col.getColore().equals(nomeColore));
				if (!esistente) {
					coloriNuovi.add(c);
				}
			}
			if (!coloriNuovi.isEmpty()) {
				coloreDao.saveAll(coloriNuovi);
				return new Risposta(200, "Colori inseriti correttamente");
			} else {
				return new Risposta(409, "I colori sono già presenti nel database");
			}
		} catch (Exception e) {
			if (e instanceof DataIntegrityViolationException)
				return new Risposta(400, e.getMessage());
			return new Risposta(400, e.getMessage());
		}
	}

	@Override
	public Object elencocolori() {
		try {
			List<Colore> colori = (List<Colore>) coloreDao.findAll();
			List<ColoreDto> coloriDaPresentare = new ArrayList<>();
			colori.forEach(c -> {
				ColoreDto coloreDto = mapper.map(c, ColoreDto.class);
				coloreDto.setColore(c.getColore());
				coloreDto.setId(c.getId());
				coloriDaPresentare.add(coloreDto);
			});
			return coloriDaPresentare;
		} catch (Exception e) {
			return new Risposta(400, "Errore in fase di richiesta elenco colori: " + e.getMessage());
		}

	}

	@Override
	public Object getColoreById(Integer idColore) {
		if(idColore!= null ) {
			try {
				Colore colore = coloreDao.findById(idColore).get();
				return colore;
			} catch (Exception e) {
				return new Risposta(400, "Colore con id: "+ idColore + " non trovato " + e.getMessage());
			}
		}
		return new Risposta(400, "non è presente nessun idColore in richiesta");
	}
}
