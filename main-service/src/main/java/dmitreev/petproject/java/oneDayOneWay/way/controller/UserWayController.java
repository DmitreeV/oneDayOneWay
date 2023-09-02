package dmitreev.petproject.java.oneDayOneWay.way.controller;

import dmitreev.petproject.java.oneDayOneWay.way.dto.WayRequestDto;
import dmitreev.petproject.java.oneDayOneWay.way.dto.WayResponseDto;
import dmitreev.petproject.java.oneDayOneWay.way.service.WayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/secured/ways")
@Tag(name = "Operations with ways available to the authorized user.")
public class UserWayController {

    private final WayService wayService;

    @PostMapping(("/{userId}/{placeId}"))
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new place by user.")
    public WayResponseDto createWay(@Valid @RequestBody WayRequestDto wayRequestDto, @PathVariable Long userId, @PathVariable Long placeId) {
        return wayService.createWay(wayRequestDto, userId, placeId);
    }
}
