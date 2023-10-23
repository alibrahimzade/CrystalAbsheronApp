package az.digital.crystalabsheronapp.dto;

import az.digital.crystalabsheronapp.enums.Payments;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerInfoDto {
    Long customerId;
    String customerName;
    String customerSurname;
    Integer paymentId;
    String clientEmail;
    Payments status;
    Double amount;
    LocalDateTime creationTime;
    LocalDateTime endDate;
    LocalDate paymentDate;
}
