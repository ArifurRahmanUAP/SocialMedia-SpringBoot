package com.arif.testapi.services.implamantation;

import com.arif.testapi.entities.Comment;
import com.arif.testapi.entities.Post;
import com.arif.testapi.entities.User;
import com.arif.testapi.exceptions.ResourceNotFoundException;
import com.arif.testapi.payloads.CommentDto;
import com.arif.testapi.repositories.CommentRepo;
import com.arif.testapi.repositories.UserPostRepo;
import com.arif.testapi.repositories.UserRepo;
import com.arif.testapi.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserPostRepo userPostRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto, int userId, int postId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
        Post post = this.userPostRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setDate(new Date());
        comment.setUser(user);
        comment.setPost(post);

        Comment createdComment = this.commentRepo.save(comment);

        return this.modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, int userId, int commentId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));

        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment Id", commentId));

        comment.setContent(commentDto.getContent());
        Comment updatedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(int commentId) {

        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "comment Id", commentId));
        this.commentRepo.delete(comment);

    }
}
