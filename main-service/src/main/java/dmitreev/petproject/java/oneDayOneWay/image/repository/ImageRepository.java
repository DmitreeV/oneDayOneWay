package dmitreev.petproject.java.oneDayOneWay.image.repository;

import dmitreev.petproject.java.oneDayOneWay.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
