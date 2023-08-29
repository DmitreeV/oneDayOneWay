package dmitreev.petproject.java.oneDayOneWay.place.controller;

import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentResponseDto;
import dmitreev.petproject.java.oneDayOneWay.comment.service.CommentService;
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
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/secured/places")
@Tag(name = "Operations with places available to the authorized user.")
public class UserPlaceController {

    private final PlaceService placeService;
    private final CommentService commentService;

    @PostMapping(("/{userId}"))
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new place by user.")
    public PlaceResponseDto createPlace(@PathVariable Long userId, @Valid @RequestBody PlaceRequestDto placeRequestDto) {
        return placeService.createPlace(userId, placeRequestDto);
    }

    @PatchMapping(("/{placeId}/file"))
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Create a new photo to place by user.")
    public PlaceResponseDto savePhotoToPlace(@PathVariable Long placeId,
                                             @RequestParam("file") MultipartFile file) throws IOException {
        return placeService.savePhotoToPlace(placeId, file);
    }

    @PatchMapping(("/{placeId}/comments/{commentId}"))
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Add a new comment to place by user.")
    public PlaceResponseDto saveCommentToPlace(@PathVariable Long placeId, @PathVariable Long commentId) {
        return placeService.saveCommentToPlace(placeId, commentId);
    }

    @GetMapping("/{placeId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a place by its Id.")
    public PlaceResponseDto getPlaceById(@PathVariable Long placeId) {
        return placeService.getPlaceById(placeId);
    }

    @GetMapping("/{placeId}/comments")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a list of all places comments.")
    public List<CommentResponseDto> getAllCommentsByPlace(@PathVariable Long placeId,
                                                          @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                          @Positive @RequestParam(defaultValue = "10") Integer size) {
        return commentService.getAllCommentsByPlace(placeId, from, size);
    }
}
