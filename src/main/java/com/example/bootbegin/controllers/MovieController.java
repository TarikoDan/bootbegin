package com.example.bootbegin.controllers;

import com.example.bootbegin.dao.MovieDao;
import com.example.bootbegin.entiti.Movie;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Movie insertMovie(@RequestBody Movie movie) {
        return movieDao.save(movie);
    }
    @PutMapping("/{id}")
    public Movie editMovie (@PathVariable int id, @RequestBody Movie movie) {
//        Movie one = movieDao.getOne(id);
//        one.setTitle(movie.getTitle());
//        one.setDuration(movie.getDuration());
//        movieDao.flush();
        if (movieDao.existsById(id)) {
            movie.setId(id);
            movieDao.saveAndFlush(movie);
            return movie;
        }
        else {
            throw new NullPointerException("no such Movie with Id " + id);
        }

    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie (@RequestBody Movie movie) {
        List<Movie> movies = movieDao.findAll();
        for (Movie m : movies) {
            if ((movie.getTitle().equals(m.getTitle()) && (movie.getDuration() == m.getDuration()))) {
                movieDao.delete(m);
            }
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByID (@PathVariable int id) {
        movieDao.deleteById(id);
    }

    @DeleteMapping("/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearAll() {
        movieDao.deleteAllInBatch();
    }

}
