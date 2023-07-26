package dmitreev.petproject.java.oneDayOneWay.place.service;

import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceRequestDto;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceResponseDto;

public interface PlaceService {

    PlaceResponseDto createPlace(Long userId, PlaceRequestDto placeRequestDto);
}