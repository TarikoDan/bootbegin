package com.example.bootbegin.services;

import com.example.bootbegin.dto.request.UserRequest;
import com.example.bootbegin.dto.response.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse save(UserRequest user);

    List<UserResponse> getAll ();

    UserResponse getByEmail(String email);

    UserResponse edit(String email, UserRequest user);

    void remove(String email);

}
