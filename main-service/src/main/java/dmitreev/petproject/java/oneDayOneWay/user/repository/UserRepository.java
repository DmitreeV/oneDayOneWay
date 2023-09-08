package dmitreev.petproject.java.oneDayOneWay.user.repository;

import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findAll(Pageable pageable);
}
