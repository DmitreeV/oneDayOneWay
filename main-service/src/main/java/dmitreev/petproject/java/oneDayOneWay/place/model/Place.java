package dmitreev.petproject.java.oneDayOneWay.place.model;

import dmitreev.petproject.java.oneDayOneWay.category.model.Category;
import dmitreev.petproject.java.oneDayOneWay.image.model.Image;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "place")
    private List<Image> images = new ArrayList<>();

//    @Column(name = "preview_image_id")
//    private Long previewImageId;
//    @Column(name = "date_of_created")
//    private LocalDateTime dateOfCreated;

//    @PrePersist
//    private void init() {
//        dateOfCreated = LocalDateTime.now();
//    }

    public void addImageToPlace(Image image) {
        image.setPlace(this);
        images.add(image);
    }
    //Location
    //List<Comment>
}
