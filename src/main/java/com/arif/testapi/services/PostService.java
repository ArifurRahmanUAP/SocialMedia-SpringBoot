package com.arif.testapi.services;

import com.arif.testapi.payloads.PostDto;
import com.arif.testapi.payloads.Response.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, int userId, int categoryId);

    PostDto updatePost(PostDto postDto, int postId, int userId);

    void deletePost(int postId);


    PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);

    List<PostDto> getPostByPostId(int postId);


    List<PostDto> getPostByCategory(int categoryId);

    PostResponse getPostByUser(int userId, int pageNumber, int pageSize, String sortBy, String sortDir);

    List<PostDto> searchPost(String keyword);


}
