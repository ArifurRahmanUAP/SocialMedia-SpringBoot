package com.arif.testapi.controllers;

import com.arif.testapi.payloads.ApiResponse;
import com.arif.testapi.payloads.UserDTO;
import com.arif.testapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {

        UserDTO createUserDto = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDTO, @PathVariable int id) {

        UserDTO updateDto = this.userService.updateUser(userDTO, id);
        return ResponseEntity.ok(updateDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {

        this.userService.deleteUsers(id);
        return new ResponseEntity<>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);

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
