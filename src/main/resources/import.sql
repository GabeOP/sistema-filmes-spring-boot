INSERT INTO tb_movie(name, synopsis, release_year, rate) VALUES('Homem-aranha 2', 'Lorem Ipsum', 2006, 4.1);
INSERT INTO tb_movie(name, synopsis, release_year, rate) VALUES('Shrek', 'Lorem Ipsum', 2008, 4.6);
INSERT INTO tb_movie(name, synopsis, release_year, rate) VALUES('Click', 'Lorem Ipsum', 2006, 3.2);

INSERT INTO tb_genre(name) VALUES ('Animação');
INSERT INTO tb_genre(name) VALUES ('Ação');
INSERT INTO tb_genre(name) VALUES ('Comédia');

INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (1, 2);
INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (2, 1);
INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (3, 3);