package dmitreev.petproject.java.oneDayOneWay.way.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WayRequestDto {

    @NotBlank(message = "'title' can not be blank")
    private String title;

    @NotBlank(message = "'description' can not be blank")
    private String description;

    @NotBlank(message = "'location' can not be blank")
    private String location;
}
