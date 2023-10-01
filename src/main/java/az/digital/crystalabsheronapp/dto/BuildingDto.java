package az.digital.crystalabsheronapp.dto;

import az.digital.crystalabsheronapp.enums.Blocks;
import az.digital.crystalabsheronapp.enums.Payments;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingDto {

    Double price;

    Double piecePrice;

    String houseOwner;

    Double area;

    Integer numberOfRooms;

    Double monthlyPayment;

    Double firstPayment;

    Integer floor;

    Payments done;

    String description;

    String guarantor;

    Blocks block;

    Double interestRate;

    String period;
}
