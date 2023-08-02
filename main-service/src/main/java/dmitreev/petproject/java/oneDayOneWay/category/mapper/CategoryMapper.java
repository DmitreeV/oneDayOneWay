package dmitreev.petproject.java.oneDayOneWay.category.mapper;

import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryRequestDto;
import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryResponseDto;
import dmitreev.petproject.java.oneDayOneWay.category.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryRequestDto categoryCreateDto);

    CategoryResponseDto toCategoryDto(Category category);
}
