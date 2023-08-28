package dmitreev.petproject.java.oneDayOneWay.comment.service;

import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentRequestDto;
import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentResponseDto;
import dmitreev.petproject.java.oneDayOneWay.comment.mapper.CommentMapper;
import dmitreev.petproject.java.oneDayOneWay.comment.model.Comment;
import dmitreev.petproject.java.oneDayOneWay.comment.repository.CommentRepository;
import dmitreev.petproject.java.oneDayOneWay.error.exception.NotFoundException;
import dmitreev.petproject.java.oneDayOneWay.place.model.Place;
import dmitreev.petproject.java.oneDayOneWay.place.repository.PlaceRepository;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import dmitreev.petproject.java.oneDayOneWay.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{

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
        log.info("Saved new comment : < {} > to the place : {}", commentDto.getText(), place.getTitle());
        return commentMapper.toCommentDto(commentRepository.save(comment));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with userId=%d not found", userId)));
    }

    private Place getPlace(Long placeId) {
        return placeRepository.findById(placeId).orElseThrow(() ->
                new NotFoundException(String.format("Place with id=%d not found", placeId)));
    }

}
