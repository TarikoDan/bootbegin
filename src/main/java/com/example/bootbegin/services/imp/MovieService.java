package com.example.bootbegin.services.imp;

import com.example.bootbegin.dao.MovieDao;
import com.example.bootbegin.entiti.Movie;
import com.example.bootbegin.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private MovieDao movieDao;

    @Override
    public Movie insert(Movie movie) {
        return movieDao.save(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.findAll();
    }

    @Override
    public Movie getById(int id) {
        return movieDao
                .findById(id)
                .orElseThrow(() -> new NullPointerException("there is no Movie with Id: " + id));
    }

    @Override
    public Movie edit(int id, Movie movie) {
        if (movieDao.existsById(id)) {
            movie.setId(id);
            movieDao.saveAndFlush(movie);
            return movie;
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


}
