package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.dao.entity.Information;
import az.digital.crystalabsheronapp.dao.entity.ResponseDTO;
import az.digital.crystalabsheronapp.service.InformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/information")
@CrossOrigin(origins = "*")
public class InformationController {

    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping
    public String meet() {
        return "Hi";
    }

    @PostMapping
    public ResponseEntity<?> createConsultation(@RequestBody Information info) {
        informationService.createConsultation(info);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setFirstName(info.getFirstName());
        responseDTO.setMessage("your consultation operation is done.");
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDTO);
    }
}
