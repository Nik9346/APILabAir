package it.labair.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.labair.helper.Risposta;
import it.labair.model.Pagamento;
import it.labair.service.PagamentoService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pay")
public class PagamentoController {
	
	@Autowired
	PagamentoService pagamentoService;
	
	@PostMapping("/payOrder")
	public ResponseEntity<Risposta> payOrder(@RequestBody Pagamento pagamento, HttpServletRequest request) {
		Risposta risposta = pagamentoService.addPagamento(pagamento, request);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}
	

}
