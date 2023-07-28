package dmitreev.petproject.java.oneDayOneWay.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequestDto {

    @NotBlank(message = "'title' can not be blank")
    private String title;

    @NotBlank(message = "'description' can not be blank")
    private String description;

    @Positive
    private Long city;
}
