package it.labair.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.labair.helper.Risposta;
import it.labair.model.Carrello;
import it.labair.model.Utente;
import it.labair.service.OrdineService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ordine")
public class OrdineController {
	
	@Autowired
	OrdineService ordineService;

	@PostMapping("/aggiungiOrdine")
	public ResponseEntity<Risposta> postMethodName(@RequestBody @Valid Carrello carrello, HttpServletRequest request) {
		Risposta risposta = ordineService.aggiungiOrdine(carrello, request);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
		
	}
	
}
