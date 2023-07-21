package com.gabriel.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.demo.dto.GenreDTO;
import com.gabriel.demo.services.GenreService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Resource
@RestController
@RequestMapping(value = "/genre")
public class GenreController {

	@Autowired
	GenreService service;

	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAll() {
		List<GenreDTO> list = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GenreDTO> findById(@PathVariable Long id) {
		GenreDTO dto = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping
	public ResponseEntity<GenreDTO> saveGenre(@Valid @RequestBody GenreDTO genre) {
		GenreDTO response = service.saveGenre(genre);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long id, @RequestBody GenreDTO body) {
		GenreDTO response = service.updateGenre(id, body);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteGenre(@PathVariable Long id) {
		service.deleteGenre(id);
		return ResponseEntity.status(HttpStatus.OK).body("Genre deleted successfully.");
	}
}
