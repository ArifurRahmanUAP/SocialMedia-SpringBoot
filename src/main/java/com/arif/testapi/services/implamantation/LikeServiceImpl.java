package com.arif.testapi.services.implamantation;

import com.arif.testapi.entities.Like;
import com.arif.testapi.entities.Post;
import com.arif.testapi.entities.User;
import com.arif.testapi.exceptions.ResourceNotFoundException;
import com.arif.testapi.payloads.LikesDto;
import com.arif.testapi.repositories.LikeRepo;
import com.arif.testapi.repositories.UserPostRepo;
import com.arif.testapi.repositories.UserRepo;
import com.arif.testapi.services.LikeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LikeRepo likeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserPostRepo userPostRepo;


    @Override
    public LikesDto postLike(LikesDto likesDto, int userId, int postId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
        Post post = this.userPostRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

        Like like = this.modelMapper.map(likesDto, Like.class);

        like.setDate(new Date());
        like.setPost(post);
        like.setUser(user);
        Like save = this.likeRepo.save(like);

        return this.modelMapper.map(save, LikesDto.class);
    }

    @Override
    public LikesDto update(LikesDto likesDto, int userId, int postId) {
        return null;
    }

    @Override
    public void deleteLike(int likeId) {

        Like like = this.likeRepo.findById(likeId).orElseThrow(() -> new ResourceNotFoundException("Like", "Like Id", likeId));
        this.likeRepo.delete(like);
    }
}
