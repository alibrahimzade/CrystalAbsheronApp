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


@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final ResidenceRepository residenceRepository;

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

    public ResponseEntity<?> createBuilding(BuildingDto buildingDto) {
       return ResponseEntity.ok(buildingRepository.save(BuildingMapper.BUILDING_MAPPER.buildDtoToEntity(buildingDto)));
    }

    public ResponseEntity<?> updateBuilder(Long buildingId, BuildingDto buildingDto) {
        Building building = buildingRepository.findById(buildingId).orElseGet(null);
        if (Objects.nonNull(building)) {
            Residence residence = residenceRepository.findById(buildingDto.getResidenceId()).orElseGet(null);
            if (Objects.nonNull(residence)) {
                Building update = BuildingMapper.BUILDING_MAPPER.buildDtoToEntity(buildingDto);
                update.setPrice(buildingDto.getPrice());
                update.setPiecePrice(buildingDto.getPiecePrice());
                update.setHouseOwner(buildingDto.getHouseOwner());
                update.setArea(buildingDto.getArea());
                update.setNumberOfRooms(buildingDto.getNumberOfRooms());
                update.setMonthlyPayment(buildingDto.getMonthlyPayment());
                update.setFirstPayment(buildingDto.getFirstPayment());
                update.setFloor(buildingDto.getFloor());
                update.setDone(buildingDto.getDone());
                update.setDescription(buildingDto.getDescription());
                update.setGuarantor(buildingDto.getGuarantor());
                update.setBlock(buildingDto.getBlock());
                update.setInterestRate(buildingDto.getInterestRate());
                update.setPeriod(buildingDto.getPeriod());
                update.setResidence(residence);
                return ResponseEntity.ok(buildingRepository.save(update));
            }
            return ResponseEntity.status(NOT_FOUND).body("Residence NOT FOUND");
        }
        return ResponseEntity.status(NOT_FOUND).body("BUILDING NOT FOUND");

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
