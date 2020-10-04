package com.example.bootbegin.repository;

import com.example.bootbegin.entiti.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserById(int id);
    User getUserByName(String name);
    boolean existsByNickName(String nickName);
    void removeUserByName(String name);
    void removeUserByNickName(String nickName);
}
