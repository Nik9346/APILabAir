package it.labair.service;

import java.util.List;

import it.labair.dto.CategoriaDto;
import it.labair.model.Categoria;

public interface CategoriaService {
 void registraCategoria(Categoria categoria);
 List<CategoriaDto> getCategorie();
 Categoria getCategoriaById(int id);
 Categoria getCategoriaByDescrizione(String descrizione);
 void cancellaCategoria(int id);
}
