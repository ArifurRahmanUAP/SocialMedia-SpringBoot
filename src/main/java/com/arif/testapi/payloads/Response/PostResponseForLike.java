package com.arif.testapi.payloads.Response;

import com.arif.testapi.payloads.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class PostResponseForLike {

    private int id;

    private PostDto Posts;

}
