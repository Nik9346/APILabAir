package it.labair.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.labair.helper.Risposta;
import it.labair.model.Colore;
import it.labair.service.ColoreService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/colore")
public class ColoreController {
	
	@Autowired
	ColoreService coloreService;
	
	@GetMapping("/get")
	public ResponseEntity<Object> elencoColori(){
		return ResponseEntity.status(HttpStatus.OK).body(coloreService.elencocolori());
	}
	
	@GetMapping("/get/colore/{nomeColore}")
	public ResponseEntity<Object> coloreByNomeColore(@PathVariable("nomeColore") String nomeColore){
		return ResponseEntity.status(HttpStatus.OK).body(coloreService.getColoreByNomeColore(nomeColore));
	}
	
	@PostMapping("/add")
	public ResponseEntity<Risposta> coloriRegistrati(@Valid @RequestBody List<Colore> colori){
		Risposta risposta =  coloreService.registraColori(colori);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}
	
	
}
