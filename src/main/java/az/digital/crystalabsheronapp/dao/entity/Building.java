package az.digital.crystalabsheronapp.dao.entity;

import az.digital.crystalabsheronapp.enums.Blocks;
import az.digital.crystalabsheronapp.enums.Payments;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "building")
public class Building {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Double price;

    @Column(name = "1m qiymet")
    Double piecePrice;

    String houseOwner;

    Double area;

    Integer numberOfRooms;

    Double monthlyPayment;

    Double firstPayment;

    Integer floor;

    Payments done;

    String description;

    @Column(name = "zamin")
    String guarantor;

    Blocks block;

    Double interestRate;

    @Column(name = "odenish muddeti")
    String period;


}
