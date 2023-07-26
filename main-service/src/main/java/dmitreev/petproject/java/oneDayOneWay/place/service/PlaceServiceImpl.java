package dmitreev.petproject.java.oneDayOneWay.place.service;

import dmitreev.petproject.java.oneDayOneWay.error.exception.NotFoundException;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceRequestDto;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceResponseDto;
import dmitreev.petproject.java.oneDayOneWay.place.mapper.PlaceMapper;
import dmitreev.petproject.java.oneDayOneWay.place.model.Place;
import dmitreev.petproject.java.oneDayOneWay.place.repository.PlaceRepository;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import dmitreev.petproject.java.oneDayOneWay.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final PlaceMapper placeMapper;

    @Override
    public PlaceResponseDto createPlace(Long userId, PlaceRequestDto placeRequestDto) {
        User user = getUser(userId);
        Place place = placeMapper.toPlace(placeRequestDto);
        place.setCreator(user);
        log.info("User with name {} saved new place {}.", user.getUsername(), place.getTitle());
        return placeMapper.toPlaceDto(placeRepository.save(place));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with userId=%d not found", userId)));
    }
}
