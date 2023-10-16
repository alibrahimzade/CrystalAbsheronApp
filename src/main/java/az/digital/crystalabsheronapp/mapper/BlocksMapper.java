package az.digital.crystalabsheronapp.mapper;

import az.digital.crystalabsheronapp.dao.entity.Blocks;
import az.digital.crystalabsheronapp.dto.BlocksDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BlocksMapper {


    BlocksDto fromEntityToDto(Blocks blocks);

    Blocks fromDtoToEntity(BlocksDto blocksDto);

    List<BlocksDto> fromEntityListToDtoList(List<Blocks> blocks);
}
