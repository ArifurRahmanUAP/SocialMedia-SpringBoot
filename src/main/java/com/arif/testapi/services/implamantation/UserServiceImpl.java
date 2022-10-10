package com.arif.testapi.services.implamantation;

import com.arif.testapi.entities.User;
import com.arif.testapi.exceptions.ResourceNotFoundException;
import com.arif.testapi.payloads.PostDto;
import com.arif.testapi.payloads.Response.AllUserResponse;
import com.arif.testapi.payloads.Response.CategoryResponse;
import com.arif.testapi.payloads.Response.UserResponse;
import com.arif.testapi.payloads.UserDTO;
import com.arif.testapi.repositories.UserPostRepo;
import com.arif.testapi.repositories.UserRepo;
import com.arif.testapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User byEmail = this.userRepo.findByEmail(userDTO.getEmail());



        if (isNull(byEmail)) {
            User user = this.modelMapper.map(userDTO, User.class);

            User savedUser = this.userRepo.save(user);

            return this.modelMapper.map(savedUser, UserDTO.class);

        } else return null;

    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserImage(userDto.getUserImage());
        user.setAbout(userDto.getAbout());
        User updateUser = this.userRepo.save(user);

        return this.modelMapper.map(updateUser, UserDTO.class);
    }

    @Override
    public void deleteUsers(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        this.userRepo.delete(user);
    }

    @Override
    public UserDTO getUserById(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public AllUserResponse getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> allUsers = this.userRepo.findAll(p);

        List<UserResponse> userResponses = allUsers.stream().map((user -> this.modelMapper.map(user, UserResponse.class))).collect(Collectors.toList());

        AllUserResponse allUserResponses = new AllUserResponse();

        allUserResponses.setTotalPages(allUsers.getTotalPages());
        allUserResponses.setPageNumber(allUsers.getNumber());
        allUserResponses.setPageSize(allUsers.getSize());
        allUserResponses.setLastPage(allUsers.isLast());
        allUserResponses.setTotalElements(allUsers.getTotalElements());

        allUserResponses.setUsers(userResponses);

        return allUserResponses;
    }


}
