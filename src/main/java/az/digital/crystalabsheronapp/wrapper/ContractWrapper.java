package az.digital.crystalabsheronapp.wrapper;

import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.enums.Payments;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractWrapper {

    Long id;

    String detail;

    Payments status;

    LocalDateTime creationTime;


    LocalDateTime closeDate;


    CustomerInfo customerInfo;
}
