package dmitreev.petproject.java.oneDayOneWay.place.model;

import dmitreev.petproject.java.oneDayOneWay.category.model.Category;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "filename")
    private String filename;

    @Column(name = "latitude", nullable = false)
    private float lat;

    @Column(name = "longitude", nullable = false)
    private float lon;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
//            mappedBy = "place")
//    private List<Comment> comments;
//
//    public void addCommentToPlace(Comment comment) {
//        comments.add(comment);
//    }
}
