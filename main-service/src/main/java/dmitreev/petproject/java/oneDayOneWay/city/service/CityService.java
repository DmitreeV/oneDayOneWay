package dmitreev.petproject.java.oneDayOneWay.city.service;

import dmitreev.petproject.java.oneDayOneWay.city.dto.CityRequestDto;
import dmitreev.petproject.java.oneDayOneWay.city.dto.CityResponseDto;

import java.util.List;

public interface CityService {

    CityResponseDto createCity(CityRequestDto cityRequestDto);
    List<CityResponseDto> getAllCities(Integer from, Integer size);
    CityResponseDto getCityByName(String cityName);
    void deleteCity(Long cityId);
}
