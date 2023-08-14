package dmitreev.petproject.java.oneDayOneWay.image.service;

import dmitreev.petproject.java.oneDayOneWay.error.exception.BadRequestException;
import dmitreev.petproject.java.oneDayOneWay.image.dto.ImageResponseDto;
import dmitreev.petproject.java.oneDayOneWay.image.model.Image;
import dmitreev.petproject.java.oneDayOneWay.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public ImageResponseDto store(MultipartFile file, Long placeId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Optional<Image> fileDB = imageRepository.findImageByName(fileName);

        if (fileDB.isEmpty() || !fileName.equals(fileDB.get().getName())) {
            Image image = new Image(fileName, file.getContentType(), placeId, file.getBytes());
            Image img = imageRepository.save(image);

            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(img.getId().toString())
                    .toUriString();

            return new ImageResponseDto(
                    img.getName(),
                    fileDownloadUri,
                    img.getType(),
                    img.getPlaceId(),
                    img.getData().length);
        }
        throw new BadRequestException("Attachment with this name already exists");
    }
}
