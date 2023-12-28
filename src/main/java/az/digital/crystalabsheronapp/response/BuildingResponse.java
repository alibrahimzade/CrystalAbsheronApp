package az.digital.crystalabsheronapp.response;

import az.digital.crystalabsheronapp.dao.entity.Block;
import az.digital.crystalabsheronapp.enums.Payments;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingResponse {

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
    Block block;
    Double interestRate;
    String period;
}
