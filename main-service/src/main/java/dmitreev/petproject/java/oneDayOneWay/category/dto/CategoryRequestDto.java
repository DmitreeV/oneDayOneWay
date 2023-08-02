package dmitreev.petproject.java.oneDayOneWay.category.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryRequestDto {

    @NotBlank(message = "'name' can not be blank")
    private String name;
}
