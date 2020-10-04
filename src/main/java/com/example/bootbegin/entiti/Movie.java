package com.example.bootbegin.entiti;

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
@JsonIgnoreProperties(value = "")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 270)
    @NotBlank
    private String title;
    @Positive
    private int duration;

}
