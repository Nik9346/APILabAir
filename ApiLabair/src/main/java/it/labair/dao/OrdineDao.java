package it.labair.dao;

import org.springframework.data.repository.CrudRepository;

import it.labair.model.Ordine;

public interface OrdineDao extends CrudRepository<Ordine, Integer> {

}
