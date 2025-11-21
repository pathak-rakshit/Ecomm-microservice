package org.example.userservice.app.controller;

import lombok.RequiredArgsConstructor;
import org.example.userservice.app.Dto.UserRequestDto;
import org.example.userservice.app.Dto.UserResponseDto;
import org.example.userservice.app.Model.User;
import org.example.userservice.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {

        List<User> users = userService.getAllUsers();
        List<UserResponseDto>  userResponseDtoList = new ArrayList<>();
        for(User user : users) {
            UserResponseDto userDto = userService.mapUserToUserResponseDto(user);
            userResponseDtoList.add(userDto);
        }
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        UserResponseDto userResponseDto = userService.mapUserToUserResponseDto(user);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto userRequestDto) {

           User user = userService.mapUserRequestToUser(userRequestDto);
           user = userService.addUser(user);
           return new ResponseEntity<>("User with name : " +user.getFirstName()+ " added", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserRequestDto userRequestDto) {

        User user = userService.updateUser(id, userRequestDto);
        return new ResponseEntity<>("User with id : " + id + " updated", HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
            String response = userService.deleteUser(id);
            return ResponseEntity.ok(response);
    }
}
