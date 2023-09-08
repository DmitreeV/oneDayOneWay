package dmitreev.petproject.java.oneDayOneWay.category.service;

import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryRequestDto;
import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryDto);

    CategoryResponseDto getCategoryById(Long categoryId);

    List<CategoryResponseDto> getAllCategories(Integer from, Integer size);

    void deleteCategory(Long categoryId);
}
