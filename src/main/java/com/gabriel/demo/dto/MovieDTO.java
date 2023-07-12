package com.gabriel.demo.dto;

import java.util.HashSet;
import java.util.Set;

import com.gabriel.demo.entities.Genre;
import com.gabriel.demo.entities.Movie;

public class MovieDTO {
	private String name;
	private String synopsis;
	private Integer releaseYear;
	private Double rate;
	private Set<Genre> genres  = new HashSet<>();
	
	public MovieDTO() {}

	public MovieDTO(Movie entity) {
		name = entity.getName();
		synopsis = entity.getSynopsis();
		releaseYear = entity.getReleaseYear();
		rate = entity.getRate();
		genres = entity.getGenres();
	}
	public MovieDTO(String name, String synopsis, Integer year, Double rate, Set<Genre> genres) {
		super();
		this.name = name;
		this.synopsis = synopsis;
		this.releaseYear = year;
		this.rate = rate;
		this.genres = genres;
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

	public Set<Genre> getGenres() {
		return genres;
	}

	
}
