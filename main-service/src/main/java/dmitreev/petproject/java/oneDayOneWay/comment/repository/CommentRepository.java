package dmitreev.petproject.java.oneDayOneWay.comment.repository;

import dmitreev.petproject.java.oneDayOneWay.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findByPlaceId(Long placeId);

    List<Comment> findAllByPlaceId(Long placeId);
}
