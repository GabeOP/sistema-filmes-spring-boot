package com.gabriel.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gabriel.demo.dto.MovieDTO;
import com.gabriel.demo.model.entities.Movie;
import com.gabriel.demo.model.exception.EmptyFieldException;
import com.gabriel.demo.model.exception.MovieNotFoundException;
import com.gabriel.demo.repositories.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository repository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public Page<MovieDTO> findAllPaged(PageRequest pageRequest) {
		Page<Movie> movies = repository.findAll(pageRequest);
		return movies.map(x ->  new MovieDTO(x));
	}
	
	public MovieDTO findById(Long id) {
		repository.existsById(id);
		
		Movie entity = repository.findById(id)
				.orElseThrow(() -> new MovieNotFoundException("[ERROR] Movie ID not found."));
		
		return new MovieDTO(entity, entity.getGenres());
	}

	public MovieDTO saveMovie(MovieDTO body) {
		Movie entity = modelMapper.map(body, Movie.class);
		repository.save(entity);
		return new MovieDTO(entity);
	}
	
	public MovieDTO updateMovie(Long id, MovieDTO body) {

		Movie entity = repository.findById(id)
				.orElseThrow(() -> new MovieNotFoundException("[ERROR] Movie ID not found."));

		entity.setName(body.getName());
		entity.setRate(body.getRate());
		entity.setReleaseYear(body.getReleaseYear());
		entity.setSynopsis(body.getSynopsis());

		repository.save(entity);
		return new MovieDTO(entity);
	}
	
	public void deleteMovie(Long id) {
		if(!repository.existsById(id)) {
			throw new MovieNotFoundException("[ERROR] Movie ID not found.");
		}
			repository.deleteById(id);
	}
}
