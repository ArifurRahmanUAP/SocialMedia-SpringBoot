package com.arif.testapi.services.implamantation;

import com.arif.testapi.entities.Category;
import com.arif.testapi.exceptions.ResourceNotFoundException;
import com.arif.testapi.payloads.CategoryDto;
import com.arif.testapi.payloads.Response.CategoryResponse;
import com.arif.testapi.repositories.CategoryRepo;
import com.arif.testapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public CategoryResponse getCategories(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Category> category = this.categoryRepo.findAll(p);

        CategoryResponse categoryResponse = new CategoryResponse();
        List<CategoryDto> categories = category.stream().map(cat -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());

        categoryResponse.setCategory(categories);
        categoryResponse.setPageNumber(category.getNumber());
        categoryResponse.setPageSize(category.getSize());
        categoryResponse.setTotalPages(category.getTotalPages());
        categoryResponse.setTotalElements(category.getTotalElements());
        categoryResponse.setLastPage(category.isLast());
        return categoryResponse;
    }


    public Category dtoToCategory(CategoryDto categoryDto) {

        return this.modelMapper.map(categoryDto, Category.class);
    }

    public CategoryDto categoryToDto(Category category) {

        return this.modelMapper.map(category, CategoryDto.class);
    }
}
