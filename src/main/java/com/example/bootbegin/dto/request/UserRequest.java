package com.example.bootbegin.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    @NonNull
    private String name;
    @NonNull
    private String surName;
    @NonNull
    private String birthDay;

}
