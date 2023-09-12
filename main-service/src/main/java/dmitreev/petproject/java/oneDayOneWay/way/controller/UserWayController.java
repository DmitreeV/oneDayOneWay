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

    @PostMapping("/{userId}/{placeId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new way by user.")
    public WayResponseDto createWay(@Valid @RequestBody WayRequestDto wayRequestDto, @PathVariable Long userId, @PathVariable Long placeId) {
        return wayService.createWay(wayRequestDto, userId, placeId);
    }

    @PatchMapping("/{wayId}/{placeId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Add new place to way.")
    public WayResponseDto addPlaceToWay(@PathVariable Long wayId, @PathVariable Long placeId) {
        return wayService.addPlaceToWay(wayId, placeId);
    }

    @DeleteMapping("/{userId}/{wayId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a way by user.")
    public void userDeleteWay(@PathVariable Long userId,
                              @PathVariable Long wayId) {
        wayService.userDeleteWay(userId, wayId);
    }
}
