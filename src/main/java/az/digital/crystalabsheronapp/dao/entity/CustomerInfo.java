package az.digital.crystalabsheronapp.dao.entity;

import az.digital.crystalabsheronapp.enums.Payments;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customers")
public class CustomerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long customerId;
    String customerName;
    String customerSurname;
    Integer paymentId;
    String clientEmail;
    Payments status;
    Double amount;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-mm-yyyy'T'HH:mm")
    LocalDateTime creationTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-mm-yyyy'T'HH:mm")
    LocalDateTime endDate;
    LocalDate paymentDate;

//    @OneToMany(mappedBy = "customerInfo",
//                fetch = FetchType.LAZY,
//                cascade = CascadeType.ALL)
//    List<Building> buildings;
}
