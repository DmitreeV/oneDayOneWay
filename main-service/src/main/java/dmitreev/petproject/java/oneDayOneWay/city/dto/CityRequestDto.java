package dmitreev.petproject.java.oneDayOneWay.city.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityRequestDto {

    @NotBlank(message = "'name' can not be blank")
    private String name;
}
