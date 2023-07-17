package com.gabriel.demo.dto;

import com.gabriel.demo.entities.Genre;

public class GenreDTO {
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
