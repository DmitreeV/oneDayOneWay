package dmitreev.petproject.java.oneDayOneWay.place.dto;

import dmitreev.petproject.java.oneDayOneWay.user.dto.UserShortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResponseDto {

    private Long id;
    private String title;
    private String description;
    private UserShortDto creator;
}
