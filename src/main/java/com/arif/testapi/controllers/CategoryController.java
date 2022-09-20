package com.arif.testapi.controllers;

import com.arif.testapi.payloads.ApiResponse;
import com.arif.testapi.payloads.CategoryDto;
import com.arif.testapi.payloads.CategoryResponse;
import com.arif.testapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        CategoryDto category = this.categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable int categoryId) {

        CategoryDto category = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {

        this.categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(new ApiResponse("Category deleted Successfully", true), HttpStatus.OK);

    }

    @GetMapping("/{categoryId}")
    ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {

        CategoryDto categoryById = this.categoryService.getCategoryById(categoryId);

        return new ResponseEntity<>(categoryById, HttpStatus.OK);
    }

    @GetMapping("/")
    ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                      @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
                                                      @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                      @RequestParam(value = "sortDir", defaultValue = "Asc", required = false) String sortDir) {
        CategoryResponse categories = this.categoryService.getCategories(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(categories);
    }
}
