package dmitreev.petproject.java.oneDayOneWay.category.service;

import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryRequestDto;
import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryResponseDto;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

//    CategoryDto updateCategory(Long categoryId, CategoryCreateDto categoryDto);
//
//    CategoryDto getCategoryById(Long categoryId);
//
//    List<CategoryDto> getAllCategories(Integer from, Integer size);
//
//    void deleteCategory(Long categoryId);
}
