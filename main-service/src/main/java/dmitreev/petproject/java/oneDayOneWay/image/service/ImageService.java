package dmitreev.petproject.java.oneDayOneWay.image.service;

import dmitreev.petproject.java.oneDayOneWay.image.dto.ImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    ImageResponseDto store(MultipartFile file, Long placeId) throws IOException;
}
