package com.example.bootbegin.services;

import com.example.bootbegin.dto.response.MoviePageResponse;
import com.example.bootbegin.dto.response.MovieResponse;
import com.example.bootbegin.entiti.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IMovieService {
    MovieResponse insert(int id, Movie movie);

    List<MovieResponse> getAll();

    MoviePageResponse getAll(PageRequest pageRequest);

    MovieResponse getById(int id);

    List<MovieResponse> getByDirectorId(int directorId);

    MovieResponse edit (int id, Movie movie);

    void deleteById (int id);

    void deleteAll ();

    void remove(Movie movie);
}
