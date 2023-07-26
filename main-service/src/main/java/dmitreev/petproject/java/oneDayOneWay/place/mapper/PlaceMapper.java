package dmitreev.petproject.java.oneDayOneWay.place.mapper;

import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceRequestDto;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceResponseDto;
import dmitreev.petproject.java.oneDayOneWay.place.model.Place;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceMapper {

    PlaceResponseDto toPlaceDto(Place place);

    Place toPlace(PlaceRequestDto placeRequestDto);
}

