package com.gabriel.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.demo.dto.GenreDTO;
import com.gabriel.demo.model.entities.Genre;
import com.gabriel.demo.repositories.GenreRepository;

@Service
public class GenreService {

	@Autowired
	GenreRepository repository;
	
	public List<GenreDTO> findAll() {
		List<Genre> genres = repository.findAll();
		List<GenreDTO> dto = genres.stream().map(x -> new GenreDTO(x)).toList();
		return dto;
	}
	
	public Genre findById(Long id) {
		return repository.findById(id).get();
	}
	
	public GenreDTO saveGenre(Genre genre) {
		Genre entity = repository.save(genre);
		return new GenreDTO(entity);
	}
	
	public Object updateGenre(Long id, Genre genre) {
		try {
			Genre entity = repository.findById(id).get();
			entity.setName(genre.getName());
			repository.save(entity);
			GenreDTO dto = new GenreDTO(entity);
			return dto;
		}catch(NoSuchElementException e) {
			return "Genre not found.";
		}
	}
	
	public void deleteGenre(Long id) {
		if(!repository.existsById(id)) {
			throw new NoSuchElementException("Genre not found.");
		}
		repository.deleteById(id);
	}
	
}
