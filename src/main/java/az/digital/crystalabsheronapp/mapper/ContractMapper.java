package az.digital.crystalabsheronapp.mapper;

import az.digital.crystalabsheronapp.dao.entity.Contract;
import az.digital.crystalabsheronapp.dto.ContractDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ContractMapper {

    ContractDto fromEntityToDto(Contract contract);

    Contract fromDtoToEntity(ContractDto contractDto);

}
