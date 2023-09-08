package dmitreev.petproject.java.oneDayOneWay.comment.service;

import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentRequestDto;
import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto createComment(CommentRequestDto commentDto, Long userId, Long placeId);

    CommentResponseDto updateComment(Long commentId, Long userId, CommentRequestDto commentDto);

    List<CommentResponseDto> getAllCommentsByPlace(Long placeId, Integer from, Integer size);

    CommentResponseDto getCommentById(Long commentId);

    void userDeleteComment(Long commentId, Long userId);
}
