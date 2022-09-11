package com.arif.testapi.services.implamantation;

import com.arif.testapi.entities.Category;
import com.arif.testapi.exceptions.ResourceNotFoundException;
import com.arif.testapi.payloads.CategoryDto;
import com.arif.testapi.repositories.CategoryRepo;
import com.arif.testapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }


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
    public List<CategoryDto> getCategories() {

        List<Category> all = this.categoryRepo.findAll();

        return all.stream().map(this::categoryToDto).collect(Collectors.toList());
    }


    public Category dtoToCategory(CategoryDto categoryDto) {

        return this.modelMapper.map(categoryDto, Category.class);
    }

    public CategoryDto categoryToDto(Category category) {

        return this.modelMapper.map(category, CategoryDto.class);
    }
}
