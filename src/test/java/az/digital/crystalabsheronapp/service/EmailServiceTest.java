package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.repository.CustomerInfoRepository;
import az.digital.crystalabsheronapp.dto.CustomerInfoDto;
import az.digital.crystalabsheronapp.exceptions.NoSuchCustomerException;
import az.digital.crystalabsheronapp.mapper.CustomerInfoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class EmailServiceTest {
    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private CustomerInfoRepository customerInfoRepository;

    @Mock
    private CustomerInfoMapper customerInfoMapper;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdatePaymentDueDate_CustomerNotFound() {
        // Create a CustomerInfoDto for testing with an invalid Customer ID
        CustomerInfoDto customerInfoDto = new CustomerInfoDto();
        customerInfoDto.setCustomerId(999L); // Assuming an invalid Customer ID

        // Create a CustomerInfo with an invalid Customer ID
        when(customerInfoRepository.findById(999L)).thenReturn(java.util.Optional.empty());

        // Call the service method and assert that it throws the expected exception
        assertThrows(NoSuchCustomerException.class, () -> emailService.updatePaymentDueDate(customerInfoDto));
    }
}