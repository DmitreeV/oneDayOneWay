package dmitreev.petproject.java.oneDayOneWay.way.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dmitreev.petproject.java.oneDayOneWay.location.dto.LocationShortDto;
import dmitreev.petproject.java.oneDayOneWay.user.dto.UserShortDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WayResponseDto {

    private Long id;
    private String title;
    private String description;
    private UserShortDto creator;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdOn;
    private LocationShortDto location;
    private String places;
}
