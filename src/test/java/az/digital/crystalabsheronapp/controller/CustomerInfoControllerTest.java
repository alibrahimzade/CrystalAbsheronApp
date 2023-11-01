package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.dto.CustomerInfoDto;
import az.digital.crystalabsheronapp.service.CustomerInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CustomerInfoControllerTest {
    @InjectMocks
    private CustomerInfoController customerInfoController;

    @Mock
    private CustomerInfoService customerInfoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetAllCustomers_Success() {
        // Prepare a list of CustomerInfoDto for the response
        List<CustomerInfoDto> customerInfoDtoList = Arrays.asList(
                new CustomerInfoDto(1L, "John", "Doe",null,null,null,null),
                new CustomerInfoDto(2L, "Alice", "Smith",null,null,null,null)
        );

        // Mock the behavior of the service
        when(customerInfoService.getAllCustomers()).thenReturn(ResponseEntity.ok(customerInfoDtoList));

        // Perform the test
        ResponseEntity<List<CustomerInfoDto>> responseEntity = customerInfoController.getAllCustomers();

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customerInfoDtoList, responseEntity.getBody());
    }
    @Test
    public void testGetCustomerById_Success() {
        Long customerId = 1L;
        CustomerInfoDto customerInfoDto = new CustomerInfoDto(1L, "John", "Doe",null,null,null,null);

        // Mock the behavior of the service to return the customer by ID
        when(customerInfoService.getCustomerById(customerId)).thenReturn(ResponseEntity.ok(customerInfoDto));

        // Perform the test
        ResponseEntity<CustomerInfoDto> responseEntity = customerInfoController.getCustomerById(customerId);

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customerInfoDto, responseEntity.getBody());
    }
    @Test
    public void testCreateCustomer_Success() {
        CustomerInfoDto customerInfoDto = new CustomerInfoDto(1L, "John", "Doe",null,null,null,null);

        // Mock the behavior of the service to create a customer
        when(customerInfoService.createCustomer(customerInfoDto)).thenReturn(ResponseEntity.ok(customerInfoDto));

        // Perform the test
        ResponseEntity<CustomerInfoDto> responseEntity = customerInfoController.createCustomer(customerInfoDto);

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customerInfoDto, responseEntity.getBody());
    }
    @Test
    public void testUpdateCustomer_Success() {
        CustomerInfoDto customerInfoDto = new CustomerInfoDto(1L, "John", "Doe",null,null,null,null);

        // Mock the behavior of the service to update a customer
        when(customerInfoService.updateCustomer(customerInfoDto)).thenReturn(ResponseEntity.ok(customerInfoDto));

        // Perform the test
        ResponseEntity<CustomerInfoDto> responseEntity = customerInfoController.updateCustomer(customerInfoDto);

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customerInfoDto, responseEntity.getBody());
    }

    @Test
    public void testDeleteCustomer_Success() {
        Long customerId = 1L;

        // Mock the behavior of the service to delete a customer
        when(customerInfoService.deleteCustomer(customerId)).thenReturn(ResponseEntity.status(HttpStatus.OK).build());

        // Perform the test
        ResponseEntity<CustomerInfoDto> responseEntity = customerInfoController.deleteCustomer(customerId);

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
