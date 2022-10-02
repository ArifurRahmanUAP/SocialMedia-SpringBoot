package com.arif.testapi.services;

import com.arif.testapi.payloads.LikesDto;

public interface LikeService {

    LikesDto postLike(LikesDto likesDto, int userId, int postId);

    LikesDto update(LikesDto likesDto, int userId, int postId);

    void deleteLike(int likeId);
}
