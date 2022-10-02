package com.arif.testapi.payloads;

import com.arif.testapi.payloads.Response.PostResponse;
import com.arif.testapi.payloads.Response.UserResponseForComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
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
    private UserResponseForComment user;

    @JoinColumn(name = "like_id")
    private PostResponse posts;

}
