package org.example.userservice.app.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.userservice.app.Dto.UserRequestDto;
import org.example.userservice.app.Dto.UserResponseDto;
import org.example.userservice.app.Model.User;
import org.example.userservice.app.Repository.UserRepository;
import org.example.userservice.app.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id);
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public User addUser(User user) {
         return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, UserRequestDto userRequestDto) {
       User updatedUser = mapUserRequestToUser(userRequestDto);
       updatedUser.setId(id);
       return userRepository.save(updatedUser);
    }

    @Override
    public String deleteUser(String id) {
        if(userRepository.findById(id)!=null) {
            userRepository.deleteById(id);
            return "User deleted";
        }
        return "User Not Found";
    }

    @Override
    public UserResponseDto mapUserToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setEmail(user.getEmail());
        if(user.getAddress()!=null)
            userResponseDto.setAddress(user.getAddress());

        return userResponseDto;

    }

    @Override
    public User mapUserRequestToUser(UserRequestDto userRequestDto) {
         User user = new User();
         user.setFirstName(userRequestDto.getFirstName());
         user.setLastName(userRequestDto.getLastName());
         user.setEmail(userRequestDto.getEmail());
         if(userRequestDto.getAddress()!=null)
             user.setAddress(userRequestDto.getAddress());
         return user;
    }
}
