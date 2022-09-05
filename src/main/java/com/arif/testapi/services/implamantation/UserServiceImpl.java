package com.arif.testapi.services.implamantation;

import com.arif.testapi.entities.User;
import com.arif.testapi.exceptions.ResourceNceNotFoundException;
import com.arif.testapi.payloads.UserDTO;
import com.arif.testapi.repositories.UserRepo;
import com.arif.testapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = this.dtoToUser(userDTO);

        User savedUser = this.userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNceNotFoundException("User", " id ", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updateUser = this.userRepo.save(user);

        return this.userToDto(updateUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNceNotFoundException("user", "id", userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> allUsers = this.userRepo.findAll();
        List<UserDTO> userDTOS = allUsers.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public void deleteUsers(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNceNotFoundException("User", "Id", userId));

        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        return user;

    }

    public UserDTO userToDto(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAbout(user.getAbout());
        return userDTO;

    }
}
