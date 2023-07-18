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
import com.gabriel.demo.model.entities.Genre;
import com.gabriel.demo.model.exception.GenreNotFoundException;
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
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		
		try {
			Genre entity = service.findById(id);
			GenreDTO dto = new GenreDTO(entity);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}catch(GenreNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> saveGenre(@RequestBody Genre genre) {
		Object[] verificaCampos = {genre.getName()};
		
		for(Object campos : verificaCampos) {
			if(campos == null) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body("[ERROR] Complete all fields.");
			}
		}
		GenreDTO response = service.saveGenre(genre);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateGenre(@PathVariable Long id, @RequestBody Genre body) {
		
		try {
			Object response = service.updateGenre(id, body);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}catch(GenreNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteGenre(@PathVariable Long id) {
		
		try {
			service.deleteGenre(id);
			return ResponseEntity.status(HttpStatus.OK).body("Genre deleted successfully.");
		}catch(GenreNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
