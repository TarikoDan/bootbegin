package com.example.bootbegin.services;

import com.example.bootbegin.entiti.Director;

import java.util.List;

public interface IDirectorService {
    Director insert(Director director);

    List<Director> getAll();

    Director getById(int id);

    Director getByMovieTitle(String movieTitle);

    Director edit(int id, Director director);

    void deleteById(int id);

    void clear();
}
