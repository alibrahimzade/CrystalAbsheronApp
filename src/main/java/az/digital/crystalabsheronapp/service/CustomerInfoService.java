package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.dao.repository.CustomerInfoRepository;
import az.digital.crystalabsheronapp.dto.CustomerInfoDto;
import az.digital.crystalabsheronapp.exceptions.NoSuchCustomerException;
import az.digital.crystalabsheronapp.mapper.CustomerInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class CustomerInfoService {
    private final CustomerInfoRepository customerInfoRepository;
    private final CustomerInfoMapper customerInfoMapper;

    public ResponseEntity<List<CustomerInfoDto>> getAllCustomers(){
        List<CustomerInfo> all = customerInfoRepository.findAll();
        return ResponseEntity.status(OK).body(customerInfoMapper.fromEntityListToDtoList(all));
    }

    public ResponseEntity<CustomerInfoDto> getCustomerById(Long id){
        CustomerInfo customerInfo = customerInfoRepository.findById(id).
                orElseThrow(() -> new NoSuchCustomerException("The customer does not exist"));
        return ResponseEntity.status(OK).body(customerInfoMapper.fromEntityToDto(customerInfo));
    }

    public ResponseEntity<CustomerInfoDto> createCustomer(CustomerInfoDto customerInfoDto){
        CustomerInfo customerInfo = customerInfoMapper.fromDtoToEntity(customerInfoDto);
        return ResponseEntity.status(OK).body(customerInfoMapper.fromEntityToDto(customerInfoRepository.save(customerInfo)));
    }

    public ResponseEntity<CustomerInfoDto> updateCustomer(CustomerInfoDto customerInfoDto){
        customerInfoRepository.findById(customerInfoDto.getCustomerId()).
                orElseThrow(()-> new NoSuchCustomerException("The customer does not exist"));
        CustomerInfo customerInfo = customerInfoMapper.fromDtoToEntity(customerInfoDto);
        return ResponseEntity.ok(customerInfoMapper.fromEntityToDto(customerInfoRepository.save(customerInfo)));
    }

    public ResponseEntity<CustomerInfoDto> deleteCustomer(Long id){
        customerInfoRepository.findById(id).
                orElseThrow(()-> new NoSuchCustomerException("The customer does not exist"));
        customerInfoRepository.deleteById(id);
        return ResponseEntity.status(OK).build();
    }
}
