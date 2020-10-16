package com.example.bootbegin.controllers;

import com.example.bootbegin.dto.response.MovieResponse;
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

    @PostMapping("/director/{directorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponse insert(@PathVariable int directorId, @RequestBody @Valid Movie movie) {
        log.info("Handling Post /movie with " + movie);
        return movieService.insert(directorId, movie);
    }

    @GetMapping
    public List<MovieResponse> getAll() {
        return movieService.getAll();
    }

    @GetMapping(value = "/{id}")
    public MovieResponse getById(@PathVariable int id) {
        return movieService.getById(id);
    }

    @GetMapping(value = "director/{directorId}")
    public List<MovieResponse> getByDirectorId(@PathVariable int directorId) {
        return movieService.getByDirectorId(directorId);
    }

    @PutMapping("/{id}")
    public MovieResponse edit (@PathVariable int id, @RequestBody @Valid Movie movie) {
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

    @DeleteMapping("/clear")
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
