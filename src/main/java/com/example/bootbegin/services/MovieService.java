package com.example.bootbegin.services;

import com.example.bootbegin.dao.MovieDao;
import com.example.bootbegin.entiti.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements IMovieService{
    @Autowired
    private MovieDao movieDao;

    @Override
    public Movie create(Movie movie) {
//        if (movie.getTitle().charAt(0) > 65 || movie.getTitle().charAt(0) < 90) {
           if(Character.isLowerCase(movie.getTitle().charAt(0))) {
            throw new RuntimeException("Bad first letter");
        };
        return movieDao.save(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.findAll();
    }

    @Override
    public Movie getById(int id) {
        return movieDao.findById(id)
                .orElseThrow(() -> new NullPointerException("No such Movie with Id: " + id));
    }

    @Override
    public Movie edit(int id, Movie movie) {
        //            movie.setId(id);
//            movieController.saveAndFlush(movie);
//            return movie;

        if (movieDao.existsById(id)) {
            Movie edited = movieDao.getOne(id);
            edited.setTitle(movie.getTitle());
            edited.setDuration(movie.getDuration());
            movieDao.flush();
            return edited;
        }else {
            throw new NullPointerException("no such Movie with Id: " + id);
        }
    }

    @Override
    public void deleteAll() {
        movieDao.deleteAll();
    }

    @Override
    public void deleteById(int id) {
        movieDao.deleteById(id);
    }
}
