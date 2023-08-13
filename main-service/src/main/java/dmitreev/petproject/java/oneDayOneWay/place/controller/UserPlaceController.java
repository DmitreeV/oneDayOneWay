package dmitreev.petproject.java.oneDayOneWay.place.controller;

import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceRequestDto;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceResponseDto;
import dmitreev.petproject.java.oneDayOneWay.place.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/secured/places")
@Tag(name = "Operations with places available to the authorized user.")
public class UserPlaceController {

    private final PlaceService placeService;

    @PostMapping(("/{userId}"))
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new place by user.")
    public PlaceResponseDto createPlace(@PathVariable Long userId, @Valid @RequestBody PlaceRequestDto placeRequestDto) {
        return placeService.createPlace(userId, placeRequestDto);
    }

    @PatchMapping(("/{placeId}"))
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Create a new photo to place by user.")
    public PlaceResponseDto savePhotoToPlace(@PathVariable Long placeId,
                                        @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                        @RequestParam("file3") MultipartFile file3) throws IOException {
        return placeService.savePhotoToPlace(placeId, file1, file2, file3);
    }
}
