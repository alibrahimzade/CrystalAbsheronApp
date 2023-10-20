package az.digital.crystalabsheronapp.mapper;

import az.digital.crystalabsheronapp.dao.entity.Block;
import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.dto.BlockDto;
import az.digital.crystalabsheronapp.dto.CustomerInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CustomerInfoMapper {

    CustomerInfoDto fromEntityToDto(CustomerInfo customerInfo);

    CustomerInfo fromDtoToEntity(CustomerInfoDto customerInfoDto);

    List<CustomerInfoDto> fromEntityListToDtoList(List<CustomerInfo> customerInfos);

}
