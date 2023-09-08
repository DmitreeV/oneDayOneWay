package dmitreev.petproject.java.oneDayOneWay.comment.mapper;

import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentRequestDto;
import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentResponseDto;
import dmitreev.petproject.java.oneDayOneWay.comment.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentRequestDto commentDto);

    public CommentResponseDto toCommentDto(Comment comment);
}
