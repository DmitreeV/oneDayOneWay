package dmitreev.petproject.java.oneDayOneWay.city.mapper;

import dmitreev.petproject.java.oneDayOneWay.city.dto.CityRequestDto;
import dmitreev.petproject.java.oneDayOneWay.city.dto.CityResponseDto;
import dmitreev.petproject.java.oneDayOneWay.city.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityResponseDto toCityDto(City city);

    City toCity(CityRequestDto cityRequestDto);
}

