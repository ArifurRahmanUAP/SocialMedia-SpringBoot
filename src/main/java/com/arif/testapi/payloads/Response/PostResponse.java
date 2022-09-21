package com.arif.testapi.payloads.Response;

import com.arif.testapi.entities.Comment;
import com.arif.testapi.payloads.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    private int pageNumber;

    private int pageSize;

    private boolean isLastPage;

    private long totalElements;

    private int totalPages;

    private List<PostDto> Posts;


}
