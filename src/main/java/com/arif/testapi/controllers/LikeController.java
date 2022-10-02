package com.arif.testapi.controllers;

import com.arif.testapi.payloads.LikesDto;
import com.arif.testapi.payloads.Response.ApiResponse;
import com.arif.testapi.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<LikesDto> postLike(@RequestBody LikesDto likesDto,
                                             @PathVariable int userId,
                                             @PathVariable int postId){

        LikesDto likesDto1 = this.likeService.postLike(likesDto, userId, postId);

        return new ResponseEntity<>(likesDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deleteLike( @PathVariable int postId){

        this.likeService.deleteLike(postId);

        return new ResponseEntity<>(new ApiResponse("Successfull", true), HttpStatus.OK);
    }




}
