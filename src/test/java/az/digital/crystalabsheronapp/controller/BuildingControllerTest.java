package az.digital.crystalabsheronapp.controller;
import az.digital.crystalabsheronapp.dto.BuildingDto;
import az.digital.crystalabsheronapp.exceptions.NoSuchBuildingException;
import az.digital.crystalabsheronapp.service.BuildingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BuildingControllerTest {
    @InjectMocks
    private BuildingController buildingController;

    @Mock
    private BuildingService buildingService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBuildings_Success() {
        // Mock the behavior of the service
        when(buildingService.getAllBuilding()).thenReturn(ResponseEntity.ok(Collections.emptyList()));

        // Perform the test
        ResponseEntity<?> responseEntity = buildingController.getAllBuildings();

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(buildingService, times(1)).getAllBuilding();
    }

    @Test
    public void testGetBuildingById_Success() {
        Long buildingId = 1L;
        // Mock the behavior of the service
        when(buildingService.getBuildingById(buildingId)).thenReturn(ResponseEntity.ok(new BuildingDto()));

        // Perform the test
        ResponseEntity<?> responseEntity = buildingController.getBuildingById(buildingId);

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(buildingService, times(1)).getBuildingById(buildingId);
    }

    @Test
    public void testGetBuildingById_NotFound() {
        Long buildingId = 1L;
        // Mock the behavior of the service to return a 404 Not Found response
        when(buildingService.getBuildingById(buildingId)).thenReturn(ResponseEntity.notFound().build());

        // Perform the test
        ResponseEntity<?> responseEntity = buildingController.getBuildingById(buildingId);

        // Verify the results
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(buildingService, times(1)).getBuildingById(buildingId);
    }

    @Test
    public void testUpdateBuilding_Success() {
        Long buildingId = 1L;
        BuildingDto buildingDto = new BuildingDto();
        // Mock the behavior of the service
        when(buildingService.updateBuilder(buildingId, buildingDto)).thenReturn(ResponseEntity.ok().build());

        // Perform the test
        ResponseEntity<?> responseEntity = buildingController.updateBuilding(buildingDto, buildingId);

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(buildingService, times(1)).updateBuilder(buildingId, buildingDto);
    }

}
