package dmitreev.petproject.java.oneDayOneWay.place.repository;

import dmitreev.petproject.java.oneDayOneWay.place.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
