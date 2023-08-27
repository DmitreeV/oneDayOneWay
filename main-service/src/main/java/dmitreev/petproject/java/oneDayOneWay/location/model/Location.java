package dmitreev.petproject.java.oneDayOneWay.location.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude", nullable = false)
    private float lat;

    @Column(name = "longitude", nullable = false)
    private float lon;

    @Column(name = "radius")
    private Long radius;
}
