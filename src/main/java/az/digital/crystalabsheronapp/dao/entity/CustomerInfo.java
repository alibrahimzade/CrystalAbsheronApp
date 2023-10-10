package az.digital.crystalabsheronapp.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
    Double amount;
    LocalDate paymentDate;

}
