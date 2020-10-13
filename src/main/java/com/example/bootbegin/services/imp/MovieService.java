package com.example.bootbegin.services.imp;

import com.example.bootbegin.dao.DirectorDAO;
import com.example.bootbegin.dao.MovieDao;
import com.example.bootbegin.dto.response.MovieResponse;
import com.example.bootbegin.entiti.Director;
import com.example.bootbegin.entiti.Movie;
import com.example.bootbegin.exception.LongDurationException;
import com.example.bootbegin.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private DirectorDAO directorDAO;

    @Override
    public MovieResponse insert(int id, Movie movie) {
        if (movie.getDuration() > 300) {
            throw new LongDurationException("Duration should be less than 300 min");
        }
        Director director = directorDAO.findById(id)
                .orElseThrow(() -> new NullPointerException("there is no Movie with Id: " + id));
        movie.setDirector(director);
        movie = movieDao.save(movie);

        return convertToResponse(movie);
    }

    @Override
    public List<MovieResponse> getAll() {
        ArrayList<MovieResponse> movies = new ArrayList<>();
        movieDao.findAll().forEach(movie -> movies.add(convertToResponse(movie)));
        return movies;
    }

    @Override
    public MovieResponse getById(int id) {
        Movie movie = movieDao
                .findById(id)
                .orElseThrow(() -> new NullPointerException("there is no Movie with Id: " + id));
        return convertToResponse(movie);
    }

    @Override
    public MovieResponse edit(int id, Movie movie) {
        if (movieDao.existsById(id)) {
            movie.setId(id);
            if (null == movie.getDirector()) {
                movie.setDirector(movieDao.getOne(id).getDirector());
            };
            movieDao.saveAndFlush(movie);
            return convertToResponse(movie);
        }
        else {
            throw new NullPointerException("no such Movie with Id: " + id);
        }
    }

    @Override
    public void deleteById(int id) {
        if (movieDao.existsById(id)) {
            movieDao.deleteById(id);
        }else {
                throw new NullPointerException("no such Movie with Id: " + id);
            }
    }

    @Override
    public void deleteAll() {
        movieDao.deleteAll();
    }

    @Override
    public void remove(Movie movie) {
        movieDao.removeByTitleAndDuration(movie.getTitle(), movie.getDuration());
    }

    private MovieResponse convertToResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .duration(movie.getDuration())
                .directorName(movie.getDirector().getName())
                .build();
    }

}
