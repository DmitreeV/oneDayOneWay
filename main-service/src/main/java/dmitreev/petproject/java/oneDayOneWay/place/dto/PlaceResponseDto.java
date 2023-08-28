package dmitreev.petproject.java.oneDayOneWay.place.dto;

import dmitreev.petproject.java.oneDayOneWay.category.dto.CategoryResponseDto;
import dmitreev.petproject.java.oneDayOneWay.comment.model.Comment;
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
    private CategoryResponseDto category;
    private String filename;
    private float lat;
    private float lon;
    private List<Comment> commentList;
}
