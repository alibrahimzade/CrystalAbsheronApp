package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.dto.ContractDto;
import az.digital.crystalabsheronapp.service.ContractService;
import az.digital.crystalabsheronapp.wrapper.ContractWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseEntity<List<ContractWrapper>>> getAllContracts(){
        return ResponseEntity.ok(contractService.getAllContracts());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ContractDto> getContractById(@PathVariable(name = "id") Long contractId){
        return contractService.getContractById(contractId);
    }
    @PostMapping("/create")
    public ResponseEntity<ContractDto> createContract(@RequestBody ContractDto contractDto){
        return contractService.createdContract(contractDto);
    }
    @PutMapping("/update")
    public ResponseEntity<ContractDto> updateContract(@RequestBody ContractDto contractDto){
        return contractService.updateContract(contractDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContract(@PathVariable(name = "id") Long contractId){
        contractService.deleteContract(contractId);
    }
}
