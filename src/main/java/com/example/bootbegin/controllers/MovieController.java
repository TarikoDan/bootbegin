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

    private final IMovieService movieService;

    @Autowired
    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie insert(@RequestBody @Valid Movie movie) {
        return movieService.insert(movie);
    }

    @GetMapping
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Movie getById(@PathVariable int id) {
        return movieService.getById(id);
    }

    @PutMapping("/{id}")
    public Movie edit (@PathVariable int id, @RequestBody Movie movie) {
        return movieService.edit(id, movie);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove (@RequestBody Movie movie) {
        movieService.remove(movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByID (@PathVariable int id) {
        movieService.deleteById(id);
    }

    @DeleteMapping("/clea")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearAll() {
        movieService.deleteAll();
    }

}
