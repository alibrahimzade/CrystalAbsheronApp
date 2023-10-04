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
}
