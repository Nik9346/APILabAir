package it.labair.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.labair.dao.CategoriaDao;
import it.labair.dao.ColoreDao;
import it.labair.dao.ScarpaDao;
import it.labair.dto.CategoriaDto;
import it.labair.dto.ScarpaDto;
import it.labair.model.Scarpa;
import it.labair.model.Taglia;



@Service
public class ScarpaServiceImpl implements ScarpaService {

	@Autowired
	ScarpaDao scarpaDao;
	
	@Autowired
	CategoriaDao categoriaDao;
	
	@Autowired
	ColoreDao coloreDao;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public void registraScarpa(Scarpa scarpa) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ScarpaDto> elencoScarpe() {
		List<Scarpa>scarpe = (List<Scarpa>) scarpaDao.findAll();
		List<ScarpaDto> scarpeDto = new ArrayList<>();
		scarpe.forEach(s ->{
			ScarpaDto scarpaDto = modelMapper.map(s, ScarpaDto.class);
			scarpaDto.setCategoria(modelMapper.map(s.getCategoria(),CategoriaDto.class).getDescrizione());
			scarpaDto.setTaglie(s.getTaglie().stream().map(Taglia::toString).collect(Collectors.toList()));
			List<String> colori = s.getColori().stream().map(c -> c.toString()).collect(Collectors.toList());
			scarpaDto.setColori(colori);
			scarpeDto.add(scarpaDto);
		});
		return scarpeDto;
	}

	@Override
	public Scarpa getScarpaById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scarpa getScarpaByColore(String nomeColore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancellaScarpa(int id) {
		// TODO Auto-generated method stub

	}

}
