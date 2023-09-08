package dmitreev.petproject.java.oneDayOneWay.location.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationResponseDto {

    private String name;
    private float lat;
    private float lon;
    private Long radius;
}
