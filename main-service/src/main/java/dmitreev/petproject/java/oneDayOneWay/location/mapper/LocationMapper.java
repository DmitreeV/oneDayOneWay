package dmitreev.petproject.java.oneDayOneWay.location.mapper;

import dmitreev.petproject.java.oneDayOneWay.location.dto.LocationRequestDto;
import dmitreev.petproject.java.oneDayOneWay.location.dto.LocationResponseDto;
import dmitreev.petproject.java.oneDayOneWay.location.dto.LocationShortDto;
import dmitreev.petproject.java.oneDayOneWay.location.model.Location;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface LocationMapper {

    Location toLocation(LocationRequestDto locationDto);

    LocationResponseDto toLocationDto(Location location);

    public static LocationShortDto toLocationShortDto(Location location){
        return LocationShortDto.builder()
                .name(location.getName())
                .build();
    }
}
