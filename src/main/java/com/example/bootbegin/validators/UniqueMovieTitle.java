package com.example.bootbegin.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueMovieTitleValidator.class)
public @interface UniqueMovieTitle {
    String message() default "Movie title should be unique";

    Class<?>[]groups() default {};              /* required fields*/
    Class<? extends Payload>[] payload() default {};
}
