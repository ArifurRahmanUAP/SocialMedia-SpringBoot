package com.arif.testapi.services.implamantation;

import com.arif.testapi.entities.Category;
import com.arif.testapi.entities.User;
import com.arif.testapi.entities.Post;
import com.arif.testapi.exceptions.ResourceNotFoundException;
import com.arif.testapi.payloads.CategoryDto;
import com.arif.testapi.payloads.PostDto;
import com.arif.testapi.repositories.CategoryRepo;
import com.arif.testapi.repositories.UserPostRepo;
import com.arif.testapi.repositories.UserRepo;
import com.arif.testapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PostServiceImpl implements PostService {

    @Autowired
    private UserPostRepo userPostRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, int userId, int categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setPostImage("default.png");
        post.setPostDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post save = this.userPostRepo.save(post);

        return this.modelMapper.map(save, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Post post = this.userPostRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        Post updatedPost = this.userPostRepo.save(post);

        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(int postId) {

        Post post = this.userPostRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        this.userPostRepo.delete(post);

    }

    @Override
    public List<PostDto> getAllPost(int pageNumber, int pageSize)  {

        PageRequest p = PageRequest.of(pageNumber, pageSize);

        List<PostDto> allPost = this.userPostRepo.findAll(p).stream().map((post -> this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());

        return allPost;
    }

    @Override
    public List<PostDto> getPostByPostId(int postId) {

        List<PostDto> collect = this.userPostRepo.findById(postId).stream().map((post -> this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<PostDto> getPostByUser(int userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));

        List<PostDto> collect = this.userPostRepo.findByUser(user).stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<PostDto> getPostByCategory(int categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        List<PostDto> collect = this.userPostRepo.findByCategory(category).stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return collect;
    }


    @Override
    public List<PostDto> searchPost(String keyword) {


        return null;
    }
}
