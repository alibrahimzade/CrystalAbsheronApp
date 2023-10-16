package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Contract;
import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.dao.repository.ContractRepository;
import az.digital.crystalabsheronapp.dao.repository.CustomerRepository;
import az.digital.crystalabsheronapp.dto.ContractDto;
import az.digital.crystalabsheronapp.dto.PaymentStatusDto;
import az.digital.crystalabsheronapp.exceptions.NoSuchContractException;
import az.digital.crystalabsheronapp.exceptions.NoSuchCustomerException;
import az.digital.crystalabsheronapp.mapper.ContractMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;
    private final CustomerRepository customerRepository;
    private final EmailService  emailService;


    public ResponseEntity<List<ContractDto>> getCustomersWhoPaid(ContractDto contractDto) {
        List<Contract> paidCustomers = contractRepository.findAllByHasPaid(true);
        List<ContractDto> dtoList = paidCustomers.stream().map(contractMapper::fromEntityToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    public ResponseEntity<List<ContractDto>> getCustomersWhoDidNotPay(ContractDto contractDto) {
        List<Contract> notPaidCustomers = contractRepository.findAllByHasPaid(false);
        List<ContractDto> dtoList = notPaidCustomers.stream().map(contractMapper::fromEntityToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    public ResponseEntity<ContractDto> updatePaymentStatus(Long customerId, PaymentStatusDto paymentStatusDTO) {//catch from req
        CustomerInfo customer = customerRepository.findById(customerId)
                .orElse(null);

        if (Objects.isNull(customer)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //If you want to create new contract you can use this way
//        Contract contract = new Contract();
//        contract.setHasPaid(paymentStatusDTO.isPaid());

        List<Contract> currentContracts = customer.getContracts();
//        currentContracts.add(contract); // add contract

        //update?
        //contract name?
        List<Contract> list = currentContracts.stream().filter((c) -> c.getName().equals("Agency payment")).toList();
        Contract cc=list.get(0);//for one contract, if you  have plenty of contract you can iterate all of them

        cc.setHasPaid(true);
        int i= currentContracts.indexOf(list.get(0));

        currentContracts.set(i, cc);
        customer.setContracts(currentContracts);

        customerRepository.save(customer);

        return new ResponseEntity<>(new ContractDto(), HttpStatus.OK);
    }






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
        updated.setCustomerInfo(customerInfo);
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
