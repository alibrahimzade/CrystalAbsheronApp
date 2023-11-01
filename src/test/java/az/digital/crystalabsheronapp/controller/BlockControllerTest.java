package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.dto.BlockDto;
import az.digital.crystalabsheronapp.service.BlockService;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.when;

public class BlockControllerTest {
    @InjectMocks
    private BlockController blockController;

    @Mock
    private BlockService blockService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBlocks_Success() {
        // Prepare a list of BlockDto for the response
        List<BlockDto> blockDtoList = Arrays.asList(
                new BlockDto(1L, "Block 1",1L),
                new BlockDto(2L, "Block 2",1L)
        );

        // Mock the behavior of the service
        when(blockService.getAll()).thenReturn(ResponseEntity.ok(blockDtoList));

        // Perform the test
        ResponseEntity<List<BlockDto>> responseEntity = blockController.getAllBlocks();

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(blockDtoList, responseEntity.getBody());
    }

    @Test
    public void testGetBlockById_Success() {
        // Prepare a BlockDto for the response
        BlockDto blockDto = new BlockDto(1L, "Block 1",1L);

        // Mock the behavior of the service
        when(blockService.getBlocksById(1L)).thenReturn(ResponseEntity.ok(blockDto));

        // Perform the test
        ResponseEntity<BlockDto> responseEntity = blockController.getBlockById(1L);

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(blockDto, responseEntity.getBody());
    }

    @Test
    public void testCreateBlock_Success() {
        // Prepare a BlockDto for the request
        BlockDto blockDto = new BlockDto(1L, "Block 1",1L);

        // Mock the behavior of the service
        when(blockService.createBlock(blockDto)).thenReturn(ResponseEntity.ok(blockDto));

        // Perform the test
        ResponseEntity<BlockDto> responseEntity = blockController.createBlock(blockDto);

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(blockDto, responseEntity.getBody());
    }

    @Test
    public void testUpdateBlock_Success() {
        // Prepare a BlockDto for the request
        BlockDto blockDto = new BlockDto(1L, "Updated Block 1",1L);

        // Mock the behavior of the service
        when(blockService.updateBlock(blockDto)).thenReturn(ResponseEntity.ok(blockDto));

        // Perform the test
        ResponseEntity<BlockDto> responseEntity = blockController.updateBlock(blockDto);

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(blockDto, responseEntity.getBody());
    }

    @Test
    public void testDeleteBlock_Success() {
        // Mock the behavior of the service
        when(blockService.deleteBlock(1L)).thenReturn(ResponseEntity.status(HttpStatus.OK).build());

        // Perform the test
        ResponseEntity<BlockDto> responseEntity = blockController.deleteBlock(1L);

        // Verify the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody()); // Response is empty for delete
    }
}
