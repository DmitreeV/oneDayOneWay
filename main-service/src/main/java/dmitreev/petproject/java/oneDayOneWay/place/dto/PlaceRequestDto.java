package dmitreev.petproject.java.oneDayOneWay.place.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequestDto {

    @NotBlank(message = "'title' can not be blank")
    @Schema(example = "St. Isaac's Cathedral")
    private String title;

    @NotBlank(message = "'description' can not be blank")
    @Schema(example = "The most beautiful Cathedral")
    private String description;

    @Positive
    @Schema(example = "1")
    private Long category;

    private String filename;

    @NotNull(message = "'lat' can not be null")
    @Schema(example = "59.94043")
    private float lat;

    @NotNull(message = "'lon' can not be null")
    @Schema(example = "30.31462")
    private float lon;
}
