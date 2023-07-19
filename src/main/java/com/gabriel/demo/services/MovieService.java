package com.gabriel.demo.services;

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
	
	public Page<MovieDTO> findAllPaged(PageRequest pageRequest) {
		Page<Movie> movies = repository.findAll(pageRequest);
		return movies.map(x ->  new MovieDTO(x));
	}
	
	public Object findById(Long id) {
		repository.existsById(id);
		
		Movie entity = repository.findById(id)
				.orElseThrow(() -> new MovieNotFoundException("[ERROR] Movie ID not found."));
		
		return new MovieDTO(entity, entity.getGenres());
	}

	public Object saveMovie(Movie body) {
		
		CheckIfThereEmptyFields(body);
		repository.save(body);
		MovieDTO dto = new MovieDTO(body);
 
		return dto;
		
	}
	
	public Object updateMovie(Long id, Movie body) {

		CheckIfThereEmptyFields(body);

		Movie entity = repository.findById(id)
				.orElseThrow(() -> new MovieNotFoundException("[ERROR] Movie ID not found."));

		entity.setName(body.getName());
		entity.setRate(body.getRate());
		entity.setReleaseYear(body.getReleaseYear());
		entity.setSynopsis(body.getSynopsis());

		repository.save(entity);
		MovieDTO dto = new MovieDTO(entity);
		return dto;
	}
	
	public void deleteMovie(Long id) {
		if(!repository.existsById(id)) {
			throw new MovieNotFoundException("[ERROR] Movie ID not found.");
		}
			repository.deleteById(id);
	}
	
	//Função para verificação de todos os campos vindos do controller.
	private void CheckIfThereEmptyFields(Movie body) {
		Object[] verificaCampos = {body.getName(), body.getRate(), body.getReleaseYear(), body.getSynopsis()};
		for(Object campos : verificaCampos) {
			if(campos == null) {
				throw new EmptyFieldException("[ERROR] Complete all fields");
			}
		}
	}
}
