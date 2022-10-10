package com.arif.testapi.payloads;

import com.arif.testapi.payloads.Response.PostResponse;
import com.arif.testapi.payloads.Response.UserResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class LikesDto {

    private int id;

    private String like;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private UserResponse user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostDto postDto;


}
