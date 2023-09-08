package dmitreev.petproject.java.oneDayOneWay.way.service;

import dmitreev.petproject.java.oneDayOneWay.way.dto.WayRequestDto;
import dmitreev.petproject.java.oneDayOneWay.way.dto.WayResponseDto;

import java.util.List;

public interface WayService {

    WayResponseDto createWay(WayRequestDto wayRequestDto, Long userId, Long placeId);

    WayResponseDto addPlaceToWay(Long wayId, Long placeId);

    WayResponseDto updateWayByCreator(Long userId, Long wayId, WayRequestDto wayRequestDto);

    List<WayResponseDto> getAllWaysByLocationName(String locationName, int from, int size);

    List<WayResponseDto> getAllWaysWithSortFromNewToOld(int from, int size);

    WayResponseDto geWayById(Long wayId);

    void userDeleteWay(Long userId, Long wayId);
}
