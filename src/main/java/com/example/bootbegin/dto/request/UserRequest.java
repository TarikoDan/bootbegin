package com.example.bootbegin.dto.request;

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
//    @Pattern(regexp = "((?:19|20)\\\\d\\\\d)/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])")
    private String birthDay;

}
