package com.example.bootbegin.dao;

import com.example.bootbegin.entiti.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public interface MovieDao extends JpaRepository<Movie, Integer> {
    void removeByTitleAndDuration(String title, int duration);

//    @Query("select m from Movie m where m.title =: title")   /* JPAQL */
    Optional<Movie> findByTitle(@NotBlank String title);
    Movie getByTitle(String title);
}
