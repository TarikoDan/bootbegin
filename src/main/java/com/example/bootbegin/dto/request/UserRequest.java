package com.example.bootbegin.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String role;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthDay;

}
