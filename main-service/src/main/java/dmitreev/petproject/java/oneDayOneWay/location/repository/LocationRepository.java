package dmitreev.petproject.java.oneDayOneWay.location.repository;

import dmitreev.petproject.java.oneDayOneWay.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
