package com.arif.testapi.controllers;


import com.arif.testapi.entities.User;
import com.arif.testapi.payloads.UserDTO;
import com.arif.testapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        System.out.println(userDTO.toString());

        UserDTO createUserDto = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")

    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable int id) {

        UserDTO updateDto = this.userService.updateUser(userDTO, id);
        return new ResponseEntity<>(updateDto, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable int id) {

        this.userService.deleteUsers(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @GetMapping("/userById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {

        UserDTO userById = this.userService.getUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);

    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {


        return ResponseEntity.ok(this.userService.getAllUsers());

    }


}
