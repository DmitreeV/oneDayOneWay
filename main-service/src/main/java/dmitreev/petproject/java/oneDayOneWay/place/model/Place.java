package dmitreev.petproject.java.oneDayOneWay.place.model;

import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    //City
    //Category
    //List<Image>
    //List<Comment>
}
