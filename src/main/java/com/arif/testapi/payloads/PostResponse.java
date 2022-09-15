package com.arif.testapi.payloads;

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

    private List<PostDto> content;


}
