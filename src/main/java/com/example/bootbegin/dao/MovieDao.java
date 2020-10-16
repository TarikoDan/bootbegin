package com.example.bootbegin.dao;

import com.example.bootbegin.entiti.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieDao extends JpaRepository<Movie, Integer> {
    void removeByTitleAndDuration(String title, int duration);

//    @Query("select m from Movie m where m.title =: title")   /* JPAQL */
    Optional<Movie> findByTitle(String title);
    /* == */
    Movie getByTitle(String title);

//    @Query("SELECT m FROM Movie m WHERE m.director.id=?1")
//    @Query("SELECT m FROM Movie m WHERE m.director.id=:directorId") /* in this case, 2 requests will be executed as opposed to the following*/
    @Query("SELECT m FROM Movie m JOIN FETCH m.director WHERE m.director.id=:directorId")
    List<Movie> findMoviesByDirectorId(int directorId);
}
