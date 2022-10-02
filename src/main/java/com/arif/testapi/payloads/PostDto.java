package com.arif.testapi.payloads;


import com.arif.testapi.entities.Comment;
import com.arif.testapi.payloads.Response.UserResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private int postId;

    @NotEmpty
    private String postTitle;

    @NotEmpty
    private String postContent;

    private String postImage;

    private Date postDate;

    @NotEmpty
    private CategoryDto category;

    @NotEmpty
    private UserResponse user;

    private List<CommentDto> comments = new ArrayList<>();

    private List<LikesDto> likes = new ArrayList<>();

}
