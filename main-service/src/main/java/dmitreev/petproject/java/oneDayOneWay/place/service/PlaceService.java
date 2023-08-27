package dmitreev.petproject.java.oneDayOneWay.place.service;

import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceRequestDto;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PlaceService {

    PlaceResponseDto createPlace(Long userId, PlaceRequestDto placeRequestDto);
    PlaceResponseDto savePhotoToPlace(Long placeId, MultipartFile file) throws IOException;
}
