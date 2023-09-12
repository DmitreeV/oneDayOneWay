package dmitreev.petproject.java.oneDayOneWay.way.service;

import dmitreev.petproject.java.oneDayOneWay.error.exception.ConflictException;
import dmitreev.petproject.java.oneDayOneWay.error.exception.NotFoundException;
import dmitreev.petproject.java.oneDayOneWay.location.model.Location;
import dmitreev.petproject.java.oneDayOneWay.location.repository.LocationRepository;
import dmitreev.petproject.java.oneDayOneWay.place.model.Place;
import dmitreev.petproject.java.oneDayOneWay.place.repository.PlaceRepository;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import dmitreev.petproject.java.oneDayOneWay.user.repository.UserRepository;
import dmitreev.petproject.java.oneDayOneWay.way.dto.WayRequestDto;
import dmitreev.petproject.java.oneDayOneWay.way.dto.WayResponseDto;
import dmitreev.petproject.java.oneDayOneWay.way.model.Way;
import dmitreev.petproject.java.oneDayOneWay.way.repository.WayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static dmitreev.petproject.java.oneDayOneWay.way.mapper.WayMapper.toWayDto;
import static dmitreev.petproject.java.oneDayOneWay.way.mapper.WayMapper.toWaysDto;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WayServiceImpl implements WayService {
    private final LocationRepository locationRepository;
    private final WayRepository wayRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    @Override
    public WayResponseDto createWay(WayRequestDto wayRequestDto, Long userId, Long placeId) {
        User user = getUser(userId);
        Place place = getPlace(placeId);
        Way way = new Way();

        way.setTitle(wayRequestDto.getTitle());
        way.setDescription(wayRequestDto.getDescription());
        way.setCreator(user);
        way.setCreatedOn(LocalDateTime.now());

        Location location = locationRepository.findByName(wayRequestDto.getLocation());
        if (location == null) {
            throw new NotFoundException("There is no location with that name.");
        }
        way.setLocation(location);
        way.addPlaceToWay(place);
        log.info("User saved new way {}.", way.getTitle());
        return toWayDto(wayRepository.save(way));
    }

    @Override
    public WayResponseDto addPlaceToWay(Long wayId, Long placeId) {
        Way way = getWay(wayId);
        Place place1 = getPlace(placeId);
        Place place2 = way.getPlaces().get(way.getPlaces().size() - 1);

        double distance;
        double x1 = place1.getLat();
        double y1 = place1.getLon();
        double x2 = place2.getLat();
        double y2 = place2.getLon();

        //вычисление расстояния между двумя точками на карте по их долготе и широте, с учетом кривизны Земли
        double theta = y1 - y2;
        distance = 60 * 1.1515 * (180 / Math.PI) * Math.acos(
                Math.sin(x1 * (Math.PI / 180)) * Math.sin(x2 * (Math.PI / 180)) +
                        Math.cos(x1 * (Math.PI / 180)) * Math.cos(x2 * (Math.PI / 180)) * Math.cos(theta * (Math.PI / 180)));

        if ((distance * 1.609344) < 2) {
            way.getPlaces().add(place1);
        } else {
            throw new ConflictException("The distance between places should be no more than 2 km.");
        }
        log.info("The user added a new place to the way {}.", place1.getTitle());
        return toWayDto(wayRepository.save(way));
    }

    @Override
    public WayResponseDto updateWayByCreator(Long userId, Long wayId, WayRequestDto wayRequestDto) {
        Way wayToUpdate = getWay(wayId);

        if (!wayToUpdate.getCreator().getId().equals(userId)) {
            throw new ConflictException("Only a creator can change a way info.");
        }
        wayToUpdate.setTitle(wayRequestDto.getTitle());
        wayToUpdate.setDescription(wayRequestDto.getDescription());
        wayToUpdate.setCreatedOn(LocalDateTime.now());

        log.info("Updated way with id {}.", wayId);
        return toWayDto(wayRepository.save(wayToUpdate));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WayResponseDto> getAllWaysByLocationName(String locationName, int from, int size) {

        if (locationName.isEmpty()) {
            return new ArrayList<>();
        }
        String query = locationName.toLowerCase();
        Pageable page = PageRequest.of(from / size, size);
        Page<Way> ways = wayRepository.searchWaysByLocationName(query, page);
        if (ways.isEmpty()) {
            return new ArrayList<>();
        }
        log.info("Received a list of ways by location name search with size {}.", ways.getSize());
        return toWaysDto(ways);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WayResponseDto> getAllWaysWithSortFromNewToOld(int from, int size) {
        Pageable pageable = PageRequest.of(from, size);

        log.info("Received a list of all ways sorted from newer to older.");
        return toWaysDto(wayRepository.findAll(pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public WayResponseDto geWayById(Long wayId) {
        log.info("Received a way with id {}.", wayId);
        return toWayDto(getWay(wayId));
    }

    @Override
    public void userDeleteWay(Long userId, Long wayId) {
        getUser(userId);
        Way way = getWay(wayId);

        if (!Objects.equals(way.getCreator().getId(), userId)) {
            throw new ConflictException("Only the creator can delete a way.");
        }
        wayRepository.deleteById(wayId);
        log.info("The way was deleted by the user {}.", userId);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with userId=%d not found", userId)));
    }

    private Place getPlace(Long placeId) {
        return placeRepository.findById(placeId).orElseThrow(() ->
                new NotFoundException(String.format("Place with placeId=%d not found", placeId)));
    }

    private Way getWay(Long wayId) {
        return wayRepository.findById(wayId).orElseThrow(() ->
                new NotFoundException(String.format("Way with wayId=%d not found", wayId)));
    }
}
