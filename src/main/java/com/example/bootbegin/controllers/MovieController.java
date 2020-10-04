package com.example.bootbegin.controllers;

import com.example.bootbegin.entiti.Movie;
import com.example.bootbegin.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private IMovieService movieService;

    @Autowired
    public MovieController(IMovieService movieController) {
        this.movieService = movieController;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Movie getMovie(@PathVariable int id) {
        return movieService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie insertMovie(@Valid @RequestBody Movie movie) {
        return movieService.create(movie);
    }

    @PutMapping("/{id}")
    public Movie editMovie (@Valid @PathVariable int id, @Valid @RequestBody Movie movie) {
        return movieService.edit(id, movie);
    }

//    @DeleteMapping
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteMovie (@RequestBody Movie movie) {
//        List<Movie> movies = movieController.findAll();
//        for (Movie m : movies) {
//            if ((movie.getTitle().equals(m.getTitle()) && (movie.getDuration() == m.getDuration()))) {
//                movieController.delete(m);
//            }
//        }
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteByID (@PathVariable int id) {
        return movieService.deleteById(id);
    }

    @DeleteMapping("/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearAll() {
        movieService.deleteAll();
    }

}
