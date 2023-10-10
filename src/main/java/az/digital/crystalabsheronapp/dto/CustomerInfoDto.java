package az.digital.crystalabsheronapp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
    Double amount;
    LocalDate paymentDate;
}
