package com.gabriel.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.demo.dto.GenreDTO;
import com.gabriel.demo.entities.Genre;
import com.gabriel.demo.services.GenreService;

import jakarta.annotation.Resource;

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
		Genre entity = service.findById(id);
		GenreDTO dto = new GenreDTO(entity);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@PostMapping
	public ResponseEntity<Genre> saveGenre(@RequestBody Genre genre) {
		service.saveGenre(genre);
		return ResponseEntity.status(HttpStatus.CREATED).body(genre);
	}
	
}
