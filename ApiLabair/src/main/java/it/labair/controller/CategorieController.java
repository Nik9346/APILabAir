package it.labair.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;

import it.labair.dto.CategoriaDto;
import it.labair.service.CategoriaService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


//localhost:8080/categorie
@RestController
@RequestMapping("/categorie")
public class CategorieController {
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/get")
	public ResponseEntity<List<CategoriaDto>> elencoCategorie(){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.getCategorie());
	}
	

}
