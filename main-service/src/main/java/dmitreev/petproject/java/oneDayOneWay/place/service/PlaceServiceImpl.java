package dmitreev.petproject.java.oneDayOneWay.place.service;

import dmitreev.petproject.java.oneDayOneWay.category.model.Category;
import dmitreev.petproject.java.oneDayOneWay.category.repository.CategoryRepository;
import dmitreev.petproject.java.oneDayOneWay.error.exception.NotFoundException;
import dmitreev.petproject.java.oneDayOneWay.image.model.Image;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceRequestDto;
import dmitreev.petproject.java.oneDayOneWay.place.dto.PlaceResponseDto;
import dmitreev.petproject.java.oneDayOneWay.place.mapper.PlaceMapper;
import dmitreev.petproject.java.oneDayOneWay.place.model.Place;
import dmitreev.petproject.java.oneDayOneWay.place.repository.PlaceRepository;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import dmitreev.petproject.java.oneDayOneWay.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PlaceMapper placeMapper;

    @Override
    public PlaceResponseDto createPlace(Long userId, PlaceRequestDto placeRequestDto) {
        User user = getUser(userId);
        Place place = placeMapper.toPlace(placeRequestDto);
        Category category = getCategory(placeRequestDto.getCategory());

        place.setCreator(user);
        place.setCategory(category);
        log.info("User saved new place {}.", place.getTitle());
        return placeMapper.toPlaceDto(placeRepository.save(place));
    }

    @Override
    public PlaceResponseDto savePhotoToPlace(Long placeId, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Place place = getPlace(placeId);

        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            place.addImageToPlace(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            place.addImageToPlace(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            place.addImageToPlace(image3);
        }

        log.info("User saved new photo to place {}.", place.getTitle());
//        Place placeFromDb = placeRepository.save(place);
//        placeFromDb.setPreviewImageId(placeFromDb.getImages().get(0).getId());
        return placeMapper.toPlaceDto(placeRepository.save(place));
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
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
}
