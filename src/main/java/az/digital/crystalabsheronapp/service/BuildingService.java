package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Building;
import az.digital.crystalabsheronapp.dao.entity.Residence;
import az.digital.crystalabsheronapp.dao.repository.BuildingRepository;
import az.digital.crystalabsheronapp.dao.repository.ResidenceRepository;
import az.digital.crystalabsheronapp.dto.BuildingDto;
import az.digital.crystalabsheronapp.mapper.BuildingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.hibernate.engine.spi.Status.DELETED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final ResidenceRepository residenceRepository;
    private final BuildingMapper buildingMapper;

    public ResponseEntity<List<Building>> getAllBuilding() {
        return ResponseEntity.status(HttpStatus.OK).body(buildingRepository.findAll());
    }

    public ResponseEntity<BuildingDto> getBuildingById(Long id) {
        Building building = buildingRepository.findById(id).orElseGet(null);
        if (Objects.nonNull(building)) {
            return ResponseEntity.status(OK).body((buildingMapper.fromEntityToDto(building)));
        }
        return ResponseEntity.status(NOT_FOUND).build();
    }

    public ResponseEntity<BuildingDto> createBuilding(BuildingDto buildingDto) {
        Building building = buildingMapper.fromDtoToEntity(buildingDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(buildingMapper.fromEntityToDto(buildingRepository.save(building)));
    }

    public ResponseEntity<BuildingDto> updateBuilder(Long buildingId, BuildingDto buildingDto) {
        Building building = buildingRepository.findById(buildingId).orElseGet(null);
        if (Objects.nonNull(building)) {
            Residence residence = residenceRepository.findById(buildingDto.getResidenceId()).orElseGet(null);
            if (Objects.nonNull(residence)) {
                Building update = buildingMapper.fromDtoToEntity(buildingDto);
                update.setResidence(residence);
                return ResponseEntity.status(OK).body(buildingMapper.fromEntityToDto(buildingRepository.save(update)));
            }
            return ResponseEntity.status(OK).build();
        }
        return ResponseEntity.status(NOT_FOUND).build();

    }

    public ResponseEntity<BuildingDto> deleteBuilding(Long id) {
        Building building = buildingRepository.findById(id).orElseGet(null);
        if (Objects.nonNull(building)) {
            buildingRepository.deleteById(id);
            return ResponseEntity.status(OK).build();
        }
        return ResponseEntity.status(OK).build();
    }
}
