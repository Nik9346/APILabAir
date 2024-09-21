package it.labair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.labair.helper.Risposta;
import it.labair.service.CarrelloService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("cart")
public class CarrelloController {
	
	@Autowired
	CarrelloService carrelloService;
	
	@GetMapping("/get/{idUtente}")
	public ResponseEntity<Object>getCarrello(@PathVariable("idUtente") Integer idUtente, HttpServletRequest request) {
	 Object risposta = carrelloService.getCarrello(idUtente,request);
	 if(risposta instanceof Risposta)
		 return ResponseEntity.status(((Risposta)risposta).getCodice()).body((Risposta) risposta);
	 return ResponseEntity.status(HttpStatus.OK).body(risposta);
}
	
	@GetMapping("/getCartVerify/{idUtente}")
	public ResponseEntity<Object>getCarrelloForVerify(@PathVariable("idUtente") Integer idUtente, HttpServletRequest request) {
	 Object risposta = carrelloService.getCarrelloForVerify(idUtente,request);
	 if(risposta instanceof Risposta)
		 return ResponseEntity.status(((Risposta)risposta).getCodice()).body((Risposta) risposta);
	 return ResponseEntity.status(HttpStatus.OK).body(risposta);
}
	}
