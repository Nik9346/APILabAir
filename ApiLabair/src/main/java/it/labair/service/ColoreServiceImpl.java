package it.labair.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.labair.dao.ColoreDao;
import it.labair.dto.ColoreDto;
import it.labair.model.Colore;

@Service
public class ColoreServiceImpl implements ColoreService {

	@Autowired
	ColoreDao coloreDao;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public void registraColore(Colore colore) {
		coloreDao.save(colore);
	}

	@Override
	public void cancellaColore(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getColoreByNomeColore(String nomeColore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String registraColori(List<Colore> colori) {
		
		List<Colore> coloriNuovi = new ArrayList<>();
		List<Colore> coloriEsistenti = (List<Colore>) coloreDao.findAll();

		for (Colore c : colori) {
			String nomeColore = c.getColore();
			boolean esistente = coloriEsistenti.stream().anyMatch(col -> col.getColore().equals(nomeColore));
			if (!esistente) {
				coloriNuovi.add(c);
		}
		if (!coloriNuovi.isEmpty()) 
			coloreDao.saveAll(coloriNuovi);
		}
		return "colori registrati correttamente";
	}

	@Override
	public List<ColoreDto> elencocolori() {
		List<Colore> colori = (List<Colore>) coloreDao.findAll();
		List<ColoreDto> coloriDaPresentare = new ArrayList<>();
		colori.forEach(c->{
			ColoreDto coloreDto = mapper.map(c, ColoreDto.class);
			coloreDto.setColore(c.getColore());
			coloreDto.setId(c.getId());
			coloriDaPresentare.add(coloreDto);
		});
		return coloriDaPresentare;
	}
}
