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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.labair.dto.ScarpaDto;
import it.labair.model.Categoria;
import it.labair.model.Colore;
import it.labair.model.Scarpa;
import it.labair.model.Taglia;
import it.labair.service.ScarpaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/scarpe")
public class ScarpaController {

	@Autowired
	ScarpaService scarpaService;

	@PostMapping("/add")
	public void registrazioneScarpa(@RequestParam Scarpa scarpa) {
		scarpaService.registraScarpa(scarpa);
	}

	@GetMapping("/get")
	public ResponseEntity<List<ScarpaDto>> elencoScarpe() {
		return ResponseEntity.status(HttpStatus.OK).body(scarpaService.elencoScarpe());
	}

	@GetMapping("get/scarpa/{id}")
	public ResponseEntity<Object> scarpaById(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(scarpaService.getScarpaById(id));
	}

}
