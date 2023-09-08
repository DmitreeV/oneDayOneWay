package dmitreev.petproject.java.oneDayOneWay.way.repository;

import dmitreev.petproject.java.oneDayOneWay.way.model.Way;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WayRepository extends JpaRepository<Way, Long> {

    @Query(value = "select * from ways " +
            "where (lower(location) like '%' || ?1 || '%')",
            nativeQuery = true)
    Page<Way> searchWaysByLocationName(String query, Pageable page);
}
