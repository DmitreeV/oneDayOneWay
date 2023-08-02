package dmitreev.petproject.java.oneDayOneWay.category.controller;

import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryRequestDto;
import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryResponseDto;
import dmitreev.petproject.java.oneDayOneWay.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin/categories")
@Tag(name = "Operations with categories available to the administrator.")
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new category.")
    public CategoryResponseDto saveCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryService.createCategory(categoryRequestDto);
    }
}
