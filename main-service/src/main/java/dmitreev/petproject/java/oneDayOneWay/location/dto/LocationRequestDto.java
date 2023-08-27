package dmitreev.petproject.java.oneDayOneWay.location.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationRequestDto {

    @NotBlank(message = "'name' can not be blank")
    private String name;

    @NotNull(message = "'lat' can not be null")
    private float lat;

    @NotNull(message = "'lon' can not be null")
    private float lon;

    @NotNull(message = "'radius' can not be null")
    private Long radius;
}
