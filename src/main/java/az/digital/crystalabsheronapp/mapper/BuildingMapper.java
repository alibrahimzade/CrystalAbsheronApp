package az.digital.crystalabsheronapp.mapper;

import az.digital.crystalabsheronapp.dao.entity.Building;
import az.digital.crystalabsheronapp.dto.BuildingDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BuildingMapper {

   BuildingDto fromEntityToDto(Building building);

   Building fromDtoToEntity(BuildingDto buildingDto);











//    BUILDING_MAPPER;
//
//
//    public final Building buildDtoToEntity(BuildingDto buildingDto){
//        return Building.builder()
//                .id(buildingDto.getId())
//                .price(buildingDto.getPrice())
//                .piecePrice(buildingDto.getPiecePrice())
//                .houseOwner(buildingDto.getHouseOwner())
//                .area(buildingDto.getArea())
//                .numberOfRooms(buildingDto.getNumberOfRooms())
//                .monthlyPayment(buildingDto.getMonthlyPayment())
//                .firstPayment(buildingDto.getFirstPayment())
//                .floor(buildingDto.getFloor())
//                .done(buildingDto.getDone())
//                .description(buildingDto.getDescription())
//                .guarantor(buildingDto.getGuarantor())
//                .block(buildingDto.getBlock())
//                .interestRate(buildingDto.getInterestRate())
//                .period(buildingDto.getPeriod())
//                .build();
//    }
//
//    public final BuildingDto buildEntityToDto(Building building){
//        return BuildingDto.builder()
//                .id(building.getId())
//                .price(building.getPrice())
//                .piecePrice(building.getPiecePrice())
//                .houseOwner(building.getHouseOwner())
//                .area(building.getArea())
//                .numberOfRooms(building.getNumberOfRooms())
//                .monthlyPayment(building.getMonthlyPayment())
//                .firstPayment(building.getFirstPayment())
//                .floor(building.getFloor())
//                .done(building.getDone())
//                .description(building.getDescription())
//                .guarantor(building.getGuarantor())
//                .block(building.getBlock())
//                .interestRate(building.getInterestRate())
//                .period(building.getPeriod())
//                .build();
//    }
}
