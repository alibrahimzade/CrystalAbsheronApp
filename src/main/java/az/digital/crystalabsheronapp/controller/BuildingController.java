package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.dao.entity.Building;
import az.digital.crystalabsheronapp.dto.BuildingDto;
import az.digital.crystalabsheronapp.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/building")
public class BuildingController {

    private final BuildingService buildingService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Building>> getAllBuildings() {
        return buildingService.getAllBuilding();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getBuildingById(@PathVariable(name = "id") Long id)    {
        return buildingService.getBuildingById(id);
    }

    @PutMapping("/updateBuilding/{id}")
    public ResponseEntity<?> updateBuilding(@RequestBody BuildingDto buildingDto, @PathVariable(name = "id") Long buildingId) {
        return ResponseEntity.ok(buildingService.updateBuilder(buildingId, buildingDto));

    }
    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id, @RequestBody BuildingDto buildingDto){
        return ResponseEntity.ok(buildingService.changeStatus(id,buildingDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        return buildingService.deleteBuilding(id);

    }

    @PostMapping("/createdBuilding")
    public ResponseEntity<?> saveBuilding(@RequestBody BuildingDto buildingDto) {
        return buildingService.createBuilding(buildingDto);
    }


    @PostMapping("/makePayment/{buildingId}")
    public ResponseEntity<?> makeMonthlyPayment(@RequestParam double paymentAmount,
                                                @PathVariable(name = "buildingId")Long id){
        return buildingService.makeMonthlyPayment(id,paymentAmount);
    }
//    @PostMapping("/firstPayment/{buildingId}")
//        public ResponseEntity<?> makeFirstPayment(@RequestParam double firstPayment,
//                                                    @PathVariable(name = "buildingId") Long id){
//            return buildingService.makeFirstPayment(id,firstPayment);
//        }

}
