package com.example.bootbegin.validator;

import com.example.bootbegin.dao.MovieDao;
import com.example.bootbegin.entiti.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMovieTitleValidator implements ConstraintValidator<UniqueMovieTitle, String> {

    @Autowired
    MovieDao movieDao;

    public void initialize(UniqueMovieTitle constraint) {
    }

    public boolean isValid(String title, ConstraintValidatorContext context) {
        if (title != null && title.length() != 0) {
            Movie movie = null;
            try {
                movie = movieDao.getByTitle(title);
            }catch (NullPointerException e) {/*e.printStackTrace();*/}
            return movie == null;
        }
        return false;
    }
}
