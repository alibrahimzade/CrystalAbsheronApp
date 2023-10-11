package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;
}
