package dmitreev.petproject.java.oneDayOneWay.category.service;

import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryRequestDto;
import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryResponseDto;
import dmitreev.petproject.java.oneDayOneWay.category.mapper.CategoryMapper;
import dmitreev.petproject.java.oneDayOneWay.category.model.Category;
import dmitreev.petproject.java.oneDayOneWay.category.repository.CategoryRepository;
import dmitreev.petproject.java.oneDayOneWay.error.exception.ConflictException;
import dmitreev.petproject.java.oneDayOneWay.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

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

    @Override
    public CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryDto) {
        Category categoryToUpdate = getCategory(categoryId);
        Category category = categoryMapper.toCategory(categoryDto);

        categoryToUpdate.setName(category.getName());

        try {
            categoryRepository.save(categoryToUpdate);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Category is already exists.");
        }
        log.info("Updated category with id {}.", categoryId);
        return categoryMapper.toCategoryDto(categoryToUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDto getCategoryById(Long categoryId) {
        log.info("Received a category with id {}.", categoryId);
        return categoryMapper.toCategoryDto(getCategory(categoryId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getAllCategories(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        log.info("Received a list of all categories with size of {}.", size);
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        log.info("Category with id {} is deleted.", categoryId);
    }

    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new NotFoundException(String.format("Category with id=%d not found", categoryId)));
    }
}
