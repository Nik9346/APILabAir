package it.labair.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import it.labair.model.Categoria;
import it.labair.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//localhost:8080/categorie
@RestController
@RequestMapping("/categorie")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	//endpoint utilizzato per ottenere l'elenco di tutte le categorie
	@GetMapping("/get")
	public ResponseEntity<Object> elencoCategorie(){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.getCategorie());
	}
	
	//endpoint utilizzato per aggiungere una categoria al database
	@PostMapping("/add")
	public void registraCategoria(@Valid @RequestBody Categoria categoria) {
		categoriaService.registraCategoria(categoria);
	}

}
