package com.example.bootbegin.entiti;

import com.example.bootbegin.validator.UniqueMovieTitle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = "hibernateLazyInitializer")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 270)
    @NotBlank               /*Predefined validators*/
    @UniqueMovieTitle       /* own annotation */
    private String title;
    @Positive               /*Predefined validators*/
    private int duration;

}
