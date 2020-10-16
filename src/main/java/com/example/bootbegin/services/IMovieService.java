package com.example.bootbegin.services;

import com.example.bootbegin.dto.response.MovieResponse;
import com.example.bootbegin.entiti.Movie;

import java.util.List;

public interface IMovieService {
    MovieResponse insert(int id, Movie movie);

    List<MovieResponse> getAll();

    MovieResponse getById(int id);

    List<MovieResponse> getByDirectorId(int directorId);

    MovieResponse edit (int id, Movie movie);

    void deleteById (int id);

    void deleteAll ();

    void remove(Movie movie);
}
