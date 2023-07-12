package com.gabriel.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.demo.dto.MovieDTO;
import com.gabriel.demo.entities.Movie;
import com.gabriel.demo.repositories.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository repository;
	
	public List<MovieDTO> findAll() {
		List<Movie> movies = repository.findAll();
		List<MovieDTO> dto = movies.stream().map(x -> new MovieDTO(x)).toList();
		return dto;
	}
	
	public MovieDTO findById(Long id) {
		Movie entity = repository.findById(id).get();
		MovieDTO dto = new MovieDTO(entity);
		return dto;
	}

	public MovieDTO saveMovie(Movie body) {
		repository.save(body);
		MovieDTO dto = new MovieDTO();
		
		dto.setName(body.getName());
		dto.setRate(body.getRate());
		dto.setSynopsis(body.getSynopsis());
		dto.setReleaseYear(body.getReleaseYear());
		return dto;
	}
}
