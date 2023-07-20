package com.gabriel.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gabriel.demo.model.entities.Genre;
import com.gabriel.demo.model.entities.Movie;

import jakarta.validation.constraints.NotNull;

public class MovieDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String name;
	
	@NotNull
	private String synopsis;
	
	@NotNull
	private Integer releaseYear;
	
	@NotNull
	private Double rate;
	private List<GenreDTO> genres = new ArrayList<>();
	
	public MovieDTO() {}

	public MovieDTO(String name, String synopsis, Integer year, Double rate) {
		this.name = name;
		this.synopsis = synopsis;
		this.releaseYear = year;
		this.rate = rate;
	}
	
	public MovieDTO(Movie entity) {
		name = entity.getName();
		synopsis = entity.getSynopsis();
		releaseYear = entity.getReleaseYear();
		rate = entity.getRate();
	}
	
	public MovieDTO(Movie entity, Set<Genre> genres) {
		this(entity);
		genres.forEach(x -> this.genres.add(new GenreDTO(x)));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public List<GenreDTO> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDTO> genres) {
		this.genres = genres;
	}

	
}
