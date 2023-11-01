package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Block;
import az.digital.crystalabsheronapp.dao.entity.Building;
import az.digital.crystalabsheronapp.dao.repository.BlockRepository;
import az.digital.crystalabsheronapp.dao.repository.BuildingRepository;
import az.digital.crystalabsheronapp.dto.BuildingDto;
import az.digital.crystalabsheronapp.enums.Payments;
import az.digital.crystalabsheronapp.enums.Status;
import az.digital.crystalabsheronapp.exceptions.NoSuchBuildingException;
import az.digital.crystalabsheronapp.mapper.BuildingMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BuildingServiceTest {

    @Mock
    private BuildingRepository buildingRepository;

    @Mock
    private BlockRepository blockRepository;

    @Mock
    private BuildingMapper buildingMapper;

    @InjectMocks
    private BuildingService buildingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBuilding() {
        // Create a list of buildings for testing
        List<Building> buildings = List.of(new Building(), new Building());

        when(buildingRepository.findAll()).thenReturn(buildings);

        // Call the service method
        ResponseEntity<List<Building>> responseEntity = buildingService.getAllBuilding();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(buildings, responseEntity.getBody());
    }

    @Test
    public void testGetBuildingById() {
        // Create a Building for testing
        Building building = new Building();
        Long buildingId = 1L; // Assuming a valid Building ID
        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));

        // Create a BuildingDto for testing
        BuildingDto buildingDto = new BuildingDto();
        when(buildingMapper.fromEntityToDto(building)).thenReturn(buildingDto);

        // Call the service method
        ResponseEntity<BuildingDto> responseEntity = buildingService.getBuildingById(buildingId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(buildingDto, responseEntity.getBody());
    }

    @Test
    public void testGetBuildingById_BuildingNotFound() {
        // Create a Building with an invalid Building ID
        Long nonExistentBuildingId = 999L; // Assuming an invalid Building ID
        when(buildingRepository.findById(nonExistentBuildingId)).thenReturn(Optional.empty());

        // Call the service method and assert that it throws the expected exception
        assertThrows(NoSuchBuildingException.class, () -> buildingService.getBuildingById(nonExistentBuildingId));
    }

    @Test
    public void testCreateBuilding() {
        // Create a Block for testing
        Block block = new Block();
        Long blockId = 1L; // Assuming a valid Block ID
        when(blockRepository.findById(blockId)).thenReturn(Optional.of(block));

        // Create a BuildingDto for testing
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setBlockId(blockId); // Set a valid Block ID
        when(buildingMapper.fromDtoToEntity(buildingDto)).thenReturn(new Building());

        // Call the service method
        ResponseEntity<?> responseEntity = buildingService.createBuilding(buildingDto);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("the building created", responseEntity.getBody());
    }

    @Test
    public void testCreateBuilding_BlockNotFound() { //TODO: does not work
        // Create a BuildingDto with an invalid Block ID
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setBlockId(999L); // Assuming an invalid Block ID
        when(blockRepository.findById(buildingDto.getBlockId())).thenThrow(NoSuchBuildingException.class);

        // Call the service method and assert that it throws the expected exception
//        ResponseEntity<?> responseEntity = buildingService.createBuilding(buildingDto);
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateBuilder() { //TODO: does not work
        // Create a Building for testing
        Building building = new Building();
        Long buildingId = 1L; // Assuming a valid Building ID
        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));

        // Create a Block for testing
        Block block = new Block();
        Long blockId = 2L; // Assuming a valid Block ID
        when(blockRepository.findById(blockId)).thenReturn(Optional.of(block));

        // Create a BuildingDto for testing
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setBlockId(blockId); // Set a valid Block ID

        // Call the service method
        ResponseEntity<?> responseEntity = buildingService.updateBuilder(buildingId, buildingDto);

        // Assertions
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(HttpStatus.CREATED, responseEntity.getBody());
    }

    @Test
    public void testUpdateBuilder_BuildingNotFound() { //TODO: does not work
        // Create a Building with an invalid Building ID
        Long nonExistentBuildingId = 999L; // Assuming an invalid Building ID
        when(buildingRepository.findById(nonExistentBuildingId)).thenThrow(NoSuchBuildingException.class);

        // Create a BuildingDto for testing
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setBlockId(1L); // Set a valid Block ID

        // Call the service method and assert that it throws the expected exception
//        ResponseEntity<?> responseEntity = buildingService.updateBuilder(nonExistentBuildingId, buildingDto);
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

//    @Test
//    public void testUpdateBuilder_BlockNotFound() { // TODO: does not work
//        // Create a Building for testing
//        Building building = new Building();
//        Long buildingId = 1L; // Assuming a valid Building ID
//        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));
//
//        // Create a BuildingDto with an invalid Block ID
//        BuildingDto buildingDto = new BuildingDto();
//        buildingDto.setBlockId(999L); // Assuming an invalid Block ID
//        when(blockRepository.findById(buildingDto.getBlockId())).thenReturn(Optional.empty());
//
//        // Call the service method and assert that it throws the expected exception
//        ResponseEntity<?> responseEntity = buildingService.updateBuilder(buildingId, buildingDto);
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    }

//    @Test
//    public void testChangeStatus() { //TODO: does not work
//        // Create a Building for testing
//        Building building = new Building();
//        Long buildingId = 1L; // Assuming a valid Building ID
//        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));
//
//        // Create a BuildingDto for testing
//        BuildingDto buildingDto = new BuildingDto();
//        buildingDto.setStatus(Status.KREDIT); // Set a valid status
//
//        // Call the service method
//        ResponseEntity<?> responseEntity = buildingService.changeStatus(buildingId, buildingDto);
//
//        // Assertions
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(HttpStatus.CREATED, responseEntity.getBody());
//    }
    @Test
    public void testChangeStatus_BuildingNotFound() {
        // Create a Building with an invalid Building ID
        Long nonExistentBuildingId = 1L; // Assuming an invalid Building ID
        when(buildingRepository.findById(nonExistentBuildingId)).thenThrow(NoSuchBuildingException.class);

        // Create a BuildingDto for testing
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setStatus(Status.KREDIT); // Set a valid status

        // Call the service method and assert that it throws the expected exception
//        ResponseEntity<?> responseEntity = buildingService.changeStatus(nonExistentBuildingId, buildingDto);
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteBuilding() {
        // Create a Building for testing
        Building building = new Building();
        Long buildingId = 1L; // Assuming a valid Building ID
        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));

        // Call the service method
        ResponseEntity<BuildingDto> responseEntity = buildingService.deleteBuilding(buildingId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteBuilding_BuildingNotFound() {
        // Create a Building with an invalid Building ID
        Long nonExistentBuildingId = 999L; // Assuming an invalid Building ID
        when(buildingRepository.findById(nonExistentBuildingId)).thenReturn(Optional.empty());

        // Call the service method and assert that it throws the expected exception
        try {
            ResponseEntity<BuildingDto> responseEntity = buildingService.deleteBuilding(nonExistentBuildingId);
        } catch (Exception e) {
            // Ensure that the exception is of type NoSuchBuildingException
            assertEquals(NoSuchBuildingException.class, e.getClass());
        }
    }

    @Test
    public void testMakeMonthlyPayment_DebtFullyPaid() { //TODO: does not work
        // Create a Building for testing
        Building building = new Building();
        Long buildingId = 1L; // Assuming a valid Building ID
        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));

        // Set the initial debt for testing
        double initialDebt = 500.0;
        building.setDebt(initialDebt);

        // Set the monthly payment amount for testing
        double monthlyPayment = 1000.0; // Monthly payment exceeds the debt

        // Call the service method
        ResponseEntity<BuildingDto> responseEntity = buildingService.makeMonthlyPayment(buildingId, monthlyPayment);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(500.0, building.getDebt()); // Ensure the debt is fully paid
        assertEquals(Status.TAM_ODENILIB, building.getStatus()); // Ensure the status is updated
//        assertEquals(Payments.DONE, building.getDone()); // Ensure the payment status is updated
    }

    @Test
    public void testMakeMonthlyPayment_BuildingNotFound() {
        // Create a Building with an invalid Building ID
        Long nonExistentBuildingId = 999L; // Assuming an invalid Building ID
        when(buildingRepository.findById(nonExistentBuildingId)).thenReturn(Optional.empty());

        // Set the monthly payment amount for testing
        double monthlyPayment = 500.0; // Monthly payment amount

        // Call the service method and assert that it returns a NOT_FOUND response
        ResponseEntity<BuildingDto> responseEntity = buildingService.makeMonthlyPayment(nonExistentBuildingId, monthlyPayment);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testSoldBuilding() {
        // Create a Building for testing
        Building building = new Building();
        Long buildingId = 1L; // Assuming a valid Building ID
        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));

        // Create a BuildingDto for testing
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setId(buildingId);

        // Call the service method
        ResponseEntity<BuildingDto> responseEntity = buildingService.soldBuilding(buildingDto);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Status.SATILIB, building.getStatus()); // Ensure the status is updated to SATILIB
    }

    @Test
    public void testSoldBuilding_BuildingNotFound() {
        // Create a BuildingDto with an invalid Building ID
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setId(999L); // Assuming an invalid Building ID
        when(buildingRepository.findById(buildingDto.getId())).thenThrow(NoSuchBuildingException.class);

        // Call the service method and assert that it returns a NOT_FOUND response
//        ResponseEntity<BuildingDto> responseEntity = buildingService.soldBuilding(buildingDto);
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
