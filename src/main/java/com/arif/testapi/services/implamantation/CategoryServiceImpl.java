package com.arif.testapi.services.implamantation;

import com.arif.testapi.entities.Category;
import com.arif.testapi.exceptions.ResourceNotFoundException;
import com.arif.testapi.payloads.CategoryDto;
import com.arif.testapi.payloads.CategoryResponse;
import com.arif.testapi.payloads.PostDto;
import com.arif.testapi.repositories.CategoryRepo;
import com.arif.testapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = this.dtoToCategory(categoryDto);
        Category save = this.categoryRepo.save(category);
        return categoryToDto(save);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category save = this.categoryRepo.save(category);
        return categoryToDto(save);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(int categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        return this.categoryToDto(category);
    }

    @Override
    public CategoryResponse getCategories(int pageNumber, int pageSize) {

        Pageable p = PageRequest.of(pageNumber, pageSize);
        CategoryResponse categoryResponse = new CategoryResponse();
        List<CategoryDto> Categories = this.categoryRepo.findAll(p).stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

        categoryResponse.setCategory(Categories);
        categoryResponse.setPageNumber(p.getPageNumber());
        categoryResponse.setPageSize(p.getPageSize());
//        categoryResponse.setTotalPages(p.);
        return categoryResponse;
    }


    public Category dtoToCategory(CategoryDto categoryDto) {

        return this.modelMapper.map(categoryDto, Category.class);
    }

    public CategoryDto categoryToDto(Category category) {

        return this.modelMapper.map(category, CategoryDto.class);
    }
}
