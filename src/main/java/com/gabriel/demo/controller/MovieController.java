package com.gabriel.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.demo.dto.MovieDTO;
import com.gabriel.demo.model.entities.Movie;
import com.gabriel.demo.model.exception.MovieNotFoundException;
import com.gabriel.demo.services.MovieService;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping("/movie")	
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<MovieDTO> listDto = movieService.findAllPaged(pageRequest);
		
		return ResponseEntity.status(HttpStatus.OK).body(listDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			Object response = movieService.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(MovieNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<Object> saveMovie(@RequestBody Movie body) {
		Object[] verificaCampos = {body.getName(), body.getRate(), body.getReleaseYear(), body.getSynopsis()};
		for(Object campos : verificaCampos) {
			if(campos == null) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body("[ERROR] Complete all fields.");
			}
		}
		Object response = movieService.saveMovie(body);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateMovie(@PathVariable Long id, @RequestBody Movie body) {
		try {
			Object response = movieService.updateMovie(id, body);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(MovieNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteMovie(@PathVariable Long id) {
		try {
			movieService.deleteMovie(id);	
			return ResponseEntity.status(HttpStatus.OK).body("Movie deleted successfully");
		}catch(MovieNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
