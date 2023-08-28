package dmitreev.petproject.java.oneDayOneWay.comment.service;

import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentRequestDto;
import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentResponseDto;

public interface CommentService {

    CommentResponseDto createComment(CommentRequestDto commentDto, Long userId, Long placeId);
}
