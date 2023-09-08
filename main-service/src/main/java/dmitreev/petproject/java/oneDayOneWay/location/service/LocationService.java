package dmitreev.petproject.java.oneDayOneWay.location.service;

import dmitreev.petproject.java.oneDayOneWay.location.dto.LocationRequestDto;
import dmitreev.petproject.java.oneDayOneWay.location.dto.LocationResponseDto;

import java.util.List;

public interface LocationService {

    LocationResponseDto createLocation(LocationRequestDto locationDto);

    LocationResponseDto updateLocation(Long locationId, LocationRequestDto locationDto);

    LocationResponseDto getLocationById(Long locationId);

    List<LocationResponseDto> getAllLocations(Integer from, Integer size);

    void deleteLocation(Long locationId);
}