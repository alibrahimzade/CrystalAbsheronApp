package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Building;
import az.digital.crystalabsheronapp.dao.repository.BuildingRepository;
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


@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;

    public ResponseEntity<List<Building>> getAllBuilding() {
        return ResponseEntity.status(HttpStatus.OK).body(buildingRepository.findAll());
    }

    public ResponseEntity<?> getBuildingById(Long id) {
        Building building = buildingRepository.findById(id).orElseGet(null);
        if (Objects.nonNull(building)) {
            return ResponseEntity.ok(BuildingMapper.BUILDING_MAPPER.buildEntityToDto(building));
        }
        return ResponseEntity.status(NOT_FOUND).body("THIS BUILDING IS NOT EXIST");
    }

    public void createBuilding(BuildingDto buildingDto) {
        buildingRepository.save(BuildingMapper.BUILDING_MAPPER.buildDtoToEntity(buildingDto));
    }

    public ResponseEntity<?> updateBuilder(Long buildingId, BuildingDto buildingDto) {
        Building building = buildingRepository.findById(buildingId).orElseGet(null);
        if (Objects.nonNull(building)) {
            Building update = BuildingMapper.BUILDING_MAPPER.buildDtoToEntity(buildingDto);
            return ResponseEntity.ok(buildingRepository.save(update));
        }
        return ResponseEntity.status(NOT_FOUND).body("THIS USER IS NOT FOUND");
    }

    public ResponseEntity<?> deleteBuilding(Long id) {
        Building building = buildingRepository.findById(id).orElseGet(null);
        if (Objects.nonNull(building)) {
            buildingRepository.deleteById(id);
            return ResponseEntity.ok(DELETED);
        }
        return ResponseEntity.status(NOT_FOUND).body("THIS BUILDING DOES NOT EXIST");
    }
}
