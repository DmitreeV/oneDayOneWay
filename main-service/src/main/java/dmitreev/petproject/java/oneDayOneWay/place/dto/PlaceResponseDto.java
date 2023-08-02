package dmitreev.petproject.java.oneDayOneWay.place.dto;

import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryResponseDto;
import dmitreev.petproject.java.oneDayOneWay.city.dto.CityResponseDto;
import dmitreev.petproject.java.oneDayOneWay.user.dto.UserShortDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResponseDto {

    private Long id;
    private String title;
    private String description;
    private UserShortDto creator;
    private CityResponseDto city;
    private CategoryResponseDto category;
}
