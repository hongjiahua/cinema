package com.gnnu.movie.controller;

import com.gnnu.movie.entity.Movie;
import com.gnnu.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/movie")
@CrossOrigin("*")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @RequestMapping("/listAllMovie")
    @ResponseBody
    public List<Movie> listAllMovie() {
        System.out.println(movieService.listAllMovie());
        return movieService.listAllMovie();
    }

    @RequestMapping("/listMovieByName")
    @ResponseBody
    public List<Movie> listMovieByName(@RequestParam("movieName") String movieName) {
        return movieService.listMovieByName('%' + movieName + '%');
    }

    @RequestMapping("/listMovieByType")
    @ResponseBody
    public List<Movie> listMovieByType(@RequestParam("movieType") String movieType) {
        return movieService.listMovieByType(movieType);
    }

    @RequestMapping("/listMovieByMainActor")
    @ResponseBody
    public List<Movie> listMovieByMainActor(@RequestParam("mainActor") String mainActor) {
        return movieService.listMovieByMainActor('%' + mainActor + '%');
    }

    @RequestMapping("/queryMovie")
    @ResponseBody
    public Movie queryMovie(@RequestParam("movieName") String movieName) {

        return movieService.queryMovie(movieName);
    }

    @RequestMapping("/addMovie")
    @ResponseBody
    public boolean addMovie(Movie moive) {
        try {
            if (moive != null) {
                movieService.addMovie(moive);
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping("/updateMovie")
    @ResponseBody
    public boolean updateMovie(Movie moive) {
        try {
            if (moive != null) {
                movieService.updateMovie(moive);
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping("/deleteMovie")
    @ResponseBody
    public boolean deleteMovie(@RequestParam("movieId") Integer movieId) {
        try {
            Movie moive = movieService.queryMovieById(movieId);
            movieService.deleteMovie(moive);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping("/queryMovieById")
    @ResponseBody
    public Movie queryMovieById(@RequestParam("movieId") Integer movieId) {
        Movie moive = movieService.queryMovieById(movieId);
        return moive;

    }
}
