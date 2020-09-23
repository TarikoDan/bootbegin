package com.example.bootbegin.repository;

import com.example.bootbegin.entiti.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(String nickName);
//    User getByNickName(String nickName);
//    String getBirthDay(int id);
}
