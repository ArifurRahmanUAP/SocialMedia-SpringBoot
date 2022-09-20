package com.arif.testapi.controllers;

import com.arif.testapi.Config.Constants;
import com.arif.testapi.payloads.Response.ApiResponse;
import com.arif.testapi.payloads.PostDto;
import com.arif.testapi.payloads.Response.PostResponse;
import com.arif.testapi.repositories.UserPostRepo;
import com.arif.testapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Repository
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserPostRepo userPostRepo;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId) {

        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);

    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable int postId) {

        PostDto post = this.postService.updatePost(postDto, postId);
        PostDto updatePost = this.postService.updatePost(post, postId);
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
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId) {
        List<PostDto> postById = this.postService.getPostByUser(userId);

        return new ResponseEntity<>(postById, HttpStatus.OK);
    }

    @GetMapping("post/categoryId/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId) {
        List<PostDto> category = this.postService.getPostByCategory(categoryId);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @GetMapping("post/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keywords){
        List<PostDto> searchData = this.postService.searchPost(keywords);

        return  new ResponseEntity<>(searchData, HttpStatus.OK);
    }

}
