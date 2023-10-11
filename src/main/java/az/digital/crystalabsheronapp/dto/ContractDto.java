package az.digital.crystalabsheronapp.dto;

import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.enums.Payments;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractDto {

    Long id;

    String detail;
    @Enumerated(EnumType.STRING)
    Payments status;

    LocalDateTime creationTime;

    LocalDateTime closeDate;

    Long customerInfoId;
}
