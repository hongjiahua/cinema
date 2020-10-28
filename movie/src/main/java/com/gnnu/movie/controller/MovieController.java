package com.gnnu.movie.controller;

import com.gnnu.movie.entity.Movie;
import com.gnnu.movie.entity.Schedule;
import com.gnnu.movie.feign.MovieFeign;
import com.gnnu.movie.service.MovieService;
import com.gnnu.movie.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/movie")
@CrossOrigin("*")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieFeign movieFeign;

    String img;

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
    public boolean addMovie(@RequestParam("movieName") String movieName,
                            @RequestParam("movieMainActor") String movieMainActor,
                            @RequestParam("movieDirector") String movieDirector,
                            @RequestParam("movieDuraction") String movieDuraction,
                            @RequestParam("movieDescription") String movieDescription,
                            @RequestParam("movieClass") String movieClass,
                                    @RequestParam("movieScore") String movieScore,
                            @RequestParam("movieState") String movieState,
                            HttpServletRequest request) {
        try {
            Movie movie=new Movie();
            movie.setMovieName(movieName);
            movie.setMovieMainActor(movieMainActor);
            movie.setMovieDirector(movieDirector);
            movie.setMovieDuraction(movieDuraction);
            movie.setMovieDescription(movieDescription);
            movie.setMovieClass(movieClass);
            movie.setMovieScore(movieScore);
            movie.setMovieState(movieState);
            movie.setMovieImage(img);
            movieService.addMovie(movie);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping("/updateMovie")
    @ResponseBody
    public boolean updateMovie(Movie moive) {
        try {
            int count = 0;
            System.out.println(moive);
            if (moive != null) {
                List<Schedule> schedules = movieFeign.listScheduleOrderByTime(moive.getMovieId());
                for (Schedule schedule : schedules) {
                    if (schedule.getScheduleEndDateTime().after(new Date(System.currentTimeMillis()))) {
                        count++;
                    }
                }
                if (count == 0) {
                    movieService.updateMovie(moive);
                    return true;
                } else {
                    return false;
                }

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
    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(MultipartFile file,HttpServletRequest request) {
        try {
            if (file == null || file.isEmpty()) {
                return "文件为空";
            }
            String path = "E:\\vuework\\movieadmin\\src\\assets\\";
            String fileName= UploadFile.uploadFile(file, path);
            img=fileName;
            return fileName;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "fail";

        }

    }

}
