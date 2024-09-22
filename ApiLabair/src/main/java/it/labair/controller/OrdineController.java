package it.labair.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.labair.helper.Risposta;
import it.labair.model.Ordine;
import it.labair.service.OrdineService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/ordine")
public class OrdineController {
	
	@Autowired
	OrdineService ordineService;

	//enpoint utilizzato per aggiungere un ordine
	@PostMapping("/addOrder")
	public ResponseEntity<Risposta> postMethodName(@RequestBody @Valid Ordine ordine, HttpServletRequest request) {
		Risposta risposta = ordineService.addOrder(ordine, request);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
		
	}
	
	//endpoint utilizzato per ottenere l'ordine dell'utente, passando come pathVariable l'id dell'utente
	@GetMapping("/getOrder/{idUtente}")
	public ResponseEntity<Object> getOrderByUtente (@PathVariable("idUtente") Integer idUtente, HttpServletRequest request) {
		Object oggetto = ordineService.getOrderByUtente(idUtente, request);
		if(oggetto instanceof Risposta) {
			Risposta risposta = (Risposta) oggetto;
			return ResponseEntity.status(risposta.getCodice()).body(risposta);
		}
		return ResponseEntity.status(HttpStatus.OK).body(oggetto);
	}
	
	
}
