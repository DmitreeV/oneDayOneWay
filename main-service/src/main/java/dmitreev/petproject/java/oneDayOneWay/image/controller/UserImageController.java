package dmitreev.petproject.java.oneDayOneWay.image.controller;

import dmitreev.petproject.java.oneDayOneWay.image.dto.ImageResponseDto;
import dmitreev.petproject.java.oneDayOneWay.image.service.ImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/secured/images")
@Tag(name = "Operations with images available to the authorized user.")
public class UserImageController {

    private final ImageService imageService;

    @PostMapping(path = "/{placeId}", headers = "Content-Type= multipart/form-data")

    public ResponseEntity<ImageResponseDto> upload(@RequestParam("file") MultipartFile file,
                                                   @PathVariable("placeId") Long placeId) throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(imageService.store(file, placeId));
    }
}

