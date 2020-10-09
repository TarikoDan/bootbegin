package com.example.bootbegin.services.imp;

import com.example.bootbegin.dto.request.UserRequest;
import com.example.bootbegin.dto.response.UserResponse;
import com.example.bootbegin.entiti.User;
import com.example.bootbegin.repository.UserRepository;
import com.example.bootbegin.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    @Autowired
    private final UserRepository userRepo;

    @Override
    public UserResponse save(UserRequest user) {

        User userDB = userRequestToUser(user);
        userRepo.saveAndFlush(userDB);      /*saving new User to DB*/

        return userToUserResp(userDB);
    }

    @Override
    public List<UserResponse> getAll () {
        List<UserResponse> users = new ArrayList<>();
        userRepo.findAll()
                .forEach(user -> users.add(
                        userToUserResp(user)
                ));
        return users;
    }

    @Override
    public UserResponse getById(int id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new NullPointerException("no such User with Id: " + id));
        return userToUserResp(user);
    }

    @Override
    public UserResponse edit(int id, UserRequest user) {
        if (userRepo.existsById(id)) {
            User foundedUser = userRepo.getOne(id);
            User newUser = userRequestToUser(user);
            foundedUser.changeValues(newUser);
            userRepo.flush();
            return userToUserResp(foundedUser);
        }else {
            throw new NullPointerException("no such User with Id: " + id);
        }
    }

    @Override
    public void deleteById(int id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        }else {
            throw new NullPointerException("no such User with Id: " + id);
        }
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }

    @Override
    public void remove(String nickName) {
        if (userRepo.existsByNickName(nickName)) {
            userRepo.removeUserByNickName(nickName);
        }else {
            throw new NullPointerException("no such User with NickName: " + nickName);
        }
    }

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

    private Date birthDate(UserRequest user) {
//        String regEX = "((?:19|20)\\\\d\\\\d)/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])";
        String regEX = "(?:(?:19|2[01])\\d\\d(?:1[02]|0[13578])(?:[0-2]\\d|3[01]))";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date birthDate = null;  /*creating birth Date*/
        String birthDay = user.getBirthDay();
        if ((birthDay.length() == 10) /*&& birthDay.matches(regEX)*/) {
            try {
                birthDate = dateFormat.parse(birthDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            throw new ExpressionException("Bad Date Format -> must be: 'yyyy/MM/dd' .");
        }
        return birthDate;
    }

    private User userRequestToUser(UserRequest user) {
        String birthDay = user.getBirthDay();
        Date birthDate = birthDate(user);

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
                        birthDay.substring(0,4)
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
