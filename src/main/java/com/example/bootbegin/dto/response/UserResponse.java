package com.example.bootbegin.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String surName;
    @NonNull
    private String nickName;
    private String dayOfBirth;
}
