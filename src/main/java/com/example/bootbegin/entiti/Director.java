package com.example.bootbegin.entiti;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotBlank
    String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate birthDay;

    @OneToMany
    @JoinColumn(name = "director_id")
    @JsonIgnore
    List<Movie> movies;

}
