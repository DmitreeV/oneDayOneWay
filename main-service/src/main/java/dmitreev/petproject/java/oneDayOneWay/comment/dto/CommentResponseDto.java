package dmitreev.petproject.java.oneDayOneWay.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dmitreev.petproject.java.oneDayOneWay.user.dto.UserShortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;

    private String text;

    private UserShortDto author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
}
