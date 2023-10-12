package az.digital.crystalabsheronapp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentStatusDto {

    boolean paid;
    String transactionId;
    String paymentMethod;
    String paymentDate;
}
