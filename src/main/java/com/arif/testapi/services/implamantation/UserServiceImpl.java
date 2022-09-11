package com.arif.testapi.services.implamantation;

import com.arif.testapi.entities.User;
import com.arif.testapi.exceptions.ResourceNotFoundException;
import com.arif.testapi.payloads.UserDTO;
import com.arif.testapi.repositories.UserRepo;
import com.arif.testapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = this.dtoToUser(userDTO);

        User savedUser = this.userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updateUser = this.userRepo.save(user);

        return this.userToDto(updateUser);
    }

    @Override
    public void deleteUsers(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        this.userRepo.delete(user);
    }

    @Override
    public UserDTO getUserById(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> allUsers = this.userRepo.findAll();
        List<UserDTO> userDTOS = allUsers.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDTOS;
    }


    private User dtoToUser(UserDTO userDTO) {
        return this.modelMapper.map(userDTO, User.class);

    }

    public UserDTO userToDto(User user) {

        return this.modelMapper.map(user, UserDTO.class);

    }

}
