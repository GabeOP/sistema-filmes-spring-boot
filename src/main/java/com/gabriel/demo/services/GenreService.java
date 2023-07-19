package com.gabriel.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
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
	
	@Autowired
	ModelMapper modelMapper;

	public List<GenreDTO> findAll() {
		List<Genre> genres = repository.findAll();
		List<GenreDTO> dto = genres.stream().map(x -> new GenreDTO(x)).toList();
		return dto;
	}

	public GenreDTO findById(Long id) {
		Genre entity = repository.findById(id)
				.orElseThrow(() -> new GenreNotFoundException("[ERROR] Genre ID not found."));
		return new GenreDTO(entity);
	}

	public GenreDTO saveGenre(GenreDTO dto) {
		CheckIfThereEmptyFields(dto);
		Genre entity = modelMapper.map(dto, Genre.class);
		repository.save(entity);
		return new GenreDTO(entity);
	}

	public GenreDTO updateGenre(Long id, GenreDTO dto) {
		CheckIfThereEmptyFields(dto);
		Genre entity = repository.findById(id)
				.orElseThrow(() -> new GenreNotFoundException("[ERROR] Genre ID not found."));

		entity.setName(dto.getName());
		repository.save(entity);
		return new GenreDTO(entity);
	}

	public void deleteGenre(Long id) {
		if (!repository.existsById(id)) {
			throw new GenreNotFoundException("[ERROR] Genre ID not found.");
		}
		repository.deleteById(id);
	}

	// Função para verificação de todos os campos vindos do controller.
	private void CheckIfThereEmptyFields(GenreDTO body) {
		Object[] verificaCampos = { body.getName() };

		for (Object campos : verificaCampos) {
			if (campos == null) {
				throw new EmptyFieldException("[ERROR] Complete all fields");
			}
		}
	}
}
