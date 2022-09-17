package com.arif.testapi.services;

import com.arif.testapi.payloads.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO user, Integer userId);

    void deleteUsers(Integer userId);

    UserDTO getUserById(Integer userId);

    List<UserDTO> getAllUsers();

}
