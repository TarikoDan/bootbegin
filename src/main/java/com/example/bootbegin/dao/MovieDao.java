package com.example.bootbegin.dao;

import com.example.bootbegin.entiti.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<Movie, Integer> {
    void removeByTitleAndDuration(String title, int duration);
}
