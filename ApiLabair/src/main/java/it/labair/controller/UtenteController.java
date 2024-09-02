package it.labair.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.labair.helper.Risposta;
import it.labair.model.Utente;
import it.labair.service.UtenteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	@GetMapping("/{utenteId}")
	public ResponseEntity<Object> utenteById(@PathVariable("utenteId") Integer id) {
		Object oggettoRisposta = utenteService.utenteById(id);
		if (oggettoRisposta instanceof Risposta) {
			Risposta risposta = (Risposta) oggettoRisposta;
			return ResponseEntity.status(risposta.getCodice()).body(risposta);
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(oggettoRisposta);
		}
	}

	@GetMapping("/username/{usernameUtente}")
	public ResponseEntity<Object> utenteByUsername(@PathVariable("usernameUtente") String username) {
		Object oggettoRisposta = utenteService.utenteByUsername(username);
		if (oggettoRisposta instanceof Risposta) {
			Risposta risposta = (Risposta) oggettoRisposta;
			return ResponseEntity.status(risposta.getCodice()).body(risposta);
		}
		return ResponseEntity.status(HttpStatus.OK).body(utenteService.utenteByUsername(username));
	}

	@PutMapping("/login")
	public ResponseEntity<Risposta> loginUtente(@RequestBody Map<String, String> corpoRichiesta) {
		Risposta risposta = utenteService.loginUtente(corpoRichiesta.get("username"), corpoRichiesta.get("password"));
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}

	@PostMapping("/registrazioneUtente")
	public ResponseEntity<Risposta> registrazioneUtente(@Valid @RequestBody Utente utente) {
		if (utente.getProfilo().getPassword()
				.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,50}"))
			;
		Risposta risposta = utenteService.registraUtente(utente);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}

	@DeleteMapping("/logout/{token}")
	public ResponseEntity<Risposta> logoutUtente(@Valid @PathVariable("token") String token) {
		Risposta risposta = utenteService.logoutUtente(token);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> entityNotFound(EntityNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

}
