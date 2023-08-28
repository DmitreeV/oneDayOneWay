package dmitreev.petproject.java.oneDayOneWay.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {

    @NotBlank(message = "'text' can not be blank")
    @Size(min = 1, max = 2000)
    private String text;
}
