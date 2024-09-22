package it.labair.controller;


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
import it.labair.model.Scarpa;
import it.labair.service.ColoreService;
import it.labair.service.ScarpaService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/scarpe")
public class ScarpaController {

	@Autowired
	ScarpaService scarpaService;
	
	@Autowired
	ColoreService coloreService;

	//endpoint utilizzato per la registrazione di una nuova scarpa nel db
	@PostMapping("/add")
	public ResponseEntity<Risposta> registrazioneScarpa(@Valid @RequestBody Scarpa scarpa) {
		Risposta risposta = scarpaService.registraScarpa(scarpa);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}

	//endpoint utilizzato per ricevere l'elenco delle scarpe presenti nel db
	@GetMapping("/get")
	public ResponseEntity<Object> elencoScarpe() {
		return ResponseEntity.status(HttpStatus.OK).body(scarpaService.elencoScarpe());
	}

	//endpoint utilizzato per ricevere la scarpa passando l'id della scarpa come pathVariable
	@GetMapping("get/scarpa/{id}")
	public ResponseEntity<Object> scarpaById(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(scarpaService.getScarpaById(id));
	}
	
	//endpoint utilizzato per ricevere la lista delle scarpe presenti nel db filtrando l'elenco con uno specifico colore
	@GetMapping("get/scarpeColore/{nomeColore}")
	public ResponseEntity<Object> elencoScarpeByColore (@PathVariable("nomeColore") String nomeColore) {
		return ResponseEntity.status(HttpStatus.OK).body(scarpaService.getScarpaByColore(nomeColore));
	}
	
	//endpoint utilizzato per ricevere la lista delle scarpe presenti nel db che sono nuovi arrivi
	@GetMapping("/get/nuoviArrivi/{true}")
	public ResponseEntity<Object> getMethodName(@PathVariable("true") Boolean nuovoArrivo) {
		Object oggetto = scarpaService.getScarpeByNuovoArrivo(nuovoArrivo);
		if(oggetto instanceof Risposta) {
			Risposta risposta = (Risposta) oggetto;
			return ResponseEntity.status(risposta.getCodice()).body(risposta);
		}
		return ResponseEntity.status(HttpStatus.OK).body(oggetto);
	}
	
	//endpoint utilizzato per ricevere la lista delle scarpe presenti nel db filtrate per categoria
	@GetMapping("/get/{categoria}")
	public ResponseEntity<Object> getMethodName(@PathVariable("categoria") String categoria) {
		Object oggetto = scarpaService.getScarpeByCategoria(categoria);
		if(oggetto instanceof Risposta) {
			Risposta risposta = (Risposta) oggetto;
			return ResponseEntity.status(risposta.getCodice()).body(risposta);
		}
		return ResponseEntity.status(HttpStatus.OK).body(oggetto);
	}
	

}
