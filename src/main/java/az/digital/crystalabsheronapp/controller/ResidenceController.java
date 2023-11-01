package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.dto.ResidenceDto;
import az.digital.crystalabsheronapp.service.ResidenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residence")
public class ResidenceController {

    private final ResidenceService residenceService;

    @GetMapping("/getResidence/{id}")
    public ResponseEntity<?> getResidence(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(residenceService.findResidenceById(id));
    }

    @GetMapping("/getAllResidences")
    public ResponseEntity<?> getAllResidences() {
        return ResponseEntity.ok(residenceService.findAllResidences());
    }

    @PostMapping("/addResidence")
    public ResponseEntity<?> createResidence(@RequestBody ResidenceDto residenceDto) {
        return ResponseEntity.ok(residenceService.createResidence(residenceDto));
    }

    @PutMapping("/updateResidence/{id}")
    public ResponseEntity<?> updateResidence(@PathVariable(name = "id") Long id,
                                             @RequestBody ResidenceDto residenceDto) {
        return ResponseEntity.ok(residenceService.updateResidence(id, residenceDto));
    }

    @DeleteMapping("/deleteResidence/{id}")
    public ResponseEntity<?> deleteResidence(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(residenceService.deleteResidenceById(id));
    }
}
