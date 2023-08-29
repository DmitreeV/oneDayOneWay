package dmitreev.petproject.java.oneDayOneWay.place.mapper;

import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceRequestDto;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceResponseDto;
import dmitreev.petproject.java.oneDayOneWay.place.model.Place;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;

import static dmitreev.petproject.java.oneDayOneWay.category.mapper.CategoryMapper.toCategoryShortDto;
import static dmitreev.petproject.java.oneDayOneWay.user.mapper.UserMapper.toUserShortDto;

@Mapper(componentModel = "spring")
public interface PlaceMapper {

    @Mapping(target = "category.id", source = "category")
    Place toPlace(PlaceRequestDto placeRequestDto);

    public static PlaceResponseDto toPlaceDto(Place place) {
        return PlaceResponseDto.builder()
                .id(place.getId())
                .title(place.getTitle())
                .description(place.getDescription())
                .creator(toUserShortDto(place.getCreator()))
                .category(toCategoryShortDto(place.getCategory()))
                .filename(place.getFilename())
                .lat(place.getLat())
                .lon(place.getLon())
                .commentList(new ArrayList<>())
                .build();
    }
}

