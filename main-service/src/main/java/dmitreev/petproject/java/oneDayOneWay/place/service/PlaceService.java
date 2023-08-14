package dmitreev.petproject.java.oneDayOneWay.place.service;

import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceRequestDto;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceResponseDto;

public interface PlaceService {

    PlaceResponseDto createPlace(Long userId, PlaceRequestDto placeRequestDto);
   // PlaceResponseDto savePhotoToPlace(Long placeId, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException;
}
