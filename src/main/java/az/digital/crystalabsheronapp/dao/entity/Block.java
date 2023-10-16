package az.digital.crystalabsheronapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "blocks")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "blocks",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    List<Building> buildings;

    @ManyToOne
    @JoinColumn(name = "residence_id", nullable = false)
    @JsonIgnore
    Residence residence;
}
