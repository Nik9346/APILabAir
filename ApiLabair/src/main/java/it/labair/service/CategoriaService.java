package it.labair.service;

import java.util.List;

import it.labair.dto.CategoriaDto;
import it.labair.helper.Risposta;
import it.labair.model.Categoria;

public interface CategoriaService {
 Risposta registraCategoria(Categoria categoria);
 Object getCategorie();
 Object getCategoriaById(int id);
 Object getCategoriaByDescrizione(String descrizione);
 Risposta cancellaCategoria(int id);
}
