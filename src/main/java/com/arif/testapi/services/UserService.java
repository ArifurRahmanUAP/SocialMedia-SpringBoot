package com.arif.testapi.services;

import com.arif.testapi.payloads.Response.AllUserResponse;
import com.arif.testapi.payloads.Response.UserResponse;
import com.arif.testapi.payloads.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO user, Integer userId);

    void deleteUsers(Integer userId);

    UserDTO getUserById(Integer userId);

    AllUserResponse getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

}
