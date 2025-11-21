package org.example.userservice.app.service;

import org.example.userservice.app.Dto.UserRequestDto;
import org.example.userservice.app.Dto.UserResponseDto;
import org.example.userservice.app.Model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public User getUserById(String id);
    public User addUser(User user);
    public User updateUser(String id, UserRequestDto userRequestDto);
    public String deleteUser(String id);
    public UserResponseDto mapUserToUserResponseDto(User user);

    User mapUserRequestToUser(UserRequestDto userRequestDto);
}
