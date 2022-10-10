package com.arif.testapi.controllers;

import com.arif.testapi.Config.Constants;
import com.arif.testapi.payloads.Response.AllUserResponse;
import com.arif.testapi.payloads.Response.PostResponse;
import com.arif.testapi.payloads.Response.UserResponse;
import com.arif.testapi.payloads.Response.ApiResponse;
import com.arif.testapi.payloads.UserDTO;
import com.arif.testapi.services.FileService;
import com.arif.testapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

import static java.util.Objects.isNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;


    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public ResponseEntity createUser(@RequestPart("body") UserDTO userDTO,
                                              @RequestPart(value = "image", required = false) MultipartFile images) {

//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));

//        UserDTO userDTO = new UserDTO();

        String fileName = null;
        try {
            fileName = this.fileService.uploadImage(path, images);
        } catch (IOException e) {
            e.printStackTrace();
        }


        userDTO.setUserImage("http://localhost:3309/api/users/images/" + fileName);

        UserDTO createUserDto = this.userService.createUser(userDTO);

//        String uploadDir = path + createUserDto.getId();
//        Path paths = Paths.get(uploadDir);
//        if (!Files.exists(paths)) {
//            Files.createDirectories(paths);
//        }
//
//        try {
//            InputStream inputStream = image.getInputStream();
//            Path filepath = paths.resolve(fileName);
//            Files.copy(inputStream, filepath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if (isNull(createUserDto)) {
            return new ResponseEntity<>(new ApiResponse("Already Exist", false), HttpStatus.CONFLICT);

        } else
            return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {

        InputStream inputStream = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream, response.getOutputStream());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<UserDTO> updateUser(@RequestPart(value = "body") @Valid UserDTO userDTO, @PathVariable int id, @RequestPart(value = "image") MultipartFile images) {


        String fileName = null;
        try {
            fileName = this.fileService.uploadImage(path, images);
        } catch (IOException e) {
            e.printStackTrace();
        }


        userDTO.setUserImage("http://localhost:3309/api/users/images/" + fileName);

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
    public ResponseEntity<AllUserResponse> getAllUsers(@RequestParam(value = "pageNumber", defaultValue = Constants.PAGE_NUMBER, required = false) int pageNumber,
                                                       @RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE, required = false) int pageSize,
                                                       @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                       @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir) {

        AllUserResponse allUsers = this.userService.getAllUsers(pageNumber, pageSize, sortBy, sortDir);

        return ResponseEntity.ok(allUsers);

    }


}
