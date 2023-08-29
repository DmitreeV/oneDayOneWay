package dmitreev.petproject.java.oneDayOneWay.place.service;

import dmitreev.petproject.java.oneDayOneWay.category.model.Category;
import dmitreev.petproject.java.oneDayOneWay.category.repository.CategoryRepository;
import dmitreev.petproject.java.oneDayOneWay.comment.dto.CommentResponseDto;
import dmitreev.petproject.java.oneDayOneWay.comment.mapper.CommentMapper;
import dmitreev.petproject.java.oneDayOneWay.comment.model.Comment;
import dmitreev.petproject.java.oneDayOneWay.comment.repository.CommentRepository;
import dmitreev.petproject.java.oneDayOneWay.error.exception.NotFoundException;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceRequestDto;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceResponseDto;
import dmitreev.petproject.java.oneDayOneWay.place.mapper.PlaceMapper;
import dmitreev.petproject.java.oneDayOneWay.place.model.Place;
import dmitreev.petproject.java.oneDayOneWay.place.repository.PlaceRepository;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import dmitreev.petproject.java.oneDayOneWay.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PlaceServiceImpl implements PlaceService {
    private final CommentRepository commentRepository;

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final PlaceMapper placeMapper;

    private final CommentMapper commentMapper;

    @Value("/Users/dmitreevalerko/dima/oneDayOneWay/photo")
    private String uploadPath;

    @Override
    public PlaceResponseDto createPlace(Long userId, PlaceRequestDto placeRequestDto) {
        User user = getUser(userId);
        Place place = placeMapper.toPlace(placeRequestDto);
        Category category = getCategory(placeRequestDto.getCategory());

        place.setCreator(user);
        place.setCategory(category);
        log.info("User saved new place {}.", place.getTitle());
        return PlaceMapper.toPlaceDto(placeRepository.save(place));
    }

    //add places to way
//    private void validateLocation(Location location, Place place) {
//        double distance;
//        double x1 = location.getLat();
//        double y1 = location.getLon();
//        double x2 = place.getLat();
//        double y2 = place.getLon();
//
//        distance = Math.exp(Math.sqrt(2) / Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
//        if (distance < 2) {
//
//        }
//    }

    @Override
    public PlaceResponseDto savePhotoToPlace(Long placeId, MultipartFile file) throws IOException {
        Place place = getPlace(placeId);

        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            place.setFilename(resultFilename);
        }
        log.info("Saved new photo to place with id {}.", placeId);
        return PlaceMapper.toPlaceDto(placeRepository.save(place));
    }

    @Override
    public PlaceResponseDto saveCommentToPlace(Long placeId, Long commentId){
        Place place = getPlace(placeId);
        CommentResponseDto comment = commentMapper.toCommentDto(getComment(commentId));

        PlaceResponseDto placeResponseDto = PlaceMapper.toPlaceDto(place);
        placeResponseDto.getCommentList().add(0, comment);
        return placeResponseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public PlaceResponseDto getPlaceById(Long placeId) {
        log.info("Received a category with id {}.", placeId);
        return PlaceMapper.toPlaceDto(getPlace(placeId));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with userId=%d not found", userId)));
    }

    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new NotFoundException(String.format("Category with categoryId=%d not found", categoryId)));
    }

    private Place getPlace(Long placeId) {
        return placeRepository.findById(placeId).orElseThrow(() ->
                new NotFoundException(String.format("Place with placeId=%d not found", placeId)));
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException(String.format("Comment with commentId=%d not found", commentId)));
    }
}
