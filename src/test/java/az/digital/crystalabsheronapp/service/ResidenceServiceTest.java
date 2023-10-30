package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Residence;
import az.digital.crystalabsheronapp.dao.repository.ResidenceRepository;
import az.digital.crystalabsheronapp.dto.ResidenceDto;
import az.digital.crystalabsheronapp.exceptions.NoSuchResidenceException;
import az.digital.crystalabsheronapp.mapper.ResidenceMapper;
import az.digital.crystalabsheronapp.wrapper.ResidenceWrapper;
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
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ResidenceServiceTest {
    @Mock
    private ResidenceRepository repository;

    @Mock
    private ResidenceMapper residenceMapper;

    @InjectMocks
    private ResidenceService residenceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindResidenceById() {
        // Create a Residence for testing
        Residence residence = new Residence();
        Long residenceId = 1L; // Assuming a valid Residence ID
        when(repository.findById(residenceId)).thenReturn(Optional.of(residence));

        // Create a ResidenceDto for testing
        ResidenceDto residenceDto = new ResidenceDto();
        when(residenceMapper.fromEntityToDto(residence)).thenReturn(residenceDto);

        // Call the service method
        ResponseEntity<ResidenceDto> responseEntity = residenceService.findResidenceById(residenceId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(residenceDto, responseEntity.getBody());
    }

    @Test
    public void testFindResidenceById_ResidenceNotFound() {
        // Create a Residence with an invalid Residence ID
        Long nonExistentResidenceId = 999L; // Assuming an invalid Residence ID
        when(repository.findById(nonExistentResidenceId)).thenReturn(Optional.empty());

        // Call the service method and assert that it throws the expected exception
        assertThrows(NoSuchResidenceException.class, () -> residenceService.findResidenceById(nonExistentResidenceId));
    }

    @Test
    public void testFindAllResidences() {
        // Create a list of ResidenceWrappers for testing
        List<ResidenceWrapper> residenceWrappers = List.of(new ResidenceWrapper(), new ResidenceWrapper());
        when(repository.getAllResidences()).thenReturn(residenceWrappers);

        // Call the service method
        ResponseEntity<List<ResidenceWrapper>> responseEntity = residenceService.findAllResidences();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(residenceWrappers, responseEntity.getBody());
    }

    @Test
    public void testCreateResidence() {
        // Create a ResidenceDto for testing
        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setName("ResidenceName");

        // Mock the repository method to return an empty Optional, indicating that the residence does not exist
        when(repository.getResidenceByName("ResidenceName")).thenReturn(Optional.empty());

        // Call the service method
        ResponseEntity<?> responseEntity = residenceService.createResidence(residenceDto);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateResidence_ResidenceAlreadyExists() {
        // Create a ResidenceDto for testing
        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setName("ResidenceName");

        // Mock the repository method to return a non-empty Optional, indicating that the residence already exists
        when(repository.getResidenceByName("ResidenceName")).thenReturn(Optional.of(new Residence()));

        // Call the service method
        ResponseEntity<?> responseEntity = residenceService.createResidence(residenceDto);

        // Assertions
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateResidence() {
        // Create a ResidenceDto for testing
        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setName("NewResidenceName");

        // Create a Residence for testing
        Residence existingResidence = new Residence();
        Long residenceId = 1L; // Assuming a valid Residence ID
        when(repository.findById(residenceId)).thenReturn(Optional.of(existingResidence));

        // Call the service method
        ResponseEntity<?> responseEntity = residenceService.updateResidence(residenceId, residenceDto);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateResidence_ResidenceNotFound() {
        // Create a ResidenceDto for testing
        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setName("NewResidenceName");

        // Create a Residence with an invalid Residence ID
        Long nonExistentResidenceId = 999L; // Assuming an invalid Residence ID
        when(repository.findById(nonExistentResidenceId)).thenReturn(Optional.empty());

        // Call the service method and assert that it throws the expected exception
        assertThrows(NoSuchResidenceException.class, () -> residenceService.updateResidence(nonExistentResidenceId, residenceDto));
    }

    @Test
    public void testDeleteResidenceById() {
        // Create a Residence for testing
        Residence residence = new Residence();
        Long residenceId = 1L; // Assuming a valid Residence ID
        when(repository.findById(residenceId)).thenReturn(Optional.of(residence));

        // Call the service method
        ResponseEntity<?> responseEntity = residenceService.deleteResidenceById(residenceId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteResidenceById_ResidenceNotFound() {
        // Create a Residence with an invalid Residence ID
        Long nonExistentResidenceId = 999L; // Assuming an invalid Residence ID
        when(repository.findById(nonExistentResidenceId)).thenReturn(Optional.empty());

        // Call the service method and assert that it throws the expected exception
        assertThrows(NoSuchResidenceException.class, () -> residenceService.deleteResidenceById(nonExistentResidenceId));
    }


}
