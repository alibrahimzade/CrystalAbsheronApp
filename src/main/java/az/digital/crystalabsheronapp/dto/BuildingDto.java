package az.digital.crystalabsheronapp.dto;

import az.digital.crystalabsheronapp.enums.Blocks;
import az.digital.crystalabsheronapp.enums.Payments;
import az.digital.crystalabsheronapp.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingDto {

    Long  id;

    Double price;

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

    String guarantor;
    @Enumerated(EnumType.STRING)
    Blocks block;

    Double interestRate;

    String period;
    Long residenceId;
}
