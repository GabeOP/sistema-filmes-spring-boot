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
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Object response = movieService.findById(id);
		
		if(response.equals("Movie not found.")) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
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
		Object response = movieService.updateMovie(id, body);
		if(response.equals("Movie not found.")) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteMovie(@PathVariable Long id) {
		Object response = movieService.deleteMovie(id);
		if(response.equals("Movie not found.")) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
