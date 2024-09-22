package it.labair.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.labair.dao.CategoriaDao;
import it.labair.dao.ScarpaDao;
import it.labair.dao.TagliaDao;
import it.labair.dto.CategoriaDto;
import it.labair.dto.ScarpaDto;
import it.labair.helper.Risposta;
import it.labair.model.Categoria;
import it.labair.model.Colore;
import it.labair.model.Scarpa;
import it.labair.model.Taglia;

@Service
public class ScarpaServiceImpl implements ScarpaService {

	@Autowired
	ScarpaDao scarpaDao;

	@Autowired
	CategoriaDao categoriaDao;

	@Autowired
	TagliaDao tagliaDao;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ColoreService coloreServiceImpl;

	@Autowired
	TagliaService tagliaServiceImpl;

	@Override
	public Risposta registraScarpa(Scarpa scarpa) {

		if (scarpa != null) {

			try {
				Scarpa controlloEsistenza = scarpaDao.findByNome(scarpa.getNome());
				if (controlloEsistenza == null) {
					try {
						scarpaDao.save(scarpa);
						return new Risposta(200, "scarpa registrata correttamente");
					} catch (Exception e) {
						return new Risposta(400, "Errore nella registrazione della scarpa: " + e.getMessage());
					}
				} else {
					return new Risposta(409, "la scarpa è già presente nel database");
				}
			} catch (Exception e) {
				return new Risposta(400, "Errore in fase di controllo esistenza duplicato scarpa: " + e.getMessage());
			}
		}
		return new Risposta(400, "non è stata inserito nessun oggetto scarpa nella richiesta");
	}

	@Override
	public Object elencoScarpe() {

		try {
			List<Scarpa> scarpe = (List<Scarpa>) scarpaDao.findAll();
			List<ScarpaDto> scarpeDto = new ArrayList<>();
			scarpe.forEach(s -> {
				ScarpaDto scarpaDto = modelMapper.map(s, ScarpaDto.class);
				scarpaDto.setCategoria(modelMapper.map(s.getCategoria(), CategoriaDto.class).getDescrizione());
				scarpaDto.setTaglie(s.getTaglie().stream().map(Taglia::toString).collect(Collectors.toList()));
				List<String> colori = s.getColori().stream().map(c -> c.toString()).collect(Collectors.toList());
				scarpaDto.setColori(colori);
				scarpeDto.add(scarpaDto);
			});
			return scarpeDto;
		} catch (Exception e) {
			return new Risposta(400, "Errore in fase di richiesta elenco Scarpe" + e.getMessage());
		}
	}

	@Override
	public Object getScarpaById(Integer id) {
		if (id != null) {
			try {
				Scarpa scarpa = scarpaDao.findById(id).get();
				ScarpaDto scarpaDto = modelMapper.map(scarpa, ScarpaDto.class);
				scarpaDto.setCategoria(modelMapper.map(scarpa.getCategoria(), CategoriaDto.class).getDescrizione());
				scarpaDto.setTaglie(scarpa.getTaglie().stream().map(Taglia::toString).collect(Collectors.toList()));
				scarpaDto.setColori(scarpa.getColori().stream().map(t -> t.toString()).collect(Collectors.toList()));
				return scarpaDto;
			} catch (Exception e) {
				return new Risposta(400, "errore in fase di richiesta scarpa per Id" + e.getMessage());
			}
		}
		return new Risposta(400, "Errore in fase di richiesta, id non valido " + id);
	}

	@Override
	public Object getScarpaByColore(String nomeColore) {
		try {
			Colore colore = (Colore) coloreServiceImpl.getColoreByNomeColore(nomeColore);
			List<Colore> colori = new ArrayList<>();
			colori.add(colore);
			List<Scarpa> scarpe = scarpaDao.findByColori(colori);
			List<ScarpaDto> scarpeDto = new ArrayList<>();
			scarpe.forEach(s -> {
				ScarpaDto scarpaDto = modelMapper.map(s, ScarpaDto.class);
				scarpaDto.setCategoria(modelMapper.map(s.getCategoria(), CategoriaDto.class).getDescrizione());
				scarpaDto.setTaglie(s.getTaglie().stream().map(Taglia::toString).collect(Collectors.toList()));
				List<String> coloriDisponibili = s.getColori().stream().map(c -> c.toString())
						.collect(Collectors.toList());
				scarpaDto.setColori(coloriDisponibili);
				scarpeDto.add(scarpaDto);
			});
			return scarpeDto;
		} catch (Exception e) {
			return new Risposta(200, "Errore in fase di richiesta scarpa in base al colore: " + e.getMessage());
		}

	}

	@Override
	public Risposta cancellaScarpa(Integer id) {
		try {
			Scarpa scarpa = scarpaDao.findById(id).get();
			if (scarpa != null) {
				scarpaDao.deleteById(id);
				return new Risposta(200, "Scarpa con id: " + id + " cancellata correttamente");
			} else {
				return new Risposta(400, "non è presente nessuna scarpa sul database con numero Id: " + id);
			}
		} catch (Exception e) {
			return new Risposta(400, "Errore in fase di cancellazione scarpa: " + e.getMessage());
		}
	}

	@Override
	public Object getScarpaByIdForCart(Integer id) {
		try {
			Scarpa scarpa = scarpaDao.findById(id).get();
			return scarpa;
		} catch (Exception e) {
			return new Risposta(400, "errore in fase di richiesta scarpa per Id" + e.getMessage());
		}
	}

	@Override
	public Object getScarpeByNuovoArrivo(boolean nuovoArrivo) {
		if (nuovoArrivo == true) {
			try {
				List<Scarpa> scarpe = scarpaDao.findByNuovoArrivi(true);
				List<ScarpaDto> scarpeDto = new ArrayList<>();
				scarpe.forEach(s -> {
					ScarpaDto scarpaDto = modelMapper.map(s, ScarpaDto.class);
					scarpaDto.setCategoria(modelMapper.map(s.getCategoria(), CategoriaDto.class).getDescrizione());
					scarpaDto.setTaglie(s.getTaglie().stream().map(Taglia::toString).collect(Collectors.toList()));
					List<String> coloriDisponibili = s.getColori().stream().map(c -> c.toString())
							.collect(Collectors.toList());
					scarpaDto.setColori(coloriDisponibili);
					scarpeDto.add(scarpaDto);
				});
				return scarpeDto;
			} catch (Exception e) {
				return new Risposta(400, "errore in fase di richiesta Nuove Scarpe" + e.getMessage());
			}
		}
		return new Risposta(400, "Errore in fase di richiesta");
	}

	@Override
	public Object getScarpeByCategoria(String categoria) {
		if (categoria != null) {
			try {
				Categoria categoriaOggetto = categoriaDao.findByDescrizione(categoria);
				try {
					List<Scarpa> scarpe = scarpaDao.findByCategoria(categoriaOggetto);
					List<ScarpaDto> scarpeDto = new ArrayList<>();
					scarpe.forEach(s -> {
						ScarpaDto scarpaDto = modelMapper.map(s, ScarpaDto.class);
						scarpaDto.setCategoria(modelMapper.map(s.getCategoria(), CategoriaDto.class).getDescrizione());
						scarpaDto.setTaglie(s.getTaglie().stream().map(Taglia::toString).collect(Collectors.toList()));
						List<String> coloriDisponibili = s.getColori().stream().map(c -> c.toString())
								.collect(Collectors.toList());
						scarpaDto.setColori(coloriDisponibili);
						scarpeDto.add(scarpaDto);
					});
					return scarpeDto;
				} catch (Exception e) {
					return new Risposta(400, "Errore in fase di ricerca scarpe con categoria " + categoriaOggetto);
				}
			} catch (Exception e) {
				return new Risposta(400, "Errore in fase di ricerca categoria, non trovata");
			}
		}
		return new Risposta(400, "Errore in fase di richiesta, categoria non presente " + categoria);
	}
}
