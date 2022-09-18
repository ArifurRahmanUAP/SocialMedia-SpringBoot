package com.arif.testapi.payloads;


import com.arif.testapi.entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private int postId;

    private String postTitle;

    private String postContent;

    private String imageName;

    private Date postDate;

    private CategoryDto category;

    private UserDTO user;

    private List<CommentDto> comments = new ArrayList<>();

}
