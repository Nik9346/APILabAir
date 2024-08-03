package it.labair.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.labair.dto.ScarpaDto;
import it.labair.service.ScarpaService;

@RestController
@RequestMapping("/scarpe")
public class ScarpaController {
	
	@Autowired
	ScarpaService scarpaService;

	
	@GetMapping("/get")
	public ResponseEntity<List<ScarpaDto>> elencoScarpe(){
		return ResponseEntity.status(HttpStatus.OK).body(scarpaService.elencoScarpe());
	}
}
