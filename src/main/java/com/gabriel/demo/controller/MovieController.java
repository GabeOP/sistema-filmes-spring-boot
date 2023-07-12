package com.gabriel.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.demo.dto.MovieDTO;
import com.gabriel.demo.entities.Movie;
import com.gabriel.demo.services.MovieService;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping("/movie")	
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@GetMapping
	public ResponseEntity<List<MovieDTO>> findAll() {
		List<MovieDTO> listDto = movieService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		MovieDTO dto = movieService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping
	public ResponseEntity<MovieDTO> saveMovie(@RequestBody Movie body) {
		movieService.saveMovie(body);
		MovieDTO dto = new MovieDTO(body);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @RequestBody Movie body) {
		MovieDTO dto = movieService.updateMovie(id, body);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
}
