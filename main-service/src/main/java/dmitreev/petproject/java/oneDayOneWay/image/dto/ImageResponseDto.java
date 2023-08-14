package dmitreev.petproject.java.oneDayOneWay.image.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponseDto {

    private String name;
    private String url;
    private String type;
    private Long id;
    private long size;
}
