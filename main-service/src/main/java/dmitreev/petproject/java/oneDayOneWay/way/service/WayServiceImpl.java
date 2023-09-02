package dmitreev.petproject.java.oneDayOneWay.way.service;

import dmitreev.petproject.java.oneDayOneWay.error.exception.NotFoundException;
import dmitreev.petproject.java.oneDayOneWay.location.repository.LocationRepository;
import dmitreev.petproject.java.oneDayOneWay.place.model.Place;
import dmitreev.petproject.java.oneDayOneWay.place.repository.PlaceRepository;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import dmitreev.petproject.java.oneDayOneWay.user.repository.UserRepository;
import dmitreev.petproject.java.oneDayOneWay.way.dto.WayRequestDto;
import dmitreev.petproject.java.oneDayOneWay.way.dto.WayResponseDto;
import dmitreev.petproject.java.oneDayOneWay.way.mapper.WayMapper;
import dmitreev.petproject.java.oneDayOneWay.way.model.Way;
import dmitreev.petproject.java.oneDayOneWay.way.repository.WayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WayServiceImpl implements WayService {
    private final LocationRepository locationRepository;

    private final WayRepository wayRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final WayMapper wayMapper;

    @Override
    public WayResponseDto createWay(WayRequestDto wayRequestDto, Long userId, Long placeId) {
        User user = getUser(userId);
        Place place = getPlace(placeId);
        Way way = new Way();

        way.setTitle(wayRequestDto.getTitle());
        way.setDescription(wayRequestDto.getDescription());
        way.setCreator(user);
        way.setCreatedOn(LocalDateTime.now());
        way.setLocation(locationRepository.findByName(wayRequestDto.getLocation()));
        way.addPlaceToWay(place);
        log.info("User saved new way {}.", way.getTitle());
        return WayMapper.toWayDto(wayRepository.save(way));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with userId=%d not found", userId)));
    }

    private Place getPlace(Long placeId) {
        return placeRepository.findById(placeId).orElseThrow(() ->
                new NotFoundException(String.format("Place with placeId=%d not found", placeId)));
    }
}
