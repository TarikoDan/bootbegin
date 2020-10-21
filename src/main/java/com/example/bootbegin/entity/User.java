package com.example.bootbegin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotBlank
    String name;
    @NotBlank
    String surName;
    @NotBlank
    String password;
    @Column(nullable = false, unique = true)
    String nickName;
    String role;
    LocalDate birthday;

    public void changeValues(User newUser) {
        this.name = newUser.getName();
        this.surName = newUser.getSurName();
        this.nickName = newUser.getNickName();
        this.birthday = newUser.getBirthday();
    }
}
