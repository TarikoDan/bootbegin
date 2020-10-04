package com.example.bootbegin.services;

import com.example.bootbegin.dto.request.UserRequest;
import com.example.bootbegin.dto.response.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse save(UserRequest user);

    List<UserResponse> getAll ();

    UserResponse getById(int id);

    UserResponse edit(int id, UserRequest user);

    void deleteById (int id);

    void deleteAll ();

    void remove(String nickName);

}
