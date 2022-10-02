package com.arif.testapi.controllers;

import com.arif.testapi.Config.Constants;
import com.arif.testapi.payloads.Response.ApiResponse;
import com.arif.testapi.payloads.PostDto;
import com.arif.testapi.payloads.Response.PostResponse;
import com.arif.testapi.repositories.UserPostRepo;
import com.arif.testapi.services.FileService;
import com.arif.testapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserPostRepo userPostRepo;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping(value = "/user/{userId}/category/{categoryId}/post", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PostDto> createPost(@RequestPart("body") PostDto postDto,
                                              @RequestPart(value = "image", required = false) MultipartFile images,
                                              @PathVariable("userId") int userId,
                                              @PathVariable("categoryId") int categoryId) {

        String fileName = null;
        try {
            fileName = this.fileService.uploadImage(path, images);
        } catch (IOException e) {
            e.printStackTrace();
        }
        postDto.setPostImage("http://localhost:3309/api/images/" + fileName);


        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<>(createPost, HttpStatus.CREATED);

    }

    @GetMapping(value = "images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {

        InputStream inputStream = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream, response.getOutputStream());
    }

    @PutMapping(value = "/post/{postId}/{userId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PostDto> updatePost(@RequestPart("body") PostDto postDto,
                                              @RequestPart("image") MultipartFile images,
                                              @PathVariable int postId,
                                              @PathVariable int userId) {

        String fileName = null;
        try {
            fileName = this.fileService.uploadImage(path, images);
        } catch (IOException e) {
            e.printStackTrace();
        }
        postDto.setPostImage("http://localhost:3309/api/images/" + fileName);

        PostDto updatePost = this.postService.updatePost(postDto, postId, userId);

        return new ResponseEntity<>(updatePost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) {

        this.postService.deletePost(postId);

        return new ResponseEntity<>(new ApiResponse(Constants.POST_DELETED, true), HttpStatus.OK);
    }

    @GetMapping("post/allPost")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = Constants.PAGE_NUMBER, required = false) int pageNumber,
                                                   @RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE, required = false) int pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir) {
        PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    @GetMapping("post/postId/{postId}")
    public ResponseEntity<List<PostDto>> getPostByPostId(@PathVariable int postId) {
        List<PostDto> postById = this.postService.getPostByPostId(postId);

        return new ResponseEntity<>(postById, HttpStatus.OK);
    }

    @GetMapping("post/userId/{userId}")
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable int userId,
                                                      @RequestParam(value = "pageNumber", defaultValue = Constants.PAGE_NUMBER, required = false) int pageNumber,
                                                      @RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE, required = false) int pageSize,
                                                      @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
                                                      @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir) {
        PostResponse postById = this.postService.getPostByUser(userId, pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(postById, HttpStatus.OK);
    }

    @GetMapping("post/categoryId/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId) {
        List<PostDto> category = this.postService.getPostByCategory(categoryId);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("post/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keywords) {
        List<PostDto> searchData = this.postService.searchPost(keywords);

        return new ResponseEntity<>(searchData, HttpStatus.OK);
    }

}
