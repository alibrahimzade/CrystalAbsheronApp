package az.digital.crystalabsheronapp.mapper;

import az.digital.crystalabsheronapp.dao.entity.Residence;
import az.digital.crystalabsheronapp.dto.ResidenceDto;

public enum ResidenceMapper {
    RESIDENCE_MAPPER;

    public final Residence buildDtoToEntity(ResidenceDto residenceDto){
        return Residence.builder()
                .name(residenceDto.getName())
                .build();
    }

    public final ResidenceDto buildEntityToDto(Residence residence){
        return ResidenceDto.builder()
                .name(residence.getName())
                .build();
    }
}
