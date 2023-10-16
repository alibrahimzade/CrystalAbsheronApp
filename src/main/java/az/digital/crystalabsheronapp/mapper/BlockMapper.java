package az.digital.crystalabsheronapp.mapper;

import az.digital.crystalabsheronapp.dao.entity.Block;
import az.digital.crystalabsheronapp.dto.BlockDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BlockMapper {


    BlockDto fromEntityToDto(Block block);

    Block fromDtoToEntity(BlockDto blockDto);

    List<BlockDto> fromEntityListToDtoList(List<Block> blocks);
}
