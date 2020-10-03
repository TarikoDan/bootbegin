package com.example.bootbegin.controllers;

import com.example.bootbegin.entiti.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller       /*different annotations*/
//@Service
//@Repository
//@Component
//@Configuration
//@Bean

@RestController
public class MovieController {

    private List<Movie> movies = new ArrayList<>();
    {
        movies.add(new Movie(1,"Harry Potter", 138));
        movies.add(new Movie(2,"Rock Star", 117));
    }

//    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movies;
    }

    @GetMapping(value = "/movies/{id}")
//    public Movie getMovie(@RequestParam int id) {
    public Movie getMovie(@PathVariable int id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no such movie"));
    }

    //    @RequestMapping(value = "movies", method = RequestMethod.POST)
    @PostMapping("/movies")
    public Movie insertMovie(@RequestBody Movie movie) {
        movies.add(movie);
        return movie;
    }

}
