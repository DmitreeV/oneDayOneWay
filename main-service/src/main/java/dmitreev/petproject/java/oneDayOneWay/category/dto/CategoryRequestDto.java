package dmitreev.petproject.java.oneDayOneWay.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "Museum")
    private String name;
}
