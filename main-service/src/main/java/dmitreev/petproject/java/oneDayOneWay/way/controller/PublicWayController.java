package dmitreev.petproject.java.oneDayOneWay.way.controller;

import dmitreev.petproject.java.oneDayOneWay.way.dto.WayResponseDto;
import dmitreev.petproject.java.oneDayOneWay.way.service.WayService;
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
@RequestMapping("/info/ways")
@Tag(name = "Operations with ways in the public domain.")
public class PublicWayController {

    private final WayService wayService;

    @GetMapping("/search")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Return the list of ways found by location name.")
    List<WayResponseDto> getAllWaysByLocationName(@RequestParam String locationName,
                                                  @PositiveOrZero @RequestParam(defaultValue = "0") int from,
                                                  @Positive @RequestParam(defaultValue = "20") int size) {
        return wayService.getAllWaysByLocationName(locationName, from, size);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "View all ways with date sorting from new to old.")
    List<WayResponseDto> getAllWaysWithSortFromNewToOld(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                        @Positive @RequestParam(defaultValue = "5") Integer size) {
        return wayService.getAllWaysWithSortFromNewToOld(from, size);
    }

    @GetMapping("/{wayId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a way by its Id.")
    public WayResponseDto geWayById(@PathVariable Long wayId) {
        return wayService.geWayById(wayId);
    }
}
