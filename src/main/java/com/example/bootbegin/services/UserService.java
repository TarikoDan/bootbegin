package com.example.bootbegin.services;

import com.example.bootbegin.dto.request.UserRequest;
import com.example.bootbegin.dto.response.UserResponse;
import com.example.bootbegin.entiti.User;
import com.example.bootbegin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepo;

    public UserResponse save(UserRequest user) {

        User userDB = userRequestToUser(user);
        userRepo.saveAndFlush(userDB);      /*saving new User to DB*/

        return userToUserResp(userDB);
    }
    public List<UserResponse> getAll () {
        List<UserResponse> users = new ArrayList<>();
        userRepo.findAll()
                .forEach(user -> users.add(
                        userToUserResp(user)
                ));
        return users;
    }
    public UserResponse getById(int id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new NullPointerException("no such User with id" + id));
        return userToUserResp(user);
    }

    public UserResponse edit(int id, UserRequest user) {
        User foundedUser = userRepo.getOne(id);
//                .orElseThrow(() -> new NullPointerException("no such User with id" + id));
        User newUser = userRequestToUser(user);
        foundedUser.changeValues(newUser);
        userRepo.flush();
        return userToUserResp(foundedUser);
    };

    private String formatDateToDay(Date date) {
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        return dayFormat.format(date);
    }
    private UserResponse userToUserResp(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surName(user.getSurName())
                .nickName(user.getNickName())
                .dayOfBirth(formatDateToDay(user.getBirthday()))
                .build();
    }

    private User userRequestToUser(UserRequest user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date birthDate = null;  /*creating birth Date*/
        try {
            birthDate = dateFormat.parse(user.getBirthDay());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String nickName = user.getName()  /*creating nickName*/
                .strip()
                .toLowerCase()
                .substring(0, 3)
                .concat(
                        user.getSurName()
                                .strip()
                                .toUpperCase()
                                .substring(0, 3)
                ).concat(
                        user.getBirthDay().substring(0,4)
                );
        User userDb = User.builder()    /*creating new User*/
                .name(user.getName())
                .surName(user.getSurName())
                .nickName(nickName)
                .birthday(birthDate)
                .build();

        return userDb;

    }

}
