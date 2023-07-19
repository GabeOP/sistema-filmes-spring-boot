package com.gabriel.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.demo.dto.GenreDTO;
import com.gabriel.demo.model.entities.Genre;
import com.gabriel.demo.model.exception.EmptyFieldException;
import com.gabriel.demo.model.exception.GenreNotFoundException;
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
		return repository.findById(id).orElseThrow(() -> new GenreNotFoundException("[ERROR] Genre ID not found."));
	}

	public GenreDTO saveGenre(Genre genre) {
		CheckIfThereEmptyFields(genre);
		Genre entity = repository.save(genre);
		return new GenreDTO(entity);
	}

	public Object updateGenre(Long id, Genre genre) {
		CheckIfThereEmptyFields(genre);
		Genre entity = repository.findById(id)
				.orElseThrow(() -> new GenreNotFoundException("[ERROR] Genre ID not found."));

		entity.setName(genre.getName());
		repository.save(entity);
		GenreDTO dto = new GenreDTO(entity);
		return dto;
	}

	public void deleteGenre(Long id) {
		if (!repository.existsById(id)) {
			throw new GenreNotFoundException("[ERROR] Genre ID not found.");
		}
		repository.deleteById(id);
	}

	// Função para verificação de todos os campos vindos do controller.
	private void CheckIfThereEmptyFields(Genre body) {
		Object[] verificaCampos = { body.getName() };

		for (Object campos : verificaCampos) {
			if (campos == null) {
				throw new EmptyFieldException("[ERROR] Complete all fields");
			}
		}
	}
}
