package com.example.bootbegin.services;

import com.example.bootbegin.entiti.Movie;

import java.util.List;

public interface IMovieService {

    Movie create(Movie movie);

    List<Movie> getAll();

    Movie getById(int id);

    Movie edit(int id, Movie movie);

    void deleteAll();

    void deleteById(int id);
}
