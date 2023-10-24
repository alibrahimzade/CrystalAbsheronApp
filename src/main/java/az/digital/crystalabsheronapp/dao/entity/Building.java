package az.digital.crystalabsheronapp.dao.entity;

import az.digital.crystalabsheronapp.enums.Payments;
import az.digital.crystalabsheronapp.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    Double debt;

    String houseOwner;

    Double area;

    Integer numberOfRooms;

    Double monthlyPayment;

    Double firstPayment;

    Integer floor;
    @Enumerated(EnumType.STRING)
    Status status;
    @Enumerated(EnumType.STRING)
    Payments done;

    String description;

    @Column(name = "zamin")
    String guarantor;

    Double interestRate;

    @Column(name = "odenish muddeti")
    String period;

    @ManyToOne
    @JoinColumn(name = "block_id", nullable = false)
    @JsonIgnore
    Block blocks;

    @ManyToOne
    @JoinColumn(name = "customer_info_id")
    @JsonIgnore
    CustomerInfo customerInfo;
}
