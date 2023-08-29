package dmitreev.petproject.java.oneDayOneWay.category.mapper;

import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryRequestDto;
import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryResponseDto;
import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryShortDto;
import dmitreev.petproject.java.oneDayOneWay.category.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto toCategoryDto(Category category);

    public static CategoryShortDto toCategoryShortDto(Category category){
        return CategoryShortDto.builder()
                .name(category.getName())
                .build();
    }
}
