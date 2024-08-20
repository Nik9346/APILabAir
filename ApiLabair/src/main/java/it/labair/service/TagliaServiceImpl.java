package it.labair.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.labair.dao.TagliaDao;
import it.labair.model.Taglia;
@Service
public class TagliaServiceImpl implements TagliaService {
	
	@Autowired
	TagliaDao tagliaDao;

	@Override
	public String registraTaglia(Taglia taglia) {
		tagliaDao.save(taglia);
		return "taglia salvata con successo" + taglia.getTaglia(); 
	}

	@Override
	public String registraTaglie(List<Taglia> taglie) {
		List<Taglia> taglieNuove = new ArrayList<>();
		List<Taglia> taglieEsistenti = (List<Taglia>) tagliaDao.findAll();
		
		for(Taglia t : taglie) {
			int taglia = t.getTaglia();
			boolean esistente = taglieEsistenti.stream().anyMatch(tag -> tag.getTaglia().equals(taglia));
			if(!esistente)
				taglieNuove.add(t);
		}
		if(!taglieNuove.isEmpty())
			try {
				tagliaDao.saveAll(taglieNuove);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return "taglie inserite con successo";
	}

	@Override
	public void cancellaTaglia(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getTagliaByNumero(int numeroTaglia) {
		// TODO Auto-generated method stub
		return null;
	}

}
