package dmitreev.petproject.java.oneDayOneWay.way.service;

import dmitreev.petproject.java.oneDayOneWay.way.dto.WayRequestDto;
import dmitreev.petproject.java.oneDayOneWay.way.dto.WayResponseDto;

public interface WayService {

    WayResponseDto createWay(WayRequestDto wayRequestDto, Long userId, Long placeId);
}
