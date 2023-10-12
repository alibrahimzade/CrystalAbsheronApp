package az.digital.crystalabsheronapp.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentStatus {

    boolean paid;
    String transactionId;
    String paymentMethod;
    String paymentDate;
}
