package dmitreev.petproject.java.oneDayOneWay.place.controller;

import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceRequestDto;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceResponseDto;
import dmitreev.petproject.java.oneDayOneWay.place.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
