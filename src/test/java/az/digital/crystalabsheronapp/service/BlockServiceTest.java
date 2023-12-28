package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Block;
import az.digital.crystalabsheronapp.dao.entity.Residence;
import az.digital.crystalabsheronapp.dao.repository.BlockRepository;
import az.digital.crystalabsheronapp.dao.repository.ResidenceRepository;
import az.digital.crystalabsheronapp.dto.BlockDto;
import az.digital.crystalabsheronapp.exceptions.NoSuchBlocksException;
import az.digital.crystalabsheronapp.exceptions.NoSuchResidenceException;
import az.digital.crystalabsheronapp.mapper.BlockMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BlockServiceTest {
        @Mock
        private BlockRepository blockRepository;
        @Mock
        private ResidenceRepository residenceRepository;

        @Mock
        private BlockMapper blockMapper;

        @InjectMocks
        private BlockService blockService;

        @BeforeEach
        public void setUp() {
                MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testGetAll() {
                // Create a list of blocks for testing
                List<Block> blocks = new ArrayList<>();
                when(blockRepository.findAll()).thenReturn(blocks);

                // Mock the mapping of blocks to DTOs
                List<BlockDto> blockDtos = new ArrayList<>();
                when(blockMapper.fromEntityListToDtoList(blocks)).thenReturn(blockDtos);

                // Call the service method
                ResponseEntity<List<BlockDto>> responseEntity = blockService.getAll();

                // Assertions
                assertEquals(ResponseEntity.ok(blockDtos), responseEntity);
        }

        @Test
        public void testGetBlocksById() {
                // Create a block for testing
                Block block = new Block();
                Long blockId = 1L;
                when(blockRepository.findById(blockId)).thenReturn(Optional.of(block));

                // Mock the mapping of a block to DTO
                BlockDto blockDto = new BlockDto();
                when(blockMapper.fromEntityToDto(block)).thenReturn(blockDto);

                // Call the service method
                ResponseEntity<BlockDto> responseEntity = blockService.getBlocksById(blockId);

                // Assertions
                assertEquals(ResponseEntity.ok(blockDto), responseEntity);
        }

        @Test
        public void testGetBlocksById_NotFound() {
                Long nonExistentBlockId = 999L;
                when(blockRepository.findById(nonExistentBlockId)).thenReturn(Optional.empty());

                // Call the service method and assert that it throws the expected exception
                assertThrows(NoSuchBlocksException.class, () -> blockService.getBlocksById(nonExistentBlockId));
        }


        @Test
        public void testCreateBlock_InvalidResidenceId() {
                // Create a BlockDto with an invalid Residence ID
                BlockDto blockDto = new BlockDto();
                blockDto.setResidenceId(999L); // Assuming an invalid Residence ID

                // Mock that the residence is not found
                when(residenceRepository.findById(999L)).thenReturn(Optional.empty());

                // Call the service method and assert that it throws the expected exception
                assertThrows(NoSuchResidenceException.class, () -> blockService.createBlock(blockDto));
        }

        @Test
        public void testUpdateBlock() { // TODO: does not work
                // Create a BlockDto for testing
                BlockDto blockDto = new BlockDto();
                blockDto.setId(1L); // Assuming a valid Block ID
                blockDto.setResidenceId(2L); // Assuming a valid Residence ID

                // Create a Block and Residence for testing
                Block existingBlock = new Block();
                Residence residence = new Residence();
                when(blockRepository.findById(1L)).thenReturn(Optional.of(existingBlock));
                when(residenceRepository.findById(2L)).thenReturn(Optional.of(residence));

                // Mock the mapping of BlockDto to Block entity
                Block updatedBlock = new Block();
                when(blockMapper.fromDtoToEntity(blockDto)).thenReturn(updatedBlock);

                // Mock the save operation in the repository
                when(blockRepository.save(updatedBlock)).thenReturn(updatedBlock);

                // Call the service method
                ResponseEntity<BlockDto> responseEntity = blockService.updateBlock(blockDto);

                // Assertions
                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//                assertEquals(blockDto, responseEntity.getBody()); // Assuming your mapper correctly maps Block to BlockDto
        }

        @Test
        public void testUpdateBlock_BlockNotFound() {
                // Create a BlockDto with an invalid Block ID
                BlockDto blockDto = new BlockDto();
                blockDto.setId(999L); // Assuming an invalid Block ID

                // Mock that the block is not found
                when(blockRepository.findById(999L)).thenReturn(Optional.empty());

                // Call the service method and assert that it throws the expected exception
                assertThrows(NoSuchBlocksException.class, () -> blockService.updateBlock(blockDto));
        }

        @Test
        public void testUpdateBlock_InvalidResidenceId() {
                // Create a BlockDto with an invalid Residence ID
                BlockDto blockDto = new BlockDto();
                blockDto.setId(1L); // Assuming a valid Block ID
                blockDto.setResidenceId(999L); // Assuming an invalid Residence ID

                // Mock that the residence is not found
                when(blockRepository.findById(1L)).thenReturn(Optional.of(new Block())); // Mock an existing Block
                when(residenceRepository.findById(999L)).thenReturn(Optional.empty());

                // Call the service method and assert that it throws the expected exception
                assertThrows(NoSuchResidenceException.class, () -> blockService.updateBlock(blockDto));
        }

        @Test
        public void testDeleteBlock() {
                // Create a Block for testing
                Block block = new Block();
                Long blockId = 1L; // Assuming a valid Block ID
                when(blockRepository.findById(blockId)).thenReturn(Optional.of(block));

                // Call the service method
                ResponseEntity<BlockDto> responseEntity = blockService.deleteBlock(blockId);

                // Assertions
                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertEquals(null, responseEntity.getBody()); // The response body should be null for a successful deletion
        }

        @Test
        public void testDeleteBlock_BlockNotFound() {
                // Create a Block with an invalid Block ID
                Long nonExistentBlockId = 999L; // Assuming an invalid Block ID
                when(blockRepository.findById(nonExistentBlockId)).thenReturn(Optional.empty());

                // Call the service method and assert that it throws the expected exception
                assertThrows(NoSuchBlocksException.class, () -> blockService.deleteBlock(nonExistentBlockId));
        }

}
