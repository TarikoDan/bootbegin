package com.example.bootbegin.dao;

import com.example.bootbegin.entiti.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DirectorDAO extends JpaRepository<Director, Integer> {
//    @Query("SELECT d FROM Director d where d.movies")
    @Query("SELECT m.director FROM Movie m where m.title=:movieTitle")
    Director getDirectorByMoviesContainsMovieByTitle(String movieTitle);
}
