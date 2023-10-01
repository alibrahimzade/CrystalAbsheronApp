package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Building;
import az.digital.crystalabsheronapp.dao.repository.BuildingRepository;
import az.digital.crystalabsheronapp.dto.BuildingDto;
import az.digital.crystalabsheronapp.mapper.BuildingMapper;
import az.digital.crystalabsheronapp.request.BuildingRequest;
import az.digital.crystalabsheronapp.response.BuildingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;
    BuildingMapper buildingMapper;


    public ResponseEntity<List<Building>> getAllBuilding() {
        return ResponseEntity.status(HttpStatus.OK).body(buildingRepository.findAll());

    }

    public ResponseEntity<BuildingResponse> createBuilding(BuildingRequest buildingRequest){

        buildingRepository.save(BuildingMapper.)

    }

}
