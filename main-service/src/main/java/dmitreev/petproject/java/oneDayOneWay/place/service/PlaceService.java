package dmitreev.petproject.java.oneDayOneWay.place.service;

import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceRequestDto;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PlaceService {

    PlaceResponseDto createPlace(Long userId, PlaceRequestDto placeRequestDto);

    PlaceResponseDto updatePlaceByCreator(Long userId, Long placeId, PlaceRequestDto placeRequestDto);

    PlaceResponseDto savePhotoToPlace(Long placeId, MultipartFile file) throws IOException;

    void userDeletePlace(Long userId, Long placeId);

    PlaceResponseDto getPlaceById(Long placeId);
    PlaceResponseDto saveCommentToPlace(Long placeId, Long commentId);
}
