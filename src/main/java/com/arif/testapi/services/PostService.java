package com.arif.testapi.services;

import com.arif.testapi.entities.Post;
import com.arif.testapi.payloads.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    PostDto createPost(PostDto postDto, int userId, int categoryId);

    PostDto updatePost(PostDto postDto, int postId);

    void deletePost(int postId);


    List<PostDto> getAllPost(int pageNumber, int pageSize);

    List<PostDto> getPostByPostId(int postId);


    List<PostDto> getPostByCategory(int categoryId);

    List<PostDto> getPostByUser(int userId);

    List<PostDto> searchPost(String keyword);


}
