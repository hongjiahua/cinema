package com.gnnu.movie.dao;

import com.gnnu.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface MovieDao extends JpaRepository<Movie, Integer>, JpaSpecificationExecutor<Integer>, Serializable {
    @Query("select t from Movie t where t.movieName like ?1 order by t.movieScore")
    List<Movie> listMovieByName(String name);

    @Query("select t from Movie t where t.movieClass=?1 order by t.movieScore")
    List<Movie> listMovieByType(String Type);

    @Query("select t from Movie t where t.movieMainActor like ?1 order by t.movieScore")
    List<Movie> listMovieByMainActor(String MainActor);

    @Query("select t from Movie t where t.movieName = ?1 ")
    Movie queryMovie(String movieName);

    @Query("select t from Movie t where t.movieId=?1")
    Movie queryMovieById(Integer movieId);
}
