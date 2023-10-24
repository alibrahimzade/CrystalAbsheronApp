package az.digital.crystalabsheronapp.dto;

import az.digital.crystalabsheronapp.enums.Payments;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    String clientEmail;
    @Enumerated(EnumType.STRING)
    Payments status;
    LocalDateTime soldDate;
    LocalDateTime paymentDate;
}
