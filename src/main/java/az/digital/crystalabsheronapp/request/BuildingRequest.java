package az.digital.crystalabsheronapp.request;

import az.digital.crystalabsheronapp.enums.Blocks;
import az.digital.crystalabsheronapp.enums.Payments;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingRequest {
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
