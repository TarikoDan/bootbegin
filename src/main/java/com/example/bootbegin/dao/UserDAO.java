package com.example.bootbegin.dao;

import com.example.bootbegin.entiti.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
}
