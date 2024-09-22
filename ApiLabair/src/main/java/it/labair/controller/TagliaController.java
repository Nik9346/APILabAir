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
import it.labair.model.Taglia;
import it.labair.service.TagliaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/taglia")
public class TagliaController {

	@Autowired
	TagliaService tagliaService;
	
	Risposta risposta = new Risposta(0, null);
	
	//endpoint utilizzato per ottenere l'intero elenco delle taglie
	@GetMapping("/get")
	public ResponseEntity<Object> elencoTaglie(){
		return ResponseEntity.status(HttpStatus.OK).body(tagliaService.elencoTaglie());
	}
	
	//endpoint utilizzato per ottenere la taglia passando come pathvariable il numero
	@GetMapping("/get/{numberSize}")
	public ResponseEntity<Object>TagliaByNumero(@PathVariable("numberSize") Integer numberSize){
		return ResponseEntity.status(HttpStatus.OK).body(tagliaService.getTagliaByNumero(numberSize));
	}
	
	//endpoint utilizzato per registrare una nuova taglia nel db
	@PostMapping("/addSize")
	public ResponseEntity<Risposta> registraTaglia(@Valid @RequestBody Taglia taglia){
		risposta = tagliaService.registraTaglia(taglia);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}
	//endpoint utilizzato per la registrazione di più taglie passando nel body un array con varie taglie
	@PostMapping("/addSizes")
	public ResponseEntity<Risposta> registaTaglie(@Valid @RequestBody List<Taglia> taglie){
		risposta = tagliaService.registraTaglie(taglie);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}
}
