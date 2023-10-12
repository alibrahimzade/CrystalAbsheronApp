package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Building;
import az.digital.crystalabsheronapp.dao.entity.Residence;
import az.digital.crystalabsheronapp.dao.repository.BuildingRepository;
import az.digital.crystalabsheronapp.dao.repository.ResidenceRepository;
import az.digital.crystalabsheronapp.dto.BuildingDto;
import az.digital.crystalabsheronapp.enums.Payments;
import az.digital.crystalabsheronapp.enums.Status;
import az.digital.crystalabsheronapp.exceptions.NoSuchBuildingException;
import az.digital.crystalabsheronapp.exceptions.NoSuchResidenceException;
import az.digital.crystalabsheronapp.mapper.BuildingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hibernate.engine.spi.Status.SAVING;
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
        Building building = buildingRepository.findById(id).
                orElseThrow(()->new NoSuchBuildingException("The Building in "+id+" does not exist"));
        if (Objects.nonNull(building)) {
            return ResponseEntity.status(OK).body((buildingMapper.fromEntityToDto(building)));
        }
        return ResponseEntity.status(NOT_FOUND).build();
    }

    public ResponseEntity<?> createBuilding(BuildingDto buildingDto) {
        Residence residence = residenceRepository.findById(buildingDto.getResidenceId()).
                orElseThrow(() -> new NoSuchResidenceException("The residence in " + buildingDto.getResidenceId() + " does not exist"));
        if (Objects.nonNull(residence)){
        Building building = buildingMapper.fromDtoToEntity(buildingDto);
        building.setResidence(residenceRepository.findById(buildingDto.getResidenceId()).get());
        building.setStatus(Status.BOSH);
        buildingRepository.save(building);
        return ResponseEntity.status(OK).body("the building created");
        }
        return ResponseEntity.ok(NOT_FOUND);
    }

    public ResponseEntity<?> updateBuilder(Long buildingId, BuildingDto buildingDto) {
        Building building = buildingRepository.findById(buildingId).
                orElseThrow(()->new NoSuchBuildingException("The Building  does not exist"));
        if (Objects.nonNull(building)) {
            Residence residence = residenceRepository.findById(buildingDto.getResidenceId()).orElseGet(null);
            if (Objects.nonNull(residence)) {
                building.setResidence(residence);
                building.setPrice(buildingDto.getPrice());
                building.setPiecePrice(buildingDto.getPiecePrice());
                building.setDebt(buildingDto.getDebt());
                building.setHouseOwner(buildingDto.getHouseOwner());
                building.setArea(buildingDto.getArea());
                building.setNumberOfRooms(buildingDto.getNumberOfRooms());
                building.setMonthlyPayment(buildingDto.getMonthlyPayment());
                building.setFirstPayment(buildingDto.getFirstPayment());
                building.setFloor(buildingDto.getFloor());
                building.setDone(buildingDto.getDone());
                building.setDescription(buildingDto.getDescription());
                building.setGuarantor(buildingDto.getGuarantor());
                building.setBlock(buildingDto.getBlock());
                building.setInterestRate(buildingDto.getInterestRate());
                building.setPeriod(buildingDto.getPeriod());
                buildingRepository.save(building);
                return ResponseEntity.ok(SAVING);
            }
            return ResponseEntity.status(NOT_FOUND).body("This Residence does not exist.");
        }
        return ResponseEntity.status(NOT_FOUND).build();
    }

    public ResponseEntity<?> changeStatus(Long id, BuildingDto buildingDto){
        Building building = buildingRepository.findById(id).
                orElseThrow(() -> new NoSuchBuildingException("The Building in " + id + " does not exist"));
        if (Objects.nonNull(building)){
            building.setStatus(buildingDto.getStatus());
            buildingRepository.save(building);
            if (building.getStatus() == Status.KREDIT || building.getStatus() == Status.IPOTEKA ){

            }
            return ResponseEntity.ok(SAVING);
        }
        return ResponseEntity.status(NOT_FOUND).build();
    }

    public ResponseEntity<BuildingDto> deleteBuilding(Long id) {
        Building building = buildingRepository.findById(id).
                orElseThrow(()->new NoSuchBuildingException("The Building in "+id+" does not exist"));
        if (Objects.nonNull(building)) {
            buildingRepository.deleteById(id);
            return ResponseEntity.status(OK).build();
        }
        return ResponseEntity.status(OK).build();
    }

    public ResponseEntity<?> makeMonthlyPayment(Long buildingId , double monthlyPayment){
        Optional<Building> optionalBuilding = buildingRepository.findById(buildingId);
        if (optionalBuilding.isPresent()){
            Building building = optionalBuilding.get();
            double remainingDebt = building.getDebt() - monthlyPayment;
            if (remainingDebt < 0){
                remainingDebt = 0;
            }
            building.setDebt(remainingDebt);
            building.setDone(Payments.DONE);
            buildingRepository.save(building);
            return ResponseEntity.ok("Payment successfully made. Remaining debt is "+remainingDebt);
        }else {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> makeFirstPayment(Long buildingId, double firstPayment){
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new NoSuchBuildingException("The Building in " + buildingId + " does not exist"));
        if (Objects.nonNull(building)){
            double remainingAmount = building.getPrice() - firstPayment;
            if (remainingAmount < 0){
                remainingAmount = 0;
            }
            building.setDebt(remainingAmount);
            buildingRepository.save(building);
            return ResponseEntity.ok("FirstPayment successfully made. Remaining debt is "+remainingAmount);
        }else
            return ResponseEntity.status(NOT_FOUND).build();
    }
}
