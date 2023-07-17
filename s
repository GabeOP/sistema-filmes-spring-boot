[1mdiff --git a/src/main/java/com/gabriel/demo/dto/GenreDTO.java b/src/main/java/com/gabriel/demo/dto/GenreDTO.java[m
[1mindex edf4d60..72029f0 100644[m
[1m--- a/src/main/java/com/gabriel/demo/dto/GenreDTO.java[m
[1m+++ b/src/main/java/com/gabriel/demo/dto/GenreDTO.java[m
[36m@@ -1,40 +1,28 @@[m
 package com.gabriel.demo.dto;[m
 [m
[31m-import java.util.HashSet;[m
[31m-import java.util.Set;[m
[31m-[m
 import com.gabriel.demo.entities.Genre;[m
[31m-import com.gabriel.demo.entities.Movie;[m
 [m
 public class GenreDTO {[m
 	private String name;[m
[31m-	private Set<Movie> movies = new HashSet<>();[m
 	[m
 	public GenreDTO() {}[m
 	[m
[31m-	public GenreDTO(Genre entity) {[m
[31m-		name = entity.getName();[m
[31m-		movies = entity.getMovies();[m
[31m-	}[m
 [m
[31m-	public GenreDTO(String name, Set<Movie> movies) {[m
[32m+[m	[32mpublic GenreDTO(String name) {[m
 		this.name = name;[m
[31m-		this.movies = movies;[m
 	}[m
 [m
[32m+[m	[32mpublic GenreDTO(Genre entity) {[m
[32m+[m		[32mname = entity.getName();[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
 	public String getName() {[m
 		return name;[m
 	}[m
 [m
[32m+[m
 	public void setName(String name) {[m
 		this.name = name;[m
 	}[m
[31m-[m
[31m-	public Set<Movie> getMovies() {[m
[31m-		return movies;[m
[31m-	}[m
[31m-[m
[31m-	public void setMovies(Set<Movie> movies) {[m
[31m-		this.movies = movies;[m
[31m-	}[m
[32m+[m[41m	[m
 }[m
[1mdiff --git a/src/main/java/com/gabriel/demo/dto/MovieDTO.java b/src/main/java/com/gabriel/demo/dto/MovieDTO.java[m
[1mindex 3474f2d..e464d23 100644[m
[1m--- a/src/main/java/com/gabriel/demo/dto/MovieDTO.java[m
[1m+++ b/src/main/java/com/gabriel/demo/dto/MovieDTO.java[m
[36m@@ -1,34 +1,42 @@[m
 package com.gabriel.demo.dto;[m
 [m
[31m-import java.util.HashSet;[m
[32m+[m[32mimport java.io.Serializable;[m
[32m+[m[32mimport java.util.ArrayList;[m
[32m+[m[32mimport java.util.List;[m
 import java.util.Set;[m
 [m
 import com.gabriel.demo.entities.Genre;[m
 import com.gabriel.demo.entities.Movie;[m
 [m
[31m-public class MovieDTO {[m
[32m+[m[32mpublic class MovieDTO implements Serializable {[m
[32m+[m[41m	[m
[32m+[m	[32mprivate static final long serialVersionUID = 1L;[m
[32m+[m[41m	[m
 	private String name;[m
 	private String synopsis;[m
 	private Integer releaseYear;[m
 	private Double rate;[m
[31m-	private Set<Genre> genres  = new HashSet<>();[m
[32m+[m	[32mprivate List<GenreDTO> genres = new ArrayList<>();[m
 	[m
 	public MovieDTO() {}[m
 [m
[32m+[m	[32mpublic MovieDTO(String name, String synopsis, Integer year, Double rate) {[m
[32m+[m		[32mthis.name = name;[m
[32m+[m		[32mthis.synopsis = synopsis;[m
[32m+[m		[32mthis.releaseYear = year;[m
[32m+[m		[32mthis.rate = rate;[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
 	public MovieDTO(Movie entity) {[m
 		name = entity.getName();[m
 		synopsis = entity.getSynopsis();[m
 		releaseYear = entity.getReleaseYear();[m
 		rate = entity.getRate();[m
[31m-		genres = entity.getGenres();[m
 	}[m
[31m-	public MovieDTO(String name, String synopsis, Integer year, Double rate, Set<Genre> genres) {[m
[31m-		super();[m
[31m-		this.name = name;[m
[31m-		this.synopsis = synopsis;[m
[31m-		this.releaseYear = year;[m
[31m-		this.rate = rate;[m
[31m-		this.genres = genres;[m
[32m+[m[41m	[m
[32m+[m	[32mpublic MovieDTO(Movie entity, Set<Genre> genres) {[m
[32m+[m		[32mthis(entity);[m
[32m+[m		[32mgenres.forEach(x -> this.genres.add(new GenreDTO(x)));[m
 	}[m
 [m
 	public String getName() {[m
[36m@@ -63,9 +71,13 @@[m [mpublic class MovieDTO {[m
 		this.rate = rate;[m
 	}[m
 [m
[31m-	public Set<Genre> getGenres() {[m
[32m+[m	[32mpublic List<GenreDTO> getGenres() {[m
 		return genres;[m
 	}[m
 [m
[32m+[m	[32mpublic void setGenres(List<GenreDTO> genres) {[m
[32m+[m		[32mthis.genres = genres;[m
[32m+[m	[32m}[m
[32m+[m
 	[m
 }[m
[1mdiff --git a/src/main/java/com/gabriel/demo/entities/Movie.java b/src/main/java/com/gabriel/demo/entities/Movie.java[m
[1mindex 561f63d..9f2f512 100644[m
[1m--- a/src/main/java/com/gabriel/demo/entities/Movie.java[m
[1m+++ b/src/main/java/com/gabriel/demo/entities/Movie.java[m
[36m@@ -18,7 +18,7 @@[m [mimport jakarta.persistence.Table;[m
 @Entity[m
 @Table(name = "tb_movie")[m
 public class Movie {[m
[31m-	[m
[32m+[m
 	@Id[m
 	@GeneratedValue(strategy = GenerationType.IDENTITY)[m
 	private Long id;[m
[36m@@ -32,7 +32,7 @@[m [mpublic class Movie {[m
 	joinColumns = @JoinColumn(name = "movie_id"),[m
 	inverseJoinColumns = @JoinColumn(name = "genre_id"))[m
 	@JsonIgnore[m
[31m-	private Set<Genre> genres  = new HashSet<>();[m
[32m+[m	[32mSet<Genre> genres  = new HashSet<>();[m
 	[m
 	public Movie() {}[m
 [m
[1mdiff --git a/src/main/java/com/gabriel/demo/services/MovieService.java b/src/main/java/com/gabriel/demo/services/MovieService.java[m
[1mindex 8be24fc..5aa900f 100644[m
[1m--- a/src/main/java/com/gabriel/demo/services/MovieService.java[m
[1m+++ b/src/main/java/com/gabriel/demo/services/MovieService.java[m
[36m@@ -19,13 +19,13 @@[m [mpublic class MovieService {[m
 	[m
 	public Page<MovieDTO> findAllPaged(PageRequest pageRequest) {[m
 		Page<Movie> movies = repository.findAll(pageRequest);[m
[31m-		return movies.map(x -> new MovieDTO(x));[m
[32m+[m		[32mreturn movies.map(x ->  new MovieDTO(x));[m
 	}[m
 	[m
 	public Object findById(Long id) {[m
 		try {[m
 			Movie entity = repository.findById(id).get();[m
[31m-			MovieDTO dto = new MovieDTO(entity);[m
[32m+[m			[32mMovieDTO dto = new MovieDTO(entity, entity.getGenres());[m
 			return dto;[m
 		}catch(NoSuchElementException e) {[m
 			return "Movie not found.";[m
[36m@@ -40,11 +40,7 @@[m [mpublic class MovieService {[m
 		[m
 		repository.save(body);[m
 		MovieDTO dto = new MovieDTO(body);[m
[31m-		[m
[31m-		dto.setName(body.getName());[m
[31m-		dto.setRate(body.getRate());[m
[31m-		dto.setSynopsis(body.getSynopsis());[m
[31m-		dto.setReleaseYear(body.getReleaseYear());[m
[32m+[m[41m [m
 		return dto;[m
 	}[m
 	[m
