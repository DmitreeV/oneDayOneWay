package dmitreev.petproject.java.oneDayOneWay.place.dto;

import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryShortDto;
import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentResponseDto;
import dmitreev.petproject.java.oneDayOneWay.user.dto.UserShortDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResponseDto {

    private Long id;
    private String title;
    private String description;
    private UserShortDto creator;
    private CategoryShortDto category;
    private String filename;
    private float lat;
    private float lon;
    private List<CommentResponseDto> commentList;
}
