package com.gabriel.demo.dto;

import com.gabriel.demo.model.entities.Genre;

import jakarta.validation.constraints.NotNull;

public class GenreDTO {
	
	@NotNull
	private String name;
	
	public GenreDTO() {}
	

	public GenreDTO(String name) {
		this.name = name;
	}

	public GenreDTO(Genre entity) {
		name = entity.getName();
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
}
