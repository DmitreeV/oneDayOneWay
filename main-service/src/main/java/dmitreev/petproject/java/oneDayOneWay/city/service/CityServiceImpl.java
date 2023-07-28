package dmitreev.petproject.java.oneDayOneWay.city.service;

import dmitreev.petproject.java.oneDayOneWay.city.dto.CityRequestDto;
import dmitreev.petproject.java.oneDayOneWay.city.dto.CityResponseDto;
import dmitreev.petproject.java.oneDayOneWay.city.mapper.CityMapper;
import dmitreev.petproject.java.oneDayOneWay.city.model.City;
import dmitreev.petproject.java.oneDayOneWay.city.repository.CityRepository;
import dmitreev.petproject.java.oneDayOneWay.error.exception.ConflictException;
import dmitreev.petproject.java.oneDayOneWay.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public CityResponseDto createCity(CityRequestDto cityRequestDto) {
        City city = cityMapper.toCity(cityRequestDto);
        try {
            cityRepository.save(city);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("City is already exists.");
        }
        log.info("Saved new city : {}.", cityRequestDto);
        return cityMapper.toCityDto(city);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityResponseDto> getAllCities(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        log.info("Received a list of all cities with size of {}.", size);
        return cityRepository.findAll(pageable).stream()
                .map(cityMapper::toCityDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CityResponseDto getCityByName(String cityName) {
        String query = cityName.toLowerCase();

        City city = cityRepository.findCityByName(query);
        if (city.getName().isEmpty()) {
            throw new NotFoundException("City not found.");
        }
        log.info("Received a city with name {}.", city.getName());
        return cityMapper.toCityDto(city);
    }

    @Override
    public void deleteCity(Long cityId) {
        cityRepository.deleteById(cityId);
        log.info("City with id {} is deleted.", cityId);
    }
}
