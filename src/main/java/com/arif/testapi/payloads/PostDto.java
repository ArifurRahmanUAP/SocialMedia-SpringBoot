package com.arif.testapi.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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

}
