package com.example.bootbegin.controllers;

import com.example.bootbegin.entiti.Movie;
import com.example.bootbegin.services.IMovieService;
import com.example.bootbegin.validators.MovieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final IMovieService movieService;
    private final MovieValidator movieValidator;

    @Autowired
    public MovieController(IMovieService movieService, MovieValidator movieValidator) {
        this.movieService = movieService;
        this.movieValidator = movieValidator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie insert(@RequestBody @Valid Movie movie) {
        log.info("Handling Post /movie with " + movie);
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
    public Movie edit (@PathVariable int id, @RequestBody @Valid Movie movie) {
        return movieService.edit(id, movie);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
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

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
//        webDataBinder.addValidators(new MovieValidator()); /* 1-st method*/
        webDataBinder.addValidators(movieValidator);        /* 2-nd method via Bean*/
    }

}
