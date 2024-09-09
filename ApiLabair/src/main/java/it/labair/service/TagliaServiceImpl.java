package it.labair.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import it.labair.dao.TagliaDao;
import it.labair.dto.TagliaDto;
import it.labair.helper.Risposta;
import it.labair.model.Taglia;

@Service
public class TagliaServiceImpl implements TagliaService {

	@Autowired
	TagliaDao tagliaDao;

	@Autowired
	ModelMapper mapper;

	@Override
	public Risposta registraTaglia(Taglia taglia) {
		if (taglia == null) {
			return new Risposta(400, "non è presente nessuna taglia da registrare");
		}
		try {
			tagliaDao.save(taglia);
			return new Risposta(200, "la taglia numero " + taglia.getTaglia() + " è stata registrata correttamente");
		} catch (Exception e) {
			return new Risposta(400, "errore nell'aggiunta della taglia" + e.getMessage());
		}
	}

	@Override
	public Risposta registraTaglie(List<Taglia> taglie) {
		List<Taglia> taglieNuove = new ArrayList<>();
		List<Taglia> taglieEsistenti = (List<Taglia>) tagliaDao.findAll();
		if (taglie == null || taglie.isEmpty()) {
			return new Risposta(400, "non è stata inserita nessuna taglia");
		}
		try {
			for (Taglia t : taglie) {
				int taglia = t.getTaglia();
				boolean esistente = taglieEsistenti.stream().anyMatch(tag -> tag.getTaglia().equals(taglia));
				if (!esistente)
					taglieNuove.add(t);
			}
			if (!taglieNuove.isEmpty()) {
				try {
					tagliaDao.saveAll(taglieNuove);
					return new Risposta(200, "taglie registrate correttamente");
				} catch (Exception e) {
					if (e instanceof DataIntegrityViolationException)
						return new Risposta(400, "Violazione Integrità dei dati:" + e.getMessage());
					return new Risposta(400, e.getMessage());
				}
			} else {
				return new Risposta(409, "Le taglie sono già presenti nel Database");
			}
		} catch (Exception e) {
			return new Risposta(400, "Errore durante l'elaborazione delle taglie:" + e.getMessage());
		}
	}

	@Override
	public Risposta cancellaTaglia(int id) {
		try {
			tagliaDao.deleteById(id);
			return new Risposta(200, "la taglia è stata cancellata correttamente");
		} catch (Exception e) {
			return new Risposta(400, "Errore in fase di cancellazione" + e.getMessage());
		}

	}

	@Override
	public Object getTagliaByNumero(int numeroTaglia) {
		try {
			Taglia taglia = tagliaDao.findByTaglia(numeroTaglia);
			TagliaDto tagliaDaPresentare = new TagliaDto();
			tagliaDaPresentare.setTaglia(taglia.getTaglia());
			tagliaDaPresentare.setId(taglia.getId());
			return tagliaDaPresentare;
		} catch (Exception e) {
			return new Risposta(400, "Errore in fase di richiesta taglia" + e.getMessage());
		}
	}

	@Override
	public Object elencoTaglie() {
		try {
			List<Taglia> taglie = (List<Taglia>) tagliaDao.findAll();
			List<TagliaDto> taglieDaPresentare = new ArrayList<>();
			taglie.forEach(c -> {
				TagliaDto tagliaDto = mapper.map(c, TagliaDto.class);
				tagliaDto.setTaglia(c.getTaglia());
				taglieDaPresentare.add(tagliaDto);
			});
			return taglieDaPresentare;
		} catch (Exception e) {
			return new Risposta(400, e.getMessage());
		}
	}

	@Override
	public Object getTagliaById(Integer idTaglia) {
		try {
			Taglia taglia = tagliaDao.findById(idTaglia).get();
//			TagliaDto tagliaDaPresentare = new TagliaDto();
//			tagliaDaPresentare.setTaglia(taglia.getTaglia());
//			tagliaDaPresentare.setId(taglia.getId());
			return taglia;
		} catch (Exception e) {
			return new Risposta(400, "Errore in fase di richiesta taglia" + e.getMessage());
		}
	}

}
