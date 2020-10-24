package com.gnnu.movie.service;

import com.gnnu.movie.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> listAllMovie();

    List<Movie> listMovieByName(String name);

    List<Movie> listMovieByType(String Type);

    List<Movie> listMovieByMainActor(String MainActor);

    Movie queryMovie(String movieName);

    Movie queryMovieById(Integer movieId);


    void addMovie(Movie moive);

    void updateMovie(Movie moive);

    void deleteMovie(Movie moive);

}
