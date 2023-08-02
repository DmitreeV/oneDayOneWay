package dmitreev.petproject.java.oneDayOneWay.city.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityResponseDto {

    private Long id;
    private String name;
}