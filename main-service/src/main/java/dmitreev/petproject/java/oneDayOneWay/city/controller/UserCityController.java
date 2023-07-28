package dmitreev.petproject.java.oneDayOneWay.city.controller;

import dmitreev.petproject.java.oneDayOneWay.city.dto.CityResponseDto;
import dmitreev.petproject.java.oneDayOneWay.city.service.CityService;
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
@RequestMapping("/secured/cities")
@Tag(name = "Operations with cities available to the authorized user.")
public class UserCityController {

    private final CityService cityService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a list of all cities.")
    public List<CityResponseDto> getAllCities(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                  @Positive @RequestParam(defaultValue = "10") Integer size) {
        return cityService.getAllCities(from, size);
    }

    @GetMapping("/search")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Search for a specific city by his name/part of the name.")
    CityResponseDto searchUserByName(@RequestParam String cityName) {
        return cityService.getCityByName(cityName);
    }
}
