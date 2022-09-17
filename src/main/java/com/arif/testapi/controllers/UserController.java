package com.arif.testapi.controllers;

import com.arif.testapi.payloads.ApiResponse;
import com.arif.testapi.payloads.UserDTO;
import com.arif.testapi.services.FileService;
import com.arif.testapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;


    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO, @RequestParam("image") MultipartFile image) throws IOException {


        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));

        userDTO.setUserImage(fileName);

        UserDTO createUserDto = this.userService.createUser(userDTO);

        String uploadDir = path + createUserDto.getId();
        Path paths = Paths.get(uploadDir);
        if (!Files.exists(paths)){
            Files.createDirectories(paths);
        }

        try {
            InputStream inputStream = image.getInputStream();
            Path filepath = paths.resolve(fileName);
            Files.copy(inputStream, filepath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e){
            e.printStackTrace();
        }



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
