package com.example.bootbegin.validator;

import com.example.bootbegin.entiti.Movie;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MovieValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Movie.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Movie movie = (Movie) o;
        if (movie.getTitle() != null && movie.getTitle().trim().length() > 0) {
            if (Character.isLowerCase(movie.getTitle().charAt(0))) {  /* Check the Capital letter in Title*/
                errors.rejectValue("title", "movie.title.capital-letter"
                        , "Title should start with capital letter");
            }
        }
    }
}
