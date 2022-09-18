package com.arif.testapi.services;

import com.arif.testapi.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, int userId, int postId);

    CommentDto updateComment(CommentDto commentDto, int userId, int commentId);

    void deleteComment(int commentId);
}
