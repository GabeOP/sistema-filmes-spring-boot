package com.gabriel.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.demo.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
