package com.example.bootbegin.services;

import com.example.bootbegin.entiti.Movie;

import java.util.List;

public interface IMovieService {
    Movie insert(Movie movie);

    List<Movie> getAll();

    Movie getById(int id);

    Movie edit (int id, Movie movie);

    void deleteById (int id);

    void deleteAll ();

    void remove(Movie movie);
}
