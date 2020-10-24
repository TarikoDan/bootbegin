package com.example.bootbegin.dao;

import com.example.bootbegin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    void removeUserByEmail(String email);
    User getUserByEmail(String email);
    Optional<User> findUserByEmail(String email);
}
