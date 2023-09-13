package dmitreev.petproject.java.oneDayOneWay.location.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "Saint-Petersburg")
    private String name;

    @NotNull(message = "'lat' can not be null")
    @Schema(example = "59.93")
    private float lat;

    @NotNull(message = "'lon' can not be null")
    @Schema(example = "30.31")
    private float lon;

    @NotNull(message = "'radius' can not be null")
    @Schema(example = "99")
    private Long radius;
}
