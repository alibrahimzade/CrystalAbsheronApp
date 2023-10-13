package az.digital.crystalabsheronapp.response;

import az.digital.crystalabsheronapp.dao.entity.Blocks;
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

    Blocks block;

    Double interestRate;

    String period;


//    public static BuildingResponse buildingRegisterDto(Building buildingEntity){
//        return BuildingResponse.builder()
//                .price(buildingEntity.getPrice())
//                .piecePrice(buildingEntity.getPiecePrice())
//                .houseOwner(buildingEntity.getHouseOwner())
//                .area(buildingEntity.getArea())
//                .numberOfRooms(buildingEntity.getNumberOfRooms())
//                .monthlyPayment(buildingEntity.getMonthlyPayment())
//                .firstPayment(buildingEntity.getFirstPayment())
//                .floor(buildingEntity.getFloor())
//                .done(buildingEntity.getDone())
//                .description(buildingEntity.getDescription())
//                .guarantor(buildingEntity.getGuarantor())
//                .block(buildingEntity.getBlock())
//                .interestRate(buildingEntity.getInterestRate())
//                .period(buildingEntity.getPeriod())
//                .build();
//    }
}
