package com.gabriel.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

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
	
	public Object findById(Long id) {
		try {
			Movie entity = repository.findById(id).get();
			MovieDTO dto = new MovieDTO(entity);
			return dto;
		}catch(NoSuchElementException e) {
			return "Movie not found.";
		}
	}

	public Object saveMovie(Movie body) {
		
		if(repository.existsByName(body.getName())) {
			return "Movie already registered";
		}
		
		repository.save(body);
		MovieDTO dto = new MovieDTO(body);
		
		dto.setName(body.getName());
		dto.setRate(body.getRate());
		dto.setSynopsis(body.getSynopsis());
		dto.setReleaseYear(body.getReleaseYear());
		return dto;
	}
	
	public Object updateMovie(Long id, Movie body) {
		try {
			Movie entity = repository.findById(id).get();
			entity.setName(body.getName());
			entity.setRate(body.getRate());
			entity.setReleaseYear(body.getReleaseYear());
			entity.setSynopsis(body.getSynopsis());
			repository.save(entity);
			MovieDTO dto = new MovieDTO(entity);
			return dto;
		}catch(NoSuchElementException e) {
			return "Movie not found.";
		}
	}
	
	public Object deleteMovie(Long id) {
		try {
			repository.findById(id).get();
			repository.deleteById(id);
			return "Movie deleted successfully";
		}catch(NoSuchElementException e) {
			return "Movie not found.";
		}
	}
}
