package dmitreev.petproject.java.oneDayOneWay.location.controller;

import dmitreev.petproject.java.oneDayOneWay.location.dto.LocationRequestDto;
import dmitreev.petproject.java.oneDayOneWay.location.dto.LocationResponseDto;
import dmitreev.petproject.java.oneDayOneWay.location.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin/locations")
@Tag(name = "Operations with locations available to the administrator.")
public class AdminLocationController {

    private final LocationService locationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new location.")
    public LocationResponseDto saveLocation(@Valid @RequestBody LocationRequestDto locationDto) {
        return locationService.createLocation(locationDto);
    }

    @PatchMapping("/{locationId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update a location.")
    public LocationResponseDto updateLocation(@PathVariable Long locationId, @Valid @RequestBody LocationRequestDto locationDto) {
        return locationService.updateLocation(locationId, locationDto);
    }

    @DeleteMapping("/{locationId}")
    @Operation(summary = "Delete a location.")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable Long locationId) {
        locationService.deleteLocation(locationId);
    }
}
