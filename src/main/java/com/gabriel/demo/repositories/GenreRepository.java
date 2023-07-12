package com.gabriel.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.demo.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
