 package it.labair.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.labair.helper.Risposta;
import it.labair.model.ScarpaCarrello;
import it.labair.service.ScarpaCarrelloService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/CartItem")
public class ScarpaCarrelloController {
	
	@Autowired
	ScarpaCarrelloService scarpaCarrelloService;
	
	
	@GetMapping("/listAll")
	public ResponseEntity<Object> elencoScarpe(HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.OK).body(scarpaCarrelloService.getScarpe(request));
	}
	
	@GetMapping("/getCart/{idScarpa}/{idTaglia}/{idColore}")
	public ResponseEntity<Object>getScarpaCarrelloNotLogged(@PathVariable("idScarpa") Integer idScarpa, @PathVariable("idTaglia")Integer idTaglia,@PathVariable("idColore")Integer idColore){
		return ResponseEntity.status(HttpStatus.OK).body(scarpaCarrelloService.getScarpaCarrelloNotLogged(idScarpa,idTaglia,idColore));
	}
	
	@PostMapping("/add")
	public ResponseEntity<Risposta>addToCartItem (@Valid @RequestBody ScarpaCarrello scarpaCarrello, HttpServletRequest request){
	Risposta risposta = scarpaCarrelloService.aggiuntaScarpa(scarpaCarrello, request);
	return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Risposta>updateCartItem (@Valid @RequestBody ScarpaCarrello scarpaCarrello, HttpServletRequest request){
		Risposta risposta = scarpaCarrelloService.modificaScarpa(scarpaCarrello, request);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}
	
	@DeleteMapping("/remove/{idScarpa}")
	public ResponseEntity<Risposta>deleteCartItem(@Valid @PathVariable("idScarpa")Integer idScarpa,HttpServletRequest request)
	{
	Risposta risposta = scarpaCarrelloService.rimozioneScarpa(idScarpa, request);
	return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}

}
