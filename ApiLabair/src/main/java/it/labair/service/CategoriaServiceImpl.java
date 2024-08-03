package it.labair.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.labair.dao.CategoriaDao;
import it.labair.dto.CategoriaDto;
import it.labair.model.Categoria;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void registraCategoria(Categoria categoria) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CategoriaDto> getCategorie() {
		List<Categoria> categorie = (List<Categoria>) categoriaDao.findAll();
		List<CategoriaDto> categorieDto = new ArrayList<>();
		categorie.forEach(c -> {
			CategoriaDto categoriaDto = modelMapper.map(c, CategoriaDto.class); 
		 categoriaDto.getDescrizione();
		 categorieDto.add(categoriaDto);
		 });
		return categorieDto;
	}

	@Override
	public Categoria getCategoriaById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria getCategoriaByDescrizione(String descrizione) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancellaCategoria(int id) {
		// TODO Auto-generated method stub

	}

}
