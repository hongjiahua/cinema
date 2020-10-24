package com.gnnu.movie.service.impl;

import com.gnnu.movie.dao.MovieDao;
import com.gnnu.movie.entity.Movie;
import com.gnnu.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;

    @Override
    @CachePut(value = "allmovies")
    public List<Movie> listAllMovie() {
        Sort sort = Sort.by(Sort.Direction.DESC, "movieId");
        return movieDao.findAll(sort);

    }

    @Override
    @CachePut(value = "listMovieByName", key = "#name")
    public List<Movie> listMovieByName(String name) {
        return movieDao.listMovieByName(name);
    }

    @CachePut(value = "listMovieByType", key = "#Type")
    @Override
    public List<Movie> listMovieByType(String Type) {
        return movieDao.listMovieByType(Type);
    }

    @CachePut(value = "listMovieByMainActor", key = "#MainActor")
    @Override
    public List<Movie> listMovieByMainActor(String MainActor) {
        return movieDao.listMovieByMainActor(MainActor);
    }

    @CachePut(value = "queryMovie", key = "#movieName")
    @Override
    public Movie queryMovie(String movieName) {
        return movieDao.queryMovie(movieName);
    }

    @Override
    @CachePut(value = "queryMovieById", key = "#movieId")
    public Movie queryMovieById(Integer movieId) {
        return movieDao.queryMovieById(movieId);
    }


    @Transactional
    @Modifying
    @Override
    public void addMovie(Movie moive) {
        movieDao.save(moive);
    }

    @Transactional
    @Modifying
    @Override
    public void updateMovie(Movie moive) {
        movieDao.save(moive);
    }

    @Transactional
    @Modifying
    @Override
    public void deleteMovie(Movie moive) {
        movieDao.delete(moive);
    }
}
