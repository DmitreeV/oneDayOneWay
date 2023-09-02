package dmitreev.petproject.java.oneDayOneWay.way.mapper;

import dmitreev.petproject.java.oneDayOneWay.way.dto.WayRequestDto;
import dmitreev.petproject.java.oneDayOneWay.way.dto.WayResponseDto;
import dmitreev.petproject.java.oneDayOneWay.way.model.Way;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

import static dmitreev.petproject.java.oneDayOneWay.location.mapper.LocationMapper.toLocationShortDto;
import static dmitreev.petproject.java.oneDayOneWay.user.mapper.UserMapper.toUserShortDto;

@Mapper(componentModel = "spring")
public interface WayMapper {

    @Mapping(target = "location.name", source = "location")
    Way toWay(WayRequestDto wayRequestDto);

    public static WayResponseDto toWayDto(Way way) {
        return WayResponseDto.builder()
                .id(way.getId())
                .title(way.getTitle())
                .description(way.getDescription())
                .creator(toUserShortDto(way.getCreator()))
                .location(toLocationShortDto(way.getLocation()))
                .createdOn(String.valueOf(LocalDateTime.now()))
                .places(String.valueOf(way.getPlaces()))
                .build();
    }
}
