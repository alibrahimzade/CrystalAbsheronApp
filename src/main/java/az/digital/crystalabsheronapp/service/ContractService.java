package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Contract;
import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.dao.repository.ContractRepository;
import az.digital.crystalabsheronapp.dao.repository.CustomerRepository;
import az.digital.crystalabsheronapp.dto.ContractDto;
import az.digital.crystalabsheronapp.enums.Payments;
import az.digital.crystalabsheronapp.exceptions.ContractNotFound;
import az.digital.crystalabsheronapp.exceptions.NoSuchCustomer;
import az.digital.crystalabsheronapp.mapper.ContractMapper;
import az.digital.crystalabsheronapp.wrapper.ContractWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;
    private final CustomerRepository customerRepository;

    public ResponseEntity<ContractDto> createdContract(ContractDto contractDto) {
        CustomerInfo customerInfo = customerRepository.findById(contractDto.getCustomerInfoId()).
                orElseThrow(() -> new NoSuchCustomer("The Customer does not exist"));
        contractDto.setCreationTime(LocalDateTime.now());
        contractDto.setCustomerInfoId(customerInfo.getCustomerId());
        return ResponseEntity.status(OK)
                .body(contractMapper.fromEntityToDto
                        (contractRepository.save(contractMapper.fromDtoToEntity(contractDto))));
    }

    public ResponseEntity<ContractDto> updateContract(ContractDto contractDto) {
        Contract contract = contractRepository.findById(contractDto.getId()).
                orElseThrow(() -> new ContractNotFound("This Contract does not exist"));
        CustomerInfo customerInfo = customerRepository.findById(contractDto.getCustomerInfoId()).
                orElseThrow(() -> new NoSuchCustomer("The Customer does not exist"));
        Contract updated = contractMapper.fromDtoToEntity(contractDto);
        return ResponseEntity.ok(contractMapper.fromEntityToDto(contractRepository.save(updated)));
    }

    public ResponseEntity<ContractDto> getContractById(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() ->
                new ContractNotFound("This contract does not exist"));
        return ResponseEntity.status(OK).body(contractMapper.fromEntityToDto(contract));
    }

    public ResponseEntity<List<ContractWrapper>> getAllContracts() {
        return ResponseEntity.status(OK).body(contractRepository.getAllContracts());
    }

    public void deleteContract(Long contractId) {
        contractRepository.findById(contractId).
                orElseThrow(() -> new ContractNotFound("The Contract in " + contractId + " does not exist"));
        contractRepository.deleteById(contractId);
    }
}
