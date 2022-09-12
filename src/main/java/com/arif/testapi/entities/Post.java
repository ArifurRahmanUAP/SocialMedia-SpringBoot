package com.arif.testapi.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_post")
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    private String postTitle;

    @Column(length = 1000)
    private String postContent;

    private String postImage;

    private Date postDate;

    @ManyToOne
//    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;

}
