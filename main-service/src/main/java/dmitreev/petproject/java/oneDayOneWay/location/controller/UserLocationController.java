package dmitreev.petproject.java.oneDayOneWay.location.controller;

import dmitreev.petproject.java.oneDayOneWay.location.dto.LocationResponseDto;
import dmitreev.petproject.java.oneDayOneWay.location.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/secured/locations")
@Tag(name = "Operations with locations available to the authorized user.")
public class UserLocationController {

    private final LocationService locationService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a list of all locations.")
    public List<LocationResponseDto> getAllLocations(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                     @Positive @RequestParam(defaultValue = "10") Integer size) {
        return locationService.getAllLocations(from, size);
    }

    @GetMapping("/{locationId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a location by its Id.")
    public LocationResponseDto getLocationById(@PathVariable Long locationId) {
        return locationService.getLocationById(locationId);
    }
}
