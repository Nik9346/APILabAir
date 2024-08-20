package it.labair.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.labair.dto.CategoriaDto;
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
	
	@GetMapping("/get")
	public ResponseEntity<List<CategoriaDto>> elencoCategorie(){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.getCategorie());
	}
	
	@PostMapping("/add")
	public void registraCategoria(@Valid @RequestBody Categoria categoria) {
		categoriaService.registraCategoria(categoria);
	}

}
