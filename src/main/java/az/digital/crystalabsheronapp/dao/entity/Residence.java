package az.digital.crystalabsheronapp.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NamedQuery( name = "Residence.getAllResidences",
        query = "select new az.digital.crystalabsheronapp.wrapper.ResidenceWrapper" +
                "(u.id, u.name)from Residence u ")


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "residence")
public class Residence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @OneToMany(mappedBy = "residence",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Block> blocks;
}
