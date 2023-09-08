package dmitreev.petproject.java.oneDayOneWay.comment.service;

import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentRequestDto;
import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentResponseDto;
import dmitreev.petproject.java.oneDayOneWay.comment.mapper.CommentMapper;
import dmitreev.petproject.java.oneDayOneWay.comment.model.Comment;
import dmitreev.petproject.java.oneDayOneWay.comment.repository.CommentRepository;
import dmitreev.petproject.java.oneDayOneWay.error.exception.ConflictException;
import dmitreev.petproject.java.oneDayOneWay.error.exception.NotFoundException;
import dmitreev.petproject.java.oneDayOneWay.place.model.Place;
import dmitreev.petproject.java.oneDayOneWay.place.repository.PlaceRepository;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import dmitreev.petproject.java.oneDayOneWay.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentResponseDto createComment(CommentRequestDto commentDto, Long userId, Long placeId) {
        User user = getUser(userId);
        Place place = getPlace(placeId);
        Comment comment = commentMapper.toComment(commentDto);

        comment.setAuthor(user);
        comment.setPlace(place);
        comment.setCreated(LocalDateTime.now());
        place.addCommentToPlace(comment);
        log.info("Saved new comment : < {} > to the place : {}", commentDto.getText(), place.getTitle());
        return commentMapper.toCommentDto(commentRepository.save(comment));
    }

    @Override
    public CommentResponseDto updateComment(Long commentId, Long userId, CommentRequestDto commentDto) {
        Comment comment = commentRepository.findByIdAndAuthorId(commentId, userId)
                .orElseThrow(() -> new ConflictException("Only the author can change the comment."));

        comment.setText(commentDto.getText());
        log.info("Updated comment with id {}.", commentId);
        return commentMapper.toCommentDto(commentRepository.save(comment));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getAllCommentsByPlace(Long placeId, Integer from, Integer size) {
        Place place = getPlace(placeId);
        List<Comment> comments = commentRepository.findAllByPlace(place, PageRequest.of(from / size, size));

        log.info("Received a list of all comments company id {} with size of {}.", placeId, comments.size());
        return comments.stream()
                .map(commentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CommentResponseDto getCommentById(Long commentId) {
        Comment comment = getComment(commentId);
        log.info("Received a comment with id {}.", commentId);
        return commentMapper.toCommentDto(comment);
    }

    @Override
    public void userDeleteComment(Long commentId, Long userId) {
        getUser(userId);
        Comment comment = getComment(commentId);

        if (!Objects.equals(comment.getAuthor().getId(), userId)) {
            throw new ConflictException("Only the author can delete a comment.");
        }
        commentRepository.deleteById(commentId);
        log.info("The comment was deleted by the user {}.", userId);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with userId=%d not found", userId)));
    }

    private Place getPlace(Long placeId) {
        return placeRepository.findById(placeId).orElseThrow(() ->
                new NotFoundException(String.format("Place with id=%d not found", placeId)));
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException(String.format("Comment with commentId=%d not found", commentId)));
    }
}
