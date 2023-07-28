package dmitreev.petproject.java.oneDayOneWay.city.controller;

import dmitreev.petproject.java.oneDayOneWay.city.dto.CityRequestDto;
import dmitreev.petproject.java.oneDayOneWay.city.dto.CityResponseDto;
import dmitreev.petproject.java.oneDayOneWay.city.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/cities")
@Tag(name = "Operations with cities available to the admin.")
public class AdminCityController {

    private final CityService cityService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new city.")
    public CityResponseDto createCity(@Valid @RequestBody CityRequestDto cityRequestDto) {
        return cityService.createCity(cityRequestDto);
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a category.")
    public void deleteCity(@PathVariable Long cityId) {
        cityService.deleteCity(cityId);
    }
}
