package com.example.bootbegin.entiti;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id")
    int id;
    @NotBlank
    String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate birthDay;

    @OneToMany(mappedBy = "director")
    @JsonIgnore
    Set<Movie> movies = new HashSet<Movie>();

}
