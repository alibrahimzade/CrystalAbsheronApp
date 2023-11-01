package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.dto.CustomerInfoDto;
import az.digital.crystalabsheronapp.service.CustomerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerInfoController {
    private final CustomerInfoService customerInfoService;

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerInfoDto>> getAllCustomers() {
        return customerInfoService.getAllCustomers();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CustomerInfoDto> getCustomerById(@PathVariable(name = "id") Long id) {
        return customerInfoService.getCustomerById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerInfoDto> createCustomer(@RequestBody CustomerInfoDto customerInfoDto) {
        return customerInfoService.createCustomer(customerInfoDto);
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerInfoDto> updateCustomer(@RequestBody CustomerInfoDto customerInfoDto) {
        return customerInfoService.updateCustomer(customerInfoDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CustomerInfoDto> deleteCustomer(@PathVariable(name = "id") Long id) {
        return customerInfoService.deleteCustomer(id);
    }
}
