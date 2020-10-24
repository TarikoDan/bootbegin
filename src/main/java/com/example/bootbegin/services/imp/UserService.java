package com.example.bootbegin.services.imp;

import com.example.bootbegin.dto.request.UserRequest;
import com.example.bootbegin.dto.response.UserResponse;
import com.example.bootbegin.entity.User;
import com.example.bootbegin.dao.UserDAO;
import com.example.bootbegin.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    @Autowired
    private final UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;  /* цей бін описаний в SecurityConfig*/

    @Override
    public UserResponse save(UserRequest user) {
        if (userDAO.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
            /* перевірити чи починається роль з "ROLE_" */
        if ((user.getRole() == null) || !user.getRole().startsWith("ROLE_")) {
            throw new RuntimeException("User role should start with 'ROLE_'");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userDB = userRequestToUser(user);
        userDAO.saveAndFlush(userDB);      /*saving new User to DB*/

        return userToUserResp(userDB);
    }

    @Override
    public List<UserResponse> getAll () {
        List<UserResponse> users = new ArrayList<>();
        userDAO.findAll()
                .forEach(user -> users.add(
                        userToUserResp(user)
                ));
        return users;
    }

    @Override
    public UserResponse getByEmail(String email) {
        return userToUserResp(
                userDAO.findUserByEmail(email).orElseThrow(() ->
                        new NullPointerException("no such User with email: " + email))
        );
    }

    @Override
    public UserResponse edit(String email, UserRequest user) {
        if (userDAO.existsByEmail(email)) {
            User foundedUser = userDAO.getUserByEmail(email);
            User newUser = userRequestToUser(user);
            foundedUser.changeValues(newUser);
            final String newPassword = user.getPassword();
            if (newPassword != null && newPassword.trim().length() != 0) {
                foundedUser.setPassword(passwordEncoder.encode(newPassword));
            }
            userDAO.flush();
            return userToUserResp(foundedUser);
        }else {
            throw new NullPointerException("no such User with email: " + email);
        }
    }

    @Override
    public void remove(String email) {
        if (userDAO.existsByEmail(email)) {
            userDAO.removeUserByEmail(email);
        }else {
            throw new NullPointerException("no such User with email: " + email);
        }
    }

    @Override
    public void deleteAll() {
        userDAO.deleteAll();
    }

    private UserResponse userToUserResp(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surName(user.getSurName())
                .email(user.getEmail())
                .dayOfBirth(user.getBirthday().getDayOfWeek())
                .build();
    }

    private User userRequestToUser(UserRequest user) {
        LocalDate birthDate = LocalDate.parse(user.getBirthDay());

        return User.builder()    /*creating new User*/
                .name(user.getName())
                .surName(user.getSurName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .birthday(birthDate)
                .build();
    }

}
