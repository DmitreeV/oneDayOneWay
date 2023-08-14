package dmitreev.petproject.java.oneDayOneWay.image.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "place_id")
    private Long placeId;

    @Lob
    //@Column(name = "data")                        // for Postgres
    @Column(name = "data", columnDefinition = "BLOB") // for H2
    private byte[] data;

    public Image(String name, String type, Long placeId, byte[] data) {
        this.name = name;
        this.type = type;
        this.placeId = placeId;
        this.data = data;
    }
}
