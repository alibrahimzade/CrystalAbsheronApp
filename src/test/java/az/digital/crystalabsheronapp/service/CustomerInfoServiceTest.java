package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.dao.repository.CustomerInfoRepository;
import az.digital.crystalabsheronapp.dto.CustomerInfoDto;
import az.digital.crystalabsheronapp.exceptions.NoSuchCustomerException;
import az.digital.crystalabsheronapp.mapper.CustomerInfoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;


public class CustomerInfoServiceTest {

    @Mock
    private CustomerInfoRepository customerInfoRepository;

    @Mock
    private CustomerInfoMapper customerInfoMapper;

    @InjectMocks
    private CustomerInfoService customerInfoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCustomers() {
        // Create a list of CustomerInfoDto for testing
        List<CustomerInfoDto> customerInfoDtos = List.of(new CustomerInfoDto(), new CustomerInfoDto());
        List<CustomerInfo> customerInfos = List.of(new CustomerInfo(), new CustomerInfo());

        when(customerInfoRepository.findAll()).thenReturn(customerInfos);
        when(customerInfoMapper.fromEntityListToDtoList(customerInfos)).thenReturn(customerInfoDtos);

        // Call the service method
        ResponseEntity<List<CustomerInfoDto>> responseEntity = customerInfoService.getAllCustomers();

        // Assertions
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(customerInfoDtos, responseEntity.getBody());
    }

    @Test
    public void testGetCustomerById() {
        // Create a CustomerInfo for testing
        CustomerInfo customerInfo = new CustomerInfo();
        Long customerId = 1L; // Assuming a valid Customer ID
        when(customerInfoRepository.findById(customerId)).thenReturn(Optional.of(customerInfo));

        // Create a CustomerInfoDto for testing
        CustomerInfoDto customerInfoDto = new CustomerInfoDto();
        when(customerInfoMapper.fromEntityToDto(customerInfo)).thenReturn(customerInfoDto);

        // Call the service method
        ResponseEntity<CustomerInfoDto> responseEntity = customerInfoService.getCustomerById(customerId);

        // Assertions
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(customerInfoDto, responseEntity.getBody());
    }

    @Test
    public void testGetCustomerById_CustomerNotFound() {
        // Create a CustomerInfo with an invalid Customer ID
        Long nonExistentCustomerId = 999L; // Assuming an invalid Customer ID
        when(customerInfoRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());

        // Call the service method and assert that it throws the expected exception
        assertThrows(NoSuchCustomerException.class, () -> customerInfoService.getCustomerById(nonExistentCustomerId));
    }

    @Test
    public void testCreateCustomer() { //TODO: does not work
        // Create a CustomerInfoDto for testing
        CustomerInfoDto customerInfoDto = new CustomerInfoDto();

        // Create a CustomerInfo for testing
        CustomerInfo customerInfo = new CustomerInfo();
        when(customerInfoMapper.fromDtoToEntity(customerInfoDto)).thenReturn(customerInfo);
        when(customerInfoRepository.save(customerInfo)).thenReturn(customerInfo);

        // Call the service method
        ResponseEntity<CustomerInfoDto> responseEntity = customerInfoService.createCustomer(customerInfoDto);

        // Assertions
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(customerInfoDto, responseEntity.getBody());
    }

    @Test
    public void testUpdateCustomer() { //TODO: does not work
        // Create a CustomerInfoDto for testing
        CustomerInfoDto customerInfoDto = new CustomerInfoDto();
        customerInfoDto.setCustomerId(1L); // Assuming a valid Customer ID

        // Create a CustomerInfo for testing
        CustomerInfo customerInfo = new CustomerInfo();
        when(customerInfoRepository.findById(1L)).thenReturn(Optional.of(customerInfo));
        when(customerInfoMapper.fromDtoToEntity(customerInfoDto)).thenReturn(customerInfo);

        // Call the service method
        ResponseEntity<CustomerInfoDto> responseEntity = customerInfoService.updateCustomer(customerInfoDto);

        // Assertions
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(customerInfoDto, responseEntity.getBody());
    }

    @Test
    public void testUpdateCustomer_CustomerNotFound() {
        // Create a CustomerInfoDto for testing with an invalid Customer ID
        CustomerInfoDto customerInfoDto = new CustomerInfoDto();
        customerInfoDto.setCustomerId(999L); // Assuming an invalid Customer ID

        // Create a CustomerInfo with an invalid Customer ID
        Long nonExistentCustomerId = 999L; // Assuming an invalid Customer ID
        when(customerInfoRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());

        // Call the service method and assert that it throws the expected exception
        assertThrows(NoSuchCustomerException.class, () -> customerInfoService.updateCustomer(customerInfoDto));
    }
    @Test
    public void testDeleteCustomer() {
        // Create a CustomerInfo for testing
        CustomerInfo customerInfo = new CustomerInfo();
        Long customerId = 1L; // Assuming a valid Customer ID
        when(customerInfoRepository.findById(customerId)).thenReturn(Optional.of(customerInfo));

        // Call the service method
        ResponseEntity<CustomerInfoDto> responseEntity = customerInfoService.deleteCustomer(customerId);

        // Verify that the customerInfoRepository methods were called correctly
        verify(customerInfoRepository).findById(customerId);
        verify(customerInfoRepository).deleteById(customerId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteCustomer_CustomerNotFound() {
        // Create a CustomerInfo with an invalid Customer ID
        Long nonExistentCustomerId = 999L; // Assuming an invalid Customer ID
        when(customerInfoRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());

        // Call the service method and assert that it throws the expected exception
        assertThrows(NoSuchCustomerException.class, () -> customerInfoService.deleteCustomer(nonExistentCustomerId));
    }

}
