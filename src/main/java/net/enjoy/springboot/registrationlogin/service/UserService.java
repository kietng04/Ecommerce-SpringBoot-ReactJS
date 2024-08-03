package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.UserDto;
import net.enjoy.springboot.registrationlogin.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    //Hàm này sẽ trả về một đối tượng User dựa trên id được truyền vào.
    User getUserById(Long id);

    User findUserByEmail(String email);

    List<UserDto> getAllUsers();
}