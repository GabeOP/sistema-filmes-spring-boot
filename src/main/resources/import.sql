INSERT INTO tb_movie(name, synopsis, release_year, rate) VALUES('Hustle', 'Lorem Ipsum', 2022, 7.3);
INSERT INTO tb_movie(name, synopsis, release_year, rate) VALUES('Click', 'Lorem Ipsum', 2006, 6.4);
INSERT INTO tb_movie(name, synopsis, release_year, rate) VALUES('Gente Grande', 'Lorem Ipsum', 2010, 5.9);
INSERT INTO tb_movie(name, synopsis, release_year, rate) VALUES('Como se fosse a primeira vez', 'Lorem Ipsum', 2004, 6.8);

INSERT INTO tb_genre(name) VALUES ('Animação');
INSERT INTO tb_genre(name) VALUES ('Romance');
INSERT INTO tb_genre(name) VALUES ('Drama');
INSERT INTO tb_genre(name) VALUES ('Sport');
INSERT INTO tb_genre(name) VALUES ('Comédia');

INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (1, 3);
INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (1, 4);
INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (2, 3);
INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (2, 5);
INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (3, 5);
INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (4, 2);