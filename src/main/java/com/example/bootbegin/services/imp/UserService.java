package com.example.bootbegin.services.imp;

import com.example.bootbegin.dao.UserDAO;
import com.example.bootbegin.dto.request.UserRequest;
import com.example.bootbegin.dto.response.UserResponse;
import com.example.bootbegin.entity.User;
import com.example.bootbegin.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;  /* цей бін описаний в SecurityConfig*/

    @Override
    public UserResponse save(UserRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userDB = userRequestToUser(user);
        /*Також перевірити чи починається роль з "ROLE_" */
        if (!user.getRole().startsWith("ROLE_")) {
            throw new RuntimeException("User role should start with 'ROLE_'");
        }
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
        User user = userDAO.findUserByEmail(email)
                .orElseThrow(() -> new NullPointerException("no such User with email: " + email));
        return userToUserResp(user);
    }

    @Override
    public UserResponse edit(String email, UserRequest user) {
        if (userDAO.existsByEmail(email)) {
            User foundedUser = userDAO.getUserByEmail(email);
            User newUser = userRequestToUser(user);
            foundedUser.changeValues(newUser);
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
        String birthDay = user.getBirthDay();
        if (!birthDay.matches("[1-2][0-9]{3}-[0-1][0-9]-[0-9]{2}")) {
            throw new DateTimeException("Incorrect Date format");
        };
        LocalDate birthDate = LocalDate.parse(user.getBirthDay());

        return User.builder()    /*creating new User*/
                .name(user.getName())
                .surName(user.getSurName())
                .email(user.getEmail())
                .birthday(birthDate)
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findUserByEmail(username)
                .orElseThrow(() -> new NullPointerException("no such User with email: " + username));
    }
}
