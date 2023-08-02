package dmitreev.petproject.java.oneDayOneWay.city.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CityRequestDto {

    @NotBlank(message = "'name' can not be blank")
    private String name;
}