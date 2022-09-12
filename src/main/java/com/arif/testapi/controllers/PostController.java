package com.arif.testapi.controllers;

import com.arif.testapi.payloads.ApiResponse;
import com.arif.testapi.payloads.PostDto;
import com.arif.testapi.repositories.UserPostRepo;
import com.arif.testapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserPostRepo userPostRepo;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId) {

        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);

    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable int postId) {

        PostDto post = this.postService.updatePost(postDto, postId);
        PostDto updatePost = this.postService.updatePost(post, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) {

        this.postService.deletePost(postId);

        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
    }


}
