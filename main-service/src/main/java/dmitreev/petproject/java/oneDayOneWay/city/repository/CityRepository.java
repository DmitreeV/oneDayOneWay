package dmitreev.petproject.java.oneDayOneWay.city.repository;

import dmitreev.petproject.java.oneDayOneWay.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "select * from cities " +
            "where (lower(name) like '%' || ?1 || '%')",
            nativeQuery = true)
    City findCityByName(String query);
}
