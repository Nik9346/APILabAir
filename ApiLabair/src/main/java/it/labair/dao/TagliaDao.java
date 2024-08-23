package it.labair.dao;

import org.springframework.data.repository.CrudRepository;

import it.labair.model.Taglia;

public interface TagliaDao extends CrudRepository<Taglia, Integer> {
	
	public Taglia findByTaglia(Integer tagliaNumero);

}
