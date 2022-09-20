package com.arif.testapi.controllers;

import com.arif.testapi.payloads.Response.ApiResponse;
import com.arif.testapi.payloads.CommentDto;
import com.arif.testapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int userId, @PathVariable int postId) {
        CommentDto comment = this.commentService.createComment(commentDto, userId, postId);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable int userId, @PathVariable int commentId) {
        CommentDto comment = this.commentService.updateComment(commentDto, userId, commentId);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment( @PathVariable int commentId) {
        this.commentService.deleteComment(commentId);

        return new ResponseEntity<>(new ApiResponse("Comment Deleted Successfully", true), HttpStatus.OK);
    }

}
