package az.digital.crystalabsheronapp.mapper;

import az.digital.crystalabsheronapp.dao.entity.Residence;
import az.digital.crystalabsheronapp.dto.ResidenceDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResidenceMapper {

    ResidenceDto fromEntityToDto(Residence residence);

    Residence fromDtoToEntity(ResidenceDto residenceDto);


}
