package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Contract;
import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.dao.repository.ContractRepository;
import az.digital.crystalabsheronapp.dao.repository.CustomerRepository;
import az.digital.crystalabsheronapp.dto.ContractDto;
import az.digital.crystalabsheronapp.exceptions.NoSuchContractException;
import az.digital.crystalabsheronapp.exceptions.NoSuchCustomerException;
import az.digital.crystalabsheronapp.mapper.ContractMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;
    private final CustomerRepository customerRepository;
    private final EmailService  emailService;

    public ResponseEntity<ContractDto> createdContract(ContractDto contractDto) {
        CustomerInfo customerInfo = customerRepository.findById(contractDto.getCustomerInfoId()).
                orElseThrow(() -> new NoSuchCustomerException("The Customer does not exist"));
        Contract contract = contractMapper.fromDtoToEntity(contractDto);
        contract.setCreationTime(LocalDateTime.now());
        contract.setCustomerInfo(customerRepository.findById(contractDto.getCustomerInfoId()).get());






        return ResponseEntity.status(OK).body(contractMapper.fromEntityToDto(contractRepository.save(contract)));
    }

    public ResponseEntity<ContractDto> updateContract(ContractDto contractDto) {
        Contract contract = contractRepository.findById(contractDto.getId()).
                orElseThrow(() -> new NoSuchContractException("This Contract does not exist"));
        CustomerInfo customerInfo = customerRepository.findById(contractDto.getCustomerInfoId()).
                orElseThrow(() -> new NoSuchCustomerException("The Customer does not exist"));
        Contract updated = contractMapper.fromDtoToEntity(contractDto);
        return ResponseEntity.ok(contractMapper.fromEntityToDto(contractRepository.save(updated)));
    }

    public ResponseEntity<ContractDto> getContractById(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() ->
                new NoSuchContractException("This contract does not exist"));
        return ResponseEntity.status(OK).body(contractMapper.fromEntityToDto(contract));
    }

    public ResponseEntity<List<ContractDto>> getAllContracts() {
        List<ContractDto> contractDtoList = contractRepository.findAll().stream().map(contractMapper::fromEntityToDto).toList();
        return ResponseEntity.status(OK).body(contractDtoList);
    }

    public void deleteContract(Long contractId) {
        contractRepository.findById(contractId).
                orElseThrow(() -> new NoSuchContractException("The Contract in " + contractId + " does not exist"));
        contractRepository.deleteById(contractId);
    }
}
