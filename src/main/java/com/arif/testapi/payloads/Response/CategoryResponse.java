package com.arif.testapi.payloads.Response;

import com.arif.testapi.payloads.CategoryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CategoryResponse {

    private int pageNumber;

    private int pageSize;

    private boolean isLastPage;

    private long totalElements;

    private int totalPages;

    private List<CategoryDto> Category;
}
