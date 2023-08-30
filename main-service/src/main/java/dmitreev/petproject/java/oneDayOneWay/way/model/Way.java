package dmitreev.petproject.java.oneDayOneWay.way.model;

import dmitreev.petproject.java.oneDayOneWay.location.model.Location;
import dmitreev.petproject.java.oneDayOneWay.place.model.Place;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ways")
public class Way {

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
    private Location location;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "way")
    private List<Place> places;

}
