package it.labair.dao;

import org.springframework.data.repository.CrudRepository;

import it.labair.model.Colore;

public interface ColoreDao extends CrudRepository<Colore, Integer> {
	
	Colore findByColore(String colore);
	

}
