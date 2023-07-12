package com.gabriel.demo.dto;

import java.util.HashSet;
import java.util.Set;

import com.gabriel.demo.entities.Genre;
import com.gabriel.demo.entities.Movie;

public class GenreDTO {
	private Long id;
	private String name;
	private Set<Movie> movies = new HashSet<>();
	
	public GenreDTO() {}
	
	public GenreDTO(Genre entity) {
		id = entity.getId();
		name = entity.getName();
		movies = entity.getMovies();
	}

	public GenreDTO(Long id, String name, Set<Movie> movies) {
		this.id = id;
		this.name = name;
		this.movies = movies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
}
