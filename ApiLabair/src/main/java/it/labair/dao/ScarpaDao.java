package it.labair.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.labair.model.Scarpa;
import it.labair.model.Categoria;
import it.labair.model.Colore;


public interface ScarpaDao extends CrudRepository<Scarpa, Integer> {
	
//	@Query(value = "SELECT * FROM Scarpe JOIN scarpe_colori ON scarpe.id=scarpe_colori.id_scarpa JOIN colori ON scarpe_colori.id_colore=colori.id WHERE colori.id=:u", nativeQuery = true)
//	List<Scarpa> findByColore(@Param("u") Integer idColore);

	List<Scarpa> findByColori(List<Colore> colori);
	List<Scarpa> findByCategoria(Categoria categoria);
	List<Scarpa> findByNuovoArrivi(Boolean nuovoArrivi);
	Scarpa findByNome(String nome);
	
}
