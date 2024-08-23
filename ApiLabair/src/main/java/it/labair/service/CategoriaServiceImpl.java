package it.labair.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.labair.dao.CategoriaDao;
import it.labair.dto.CategoriaDto;
import it.labair.helper.Risposta;
import it.labair.model.Categoria;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDao categoriaDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Risposta registraCategoria(Categoria categoria) {
		if (categoria == null) {
			return new Risposta(400, "nessuna categoria da registrare");
		}
		try {
			categoriaDao.save(categoria);
			return new Risposta(200, "categoria registrata con successo");
		} catch (Exception e) {
			return new Risposta(400, "Errore in fase di registrazione della categoria: " + e.getMessage());
		}
	}

	@Override
	public Object getCategorie() {
		try {
			List<CategoriaDto> categorieDto = new ArrayList<>();
			List<Categoria> categorie = (List<Categoria>) categoriaDao.findAll();
			categorie.forEach(c -> {
				CategoriaDto categoriaDto = modelMapper.map(c, CategoriaDto.class);
				categoriaDto.setDescrizione(c.getDescrizione());
				categoriaDto.setId(c.getId());
				categorieDto.add(categoriaDto);
			});
			return categorieDto;
		} catch (Exception e) {
			return new Risposta(400, "errore in fase di richiesta elenco categorie: " + e.getMessage());
		}
	}

	@Override
	public Object getCategoriaById(int id) {
		try {
			Categoria categoria = categoriaDao.findById(id).get();
			CategoriaDto categoriaDto = modelMapper.map(categoria, CategoriaDto.class);
			categoriaDto.setDescrizione(categoria.getDescrizione());
			categoriaDto.setId(categoria.getId());
			return categoria;
		} catch (Exception e) {
			return new Risposta(400, "Errore in fase di richiesta Categoria tramite Id: " + e.getMessage());
		}
	}

	@Override
	public Object getCategoriaByDescrizione(String descrizione) {
		try {
			Categoria categoria = categoriaDao.findByDescrizione(descrizione);
			CategoriaDto categoriaDto = modelMapper.map(categoria, CategoriaDto.class);
			categoriaDto.setDescrizione(categoria.getDescrizione());
			categoriaDto.setId(categoria.getId());
			return categoria;
		} catch (Exception e) {
			return new Risposta(400,
					"errore durante la fase di richiesta categoria tramite descrizione: " + e.getMessage());
		}
	}

	@Override
	public Risposta cancellaCategoria(int id) {
		Categoria categoria = categoriaDao.findById(id).get();
		if (categoria != null) {
			try {
				categoriaDao.deleteById(id);
				return new Risposta(200, "la categoria è stata cancellata con successo");
			} catch (Exception e) {
				return new Risposta(400, "errore durante la cancellazione della categoria" + e.getMessage());
			}
		} else {
			return new Risposta(400, "non è presente nessuna categoria con id: " + id + "nel database da cancellare");
		}
	}
}
