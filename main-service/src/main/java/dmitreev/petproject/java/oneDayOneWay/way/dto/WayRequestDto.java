package dmitreev.petproject.java.oneDayOneWay.way.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WayRequestDto {

    @NotBlank(message = "'title' can not be blank")
    @Schema(example = "Museum itinerary.")
    private String title;

    @NotBlank(message = "'description' can not be blank")
    @Schema(example = "This route takes you through the best museums in the city.")
    private String description;

    @NotBlank(message = "'location' can not be blank")
    @Schema(example = "Saint-Petersburg")
    private String location;
}
