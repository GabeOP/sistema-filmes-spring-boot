package com.gabriel.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class MovieResource {
	
	@Autowired
	MovieService movieService;
	
	@GetMapping
	public ResponseEntity<List<MovieDTO>> findAll() {
		List<MovieDTO> listDto = movieService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listDto);
	}

	@PostMapping
	public ResponseEntity<MovieDTO> saveMovie(@RequestBody Movie body) {
		movieService.saveMovie(body);
		MovieDTO dto = new MovieDTO(body);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
}
