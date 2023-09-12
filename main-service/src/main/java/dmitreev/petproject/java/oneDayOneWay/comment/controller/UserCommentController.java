package dmitreev.petproject.java.oneDayOneWay.comment.controller;

import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentRequestDto;
import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentResponseDto;
import dmitreev.petproject.java.oneDayOneWay.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/secured/{userId}/comments")
@Tag(name = "Operations with comments available to the authorized user.")
public class UserCommentController {

    private final CommentService commentService;

    @PostMapping("/{placeId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new comment by user.")
    public CommentResponseDto createComment(@PathVariable Long userId, @PathVariable Long placeId,
                                            @Valid @RequestBody CommentRequestDto commentDto) {
        return commentService.createComment(commentDto, userId, placeId);
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update a comment by user.")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @PathVariable Long userId,
                                            @Valid @RequestBody CommentRequestDto commentDto) {
        return commentService.updateComment(commentId, userId, commentDto);
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a comment by its Id.")
    public CommentResponseDto getCommentById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a comment by user.")
    public void userDeleteComment(@PathVariable Long userId,
                                  @PathVariable Long commentId) {
        commentService.userDeleteComment(commentId, userId);
    }
}
