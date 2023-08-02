package dmitreev.petproject.java.oneDayOneWay.category.service;

import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryRequestDto;
import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryResponseDto;
import dmitreev.petproject.java.oneDayOneWay.category.mapper.CategoryMapper;
import dmitreev.petproject.java.oneDayOneWay.category.model.Category;
import dmitreev.petproject.java.oneDayOneWay.category.repository.CategoryRepository;
import dmitreev.petproject.java.oneDayOneWay.error.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toCategory(categoryRequestDto);
        try {
            categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Category is already exists.");
        }
        log.info("Saved new category : {}.", category.getName());
        return categoryMapper.toCategoryDto(category);
    }
}
