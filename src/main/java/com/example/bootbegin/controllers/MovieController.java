package com.example.bootbegin.controllers;

import com.example.bootbegin.dao.MovieDao;
import com.example.bootbegin.entiti.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieDao.findAll();
    }

    @GetMapping(value = "/{id}")
    public Movie getMovie(@PathVariable int id) {
        return movieDao.findById(id).orElseThrow(() -> new NullPointerException("there is no Movie with Id " + id));
    }
    @PostMapping
    public Movie insertMovie(@RequestBody Movie movie) {
        return movieDao.save(movie);
    }
    @PutMapping("/{id}")
    public Movie editMovie (@PathVariable int id, @RequestBody Movie movie) {
        Movie one = movieDao.getOne(id);
        one.setTitle(movie.getTitle());
        one.setDuration(movie.getDuration());
        movieDao.flush();
        movie.setId(id);
        return movie;
    }

}
