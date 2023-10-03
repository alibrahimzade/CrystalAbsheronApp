package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.dto.BuildingDto;
import az.digital.crystalabsheronapp.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/building")
public class BuildingController {

    private final BuildingService buildingService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllBuildings() {
        return buildingService.getAllBuilding();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getBuildingById(@PathVariable(name = "id") Long id) {
        return buildingService.getBuildingById(id);
    }

    @PutMapping("/updateBuilding/{id}")
    public ResponseEntity<?> updateBuilding(@RequestBody BuildingDto buildingDto, @PathVariable(name = "id") Long buildingId) {
        return ResponseEntity.ok(buildingService.updateBuilder(buildingId, buildingDto));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(Long id) {
        return buildingService.deleteBuilding(id);

    }

    @PostMapping("/createdBuilding")
    public ResponseEntity<?> saveBuilding(@RequestBody BuildingDto buildingDto) {
        return buildingService.createBuilding(buildingDto);
    }
}
